# Test type checking.

int n = true  # error
bool c = 1  # error

func bool pos (int n):
	return n  # error
.

proc main ():
	int i = 3
	bool b = true
	i = i+1
	i = b  # error
	i = b*2  # error
	b = i>0
	if b: write(i) .
	if i: write(i) .  # error
	b = pos(true)  # error
	while pos(7): i = i+1 .
.

