# Test functions. This program won't work until support for multiple
# parameters has been added.

func int test (int a, int b): 
	return a+b .

proc main ():
        write(test(2,3))
.
