# Test if-commands.

int m = 7

proc main ():
	int n = m-4
	if m>0: write(m) .
	if m<n:
		m = m+1
		write(m)
	else:
		n = n+1
		write(n) .
.
