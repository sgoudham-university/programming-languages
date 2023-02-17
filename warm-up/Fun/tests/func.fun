# Test functions.

bool verbose = false

func int fac (int n):  # Return n!
	int f = 1
	while n > 1:
		f = f*n
		n = n-1 .
	return f .

proc main ():
# Read integers and write their factorials.
# Terminate when 0 is read.
	int num = read()
	while not (num == 0):
		if verbose: write(num) .
		write(fac(num))
		num = read() .
.
