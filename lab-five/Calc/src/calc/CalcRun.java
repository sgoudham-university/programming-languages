package calc;

import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import ast.*;

public class CalcRun {

	public static void main(String[] args) throws Exception {
		InputStream source = new FileInputStream(args[0]);
		CalcLexer lexer = new CalcLexer(
			CharStreams.fromFileName(args[0]));
		CommonTokenStream tokens = 
		   new CommonTokenStream(lexer);
		CalcParser parser = new CalcParser(tokens);
		ParseTree tree = parser.prog();
		if (parser.getNumberOfSyntaxErrors() == 0) {
			ExecVisitor exec = new ExecVisitor();
			exec.visit(tree);
		}
	}

}