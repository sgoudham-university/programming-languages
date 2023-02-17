////////////////////////////////////////////////////////////////
//
// Representation of types.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
////////////////////////////////////////////////////////////////

package fun;

import java.util.ArrayList;

public abstract class Type {

	// An object of class Type represents a Fun type, which may 
	// be a primitive type, a pair type, or a mapping type,

	public static final Primitive
		VOID = new Primitive(0),
		BOOL = new Primitive(1),
		INT  = new Primitive(2);

	public static final Error
		ERROR = new Error();

	public static final Sequence
		EMPTY = new Sequence(new ArrayList<Type>());
    
	public abstract boolean equiv (Type that);
	// Return true if and only if this type is equivalent 
	// to that.

	public abstract String toString ();
	// Return a textual reporesentation of this type.

	////////////////////////////////////////////////////////

	public static class Primitive extends Type {
		public int which;

		public Primitive (int w) {
			which = w;
		}

		public boolean equiv (Type that) {
			return (that instanceof Primitive
			   && this.which ==
			       ((Primitive)that).which);
		}

		public String toString () {
			switch (which) {
				case  0: return "void";
				case  1: return "bool";
				case  2: return "int";
			}
			return "???";
		}

	}

	////////////////////////////////////////////////////////

	public static class Pair extends Type {
		public Type first, second;

		public Pair (Type fst, Type snd) {
			first = fst;
			second = snd;
		}

		public boolean equiv (Type that) {
			if (that instanceof Pair) {
				Pair thatPair = (Pair)that;
				return this.first.equiv(thatPair.first)
				   && this.second.equiv(thatPair.second);
			} else
				return false;
		}

		public String toString () {
			return first + " x " + second;
		}

	}

    ////////////////////////////////////////////////////////////

    public static class Sequence extends Type {
	public ArrayList<Type> sequence;

	public Sequence(ArrayList<Type> seq) {
	    sequence = seq;
	}

	public boolean equiv(Type that) {
	    if (that instanceof Sequence) {
		ArrayList<Type> thatSequence = ((Sequence)that).sequence;
		if (thatSequence.size() != sequence.size())
		    return false;
		for (int i = 0; i < thatSequence.size(); i++)
		    if (!(thatSequence.get(i).equiv(sequence.get(i))))
			return false;
		return true;
	    }
	    else
		return false;
	}

	public String toString() {
	    String s = "[";
	    if (sequence.size() > 0) {
		s = s + sequence.get(0);
		for (int i = 1; i < sequence.size(); i++)
		    s = s + "," + sequence.get(i);
	    }
	    s = s + "]";
	    return s;
	}
    }

	////////////////////////////////////////////////////////

	public static class Mapping extends Type {
		public Type domain, range;

		public Mapping (Type d, Type r) {
			domain = d;
			range = r;
		}

		public boolean equiv (Type that) {
			if (that instanceof Mapping) {
				Mapping thatMapping =
				   (Mapping)that;
				return this.domain.equiv(
				          thatMapping.domain)
				   && this.range.equiv(
				          thatMapping.range);
			} else
				return false;
		}

		public String toString () {
			return domain + " -> " + range;
		}

	}

	////////////////////////////////////////////////////////

	public static class Error extends Type {

		public Error () {
		}

		public boolean equiv (Type that) {
			return true;
		}

		public String toString () {
			return "error";
		}

	}

}
