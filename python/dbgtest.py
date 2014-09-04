from pydbg import *

dbgObj = pydbg()                      # Create PyDbg object
print dbgObj.enumerate_processes()    # Lists all the current running processes along with the PIDs.