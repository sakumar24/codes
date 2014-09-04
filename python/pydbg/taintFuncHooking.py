# script to hook on a call to kernel32.dll.ReadFile() on entry and exit and get argument and return values.

from pydbg import *
from pydbg.defines import *
from pydbg.pydbg import *
from pydbg.pydbg_core import *
import utils
import sys
import re


openedFiles={} #keep a dictionary of opened file handles, key=handle, value = file name
mappedFileHandles={}

def CreateFileEntry(dbg, argu):
    print "Entered CreateFileEntry"
    dataMem=dbg.read_process_memory(argu[0],100)
    fileName=dbg.get_ascii_string(dataMem)
    print "going to create file: 0x%08x "%argu[0]
    print "going to create file: ",fileName
        
    return DBG_CONTINUE
    
    
def CreateFileReturn(dbg, argu, ret):
    print "Exiting CreateFile"
    dataMem=dbg.read_process_memory(argu[0],100)
    fileName=dbg.get_ascii_string(dataMem)
    print "going to create file: 0x%08x "%argu[0]
    print "going to create file: ",fileName
    if re.search(".(mp3)|(pdf)", fileName):
        openedFiles[ret]=fileName
        print "return val: ",hex(ret)
        
    return DBG_CONTINUE


# entry calback function
def ReadFileEntry(dbg, argu):
    print "Entered ReadFile"
    for k,v in openedFiles.iteritems():
        if argu[0]==k:
            print "openeing the file: %s"%str(v)
            print "file handler %08x"% argu[0]
            print "destination buffer 0x%08x"%argu[1]
            print "byte to read %d"%argu[2]
            print "byte read %d"%argu[3]
            print "overlapped %08x"% argu[4]
        print "In for loop."    
    return DBG_CONTINUE
    
    
def ReadFileReturn(dbg, argu, ret):
    print "Exiting ReadFile"
    for k,v in openedFiles.iteritems():
        if argu[0]==k:
            print "opened the file: %s"%str(v)
            print "file handler %08x"% argu[0]
            print "destination buffer 0x%08x"%argu[1]
            print "byte to read %d"%argu[2]
            fileRData=dbg.read_process_memory(argu[1],argu[2])
            #fileAscii=dbg.get_ascii_string(fileRData)
            fileHex=dbg.hex_dump(fileRData)
            print "read from the file:", fileHex
            print "byte read %d"%argu[3]
            dbg.bp_set_mem(argu[1],argu[2],description='', handler=buffer_access_handler)
    #print "return val %d"%ret
    return DBG_CONTINUE

def CloseHandleEntry(dbg, argu):
    print "Entered CloseHandleEntry"
    for k,v in openedFiles.iteritems():
        if argu[0]==k:
            print "closing file: %s"%str(v)
            print "closing file handler %08x"% argu[0]
            
    return DBG_CONTINUE
    
    
def CloseHandleReturn(dbg, argu, ret):
    print "Enter CloseHandleReturn"
    # first let us remove handles created with CreateFile()
    for k,v in openedFiles.iteritems():
        if argu[0]==k:
            print "closed the file: %s"%str(v)
            print "Closed file handler %08x"% argu[0]
            del openedFiles[k]
            break
    # Also remove handles created with CreateFileMappingA()
    for k,v in mappedFileHandles.iteritems():
        if argu[0]==k:
            print "closed the mapped file: %s"%str(v)
            print "Closed file handler %08x"% argu[0]
            del mappedFileHandles[k]
            break
    #print "return val %d"%ret
    return DBG_CONTINUE


def check_accessv(dbg):
    # skip firt-chance exceptions
    if dbg.dbg.u.Exception.dwFirstChance:
        return DBG_EXCEPTION_NOT_HANDLED
    crash_bin = utils.crash_binning.crash_binning()
    crash_bin.record_crash(dbg)
    print crash_bin.crash_synopsis()
    dbg.terminate_process()
    return DBG_EXCEPTION_NOT_HANDLED


def buffer_access_handler(dbg):
    print "buffer accessed\n"
    inst=dbg.disasm(dbg.context.Eip)
    print "## 0x%08x\t%s##"%(dbg.context.Eip,inst)
    #outFile.write("## 0x%08x\t%s\n##"%(dbg.context.Eip,inst))
    if dbg.op2 != None:
        #outFile.write('\tOP2: 0x%08x\n'%inst.op2)
        print '\tOP2: ',dbg.op2
    else:
        #outFile.write('\tOP1: 0x%08x\n'%inst.op1)
        print '\tOP1: ',dbg.op1
    return DBG_CONTINUE
   
def main():
    dbg = pydbg()
    #dbg.load(sys.argv[1])
    dbg.attach(int(sys.argv[1]))
    #outFile=open(str(sys.argv[2]),'w')
    hooks = utils.hook_container()
    #h = hooks(dbg)
    hook_addressRead=dbg.func_resolve_debuggee("kernel32", "ReadFile")
    hook_addressCreate=dbg.func_resolve_debuggee("kernel32", "CreateFileA")
    hook_addressCloseH=dbg.func_resolve_debuggee("kernel32", "CloseHandle")
    if hook_addressCreate:
        print "hooking CreateFile"
        hooks.add(dbg,hook_addressCreate, 7, CreateFileEntry, CreateFileReturn)
        print "hooked at 0x%08x" %hook_addressCreate
    else:
        print "[*] Could not resolve Create hook address"
        sys.exit(-1)
        
    if hook_addressRead:
        print "hooking ReadFile"
        hooks.add(dbg,hook_addressRead, 5, ReadFileEntry, ReadFileReturn)
        
        print "hooked at 0x%08x" %hook_addressRead
    else:
        print "[*] Could not resolve Read hook address"
        sys.exit(-1)
    if hook_addressCloseH:
        print "hooking CloseHandle"
        hooks.add(dbg,hook_addressCloseH, 1, CloseHandleEntry, CloseHandleReturn)
        print "hooked at 0x%08x" %hook_addressCloseH
    else:
        print "[*] Could not resolve Create hook address"
        sys.exit(-1)
    
    print "loading the file..."
    dbg.set_callback(EXCEPTION_ACCESS_VIOLATION,check_accessv)

    
    dbg.run()
    #close(outFile)

if __name__ == '__main__':
    main()