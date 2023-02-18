# Test functions. This program won't work until support for multiple
# parameters has been added.

func int test (int a, int b): 
	return a+b .

func int pigeon (int winston, int amy):
  int hammy = winston - amy
  return test(hammy,hammy)
  .

proc main ():
    write(pigeon(20,10))
.
