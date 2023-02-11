# Test recursive procedures.

proc writeoctal (int n):  
# Write n in octal, 1 digit at a time in the correct order.
	if n < 8:
		write(n)
	else:
		writeoctal(n/8)
		write(n-((n/8)*8)) .
.

proc main ():
# Read positive numbers, terminating when a 0 is read.
# Write each number in octal.
	int num = read()
	while num > 0:
		writeoctal(num)
		num = read() .
.
