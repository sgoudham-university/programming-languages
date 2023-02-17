# Test procedures.

int total = 0

proc add (int inc):
	total = total + inc .

proc main ():
# Read numbers until a 0 is read.
# Print their total.
	int i = read()
	while i > 0:
		add(i)
		i = read() .
	write(total)
.