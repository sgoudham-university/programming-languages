////////////////////////////////////////////////////////////////
//
// Representation of generic symbol tables.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
////////////////////////////////////////////////////////////////

package fun;

import java.util.HashMap;

public class SymbolTable<A> {

	// An object of class SymbolTable<A> represents a symbol 
	// table in which identifiers (strings) are associated 
	// with attributes of type A. The symbol table comprises 
	// a global part (which is always enabled) and a local 
	// part (which may be enabled or disabled). Each part is 
	// a set of (String,A) entries in which the strings are 
	// unique.

	private HashMap<String,A> globals, locals;

	public SymbolTable () {
		globals = new HashMap<String,A>();
		locals = null;  // initially disabled
	}

	public boolean put (String id, A attr) {
	// Add (id,attr) to this symbol table, either to the 
	// local part (if enabled) or to the global part 
	// (otherwise). Return true iff id is unique.
		HashMap<String,A> scope =
			(locals != null ? locals : globals);
		if (scope.get(id) == null) {
			scope.put(id, attr);
			return true;
		} else
			return false;
	}

	public A get (String id) {
	// Retrieve the attribute corresponding to id in this 
	// symbol table. If id occurs in both local and global 
	// parts, prefer the local one. Return the attribute, 
	// or null if id is not found.
		if (locals != null && locals.get(id) != null)
			return locals.get(id);
		else
			return globals.get(id);
	}

	public A getLocal (String id) {
	// Retrieve the attribute corresponding to id in the 
	// local part of this symbol table. Return the attribute, 
	// or null if id is not found.
		if (locals != null)
			return locals.get(id);
		else
			return null;
	}

	public void enterLocalScope () {
	// Enable the local part of this symbol table.
		// Assume that locals == null.
		locals = new HashMap<String,A>();
	}

	public void exitLocalScope () {
	// Discard all entries in the local part of this 
	// symbol table, and disable the local part.
		locals = null;
	}

	public String toString () {
	// Return a textual representation of this symbol table.
		String s = "Globals: " + globals + "\n";
		if (locals != null)
			s += "Locals: " + locals + "\n";
		return s;
	}
}
