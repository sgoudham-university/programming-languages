//////////////////////////////////////////////////////////////
//
// Representation of FunVM code, global, and local addresses.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
//////////////////////////////////////////////////////////////

package fun;

public class Address {

	public static final int
	   CODE   = 0,
	   GLOBAL = 1,
	   LOCAL  = 2;

	public int offset;
	public int locale;  // CODE, GLOBAL, or LOCAL

	public Address (int off, int loc) {
		offset = off;  locale = loc;
	}

}
