//////////////////////////////////////////////////////////////
//
// Driver for the Fun syntactic analyser.
//
// Based on a previous version developed by
// David Watt and Simon Gay (University of Glasgow).
//
//////////////////////////////////////////////////////////////

package fun;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

import ast.*;

public class FunParse {

	private static boolean tracing = false;

	private static PrintStream out = System.out;

	public static void main(String[] args) {
	// Compile a Fun source program to SVM code, 
	// then interpret it if it compiles successfully.
	// The source file name must be given as the 
	// first program argument.
		try {
			if (args.length == 0)
				throw new FunException();
			ParseTree tree =
			   syntacticAnalyse(args[0]);
		} catch (FunException x) {
			out.println("Compilation failed");
		} catch (Exception x) {
			x.printStackTrace(out);
		}
	}

	private static ParseTree syntacticAnalyse (String filename)
			throws Exception {
	// Perform syntactic analysis of a Fun source program.
	// Print any error messages.
	// Return a syntax tree representation of the Fun program.
		out.println();
		out.println("Syntactic analysis ...");
		FunLexer lexer = new FunLexer(
				CharStreams.fromFileName(filename));
		CommonTokenStream tokens = 
		   new CommonTokenStream(lexer);
		FunParser parser = new FunParser(tokens);
		ParseTree tree = parser.program();
		int errors = parser.getNumberOfSyntaxErrors();
		out.println(errors + " syntactic errors");
		if (errors > 0)
			throw new FunException();
		return tree;
	}

	private static class FunException extends Exception {
	}

}
