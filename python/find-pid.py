from pydbg import * 

def find_pid(dbg, name):
    namel = name.lower()
    found_target = False
    for (pid, proc_name) in dbg.enumerate_processes():
        if proc_name.lower() == namel:
            return pid
    return -1


#using the function:

dbg = pydbg()

process="chrome.exe"
pid = find_pid(dbg, process)
if pid!=-1:
    print ("PID : %d") % (pid)
    dbg.attach(pid)
else:
    error("process not found.")