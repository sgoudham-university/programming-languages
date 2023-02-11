package calc;

import org.antlr.v4.runtime.Token;
import ast.*;

import java.util.List;

public class ExecVisitor extends CalcBaseVisitor<Integer> {

    int[] store = new int[26];

	@Override 
	public Integer visitProg(CalcParser.ProgContext ctx) { 
	    return visitChildren(ctx); 
	}

	@Override 
	public Integer visitPut(CalcParser.PutContext ctx) { 
	    int value = visit(ctx.expr());
	    System.out.println(value);
	    return 0;
	}

	@Override 
	public Integer visitSet(CalcParser.SetContext ctx) { 
	    int value = visit(ctx.expr()); 
	    int address = ctx.var().ID().getText().charAt(0) - 'a'; 
            store[address] = value;
	    return 0;
	}

	@Override 
	public Integer visitOp(CalcParser.OpContext ctx) { 
	    List<CalcParser.PrimContext> prims = ctx.prim();
	    List<Token> ops = ctx.operator;
	    int value = visit(prims.get(0));
	    int numTerms = prims.size();
	    for (int i = 1; i < numTerms; i++) {
 		switch (ops.get(i-1).getType()) {
		case CalcParser.PLUS:
		    value = value + visit(prims.get(i));
		    break;
		case CalcParser.MINUS:
		    value = value - visit(prims.get(i));
		    break;
		case CalcParser.TIMES:
		    value = value * visit(prims.get(i));
		    break;
		default:
		}
	    }
	    return value;
	}

	@Override 
	public Integer visitNum(CalcParser.NumContext ctx) { 
	    return Integer.valueOf(ctx.NUM().getText()); 
	}

	@Override 
	public Integer visitId(CalcParser.IdContext ctx) { 
	    int address = ctx.ID().getText().charAt(0) - 'a';
	    return store[address]; 
	}

	@Override 
	public Integer visitParens(CalcParser.ParensContext ctx) { 
	    return visit(ctx.expr()); 
	}

}
