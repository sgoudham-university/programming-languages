# Test while-commands.

proc main ():
# Read a number, and write all squares 
# up to and including that number.
	int m = read()
	int n = 1
	while n*n < m+1:
		write(n*n)
		n = n+1 . 
.
