# Test scope checking.

int y = x  # error
int x = 1
bool x = true  # error

proc main ():
	int n = 0
	int x = 0
	int n = 1  # error
	x = x+y  # error
	p()  # error
.
