package calc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import ast.CalcBaseVisitor;
import ast.CalcParser;

public class ExecVisitor extends CalcBaseVisitor<Integer> {

    Map<String, Integer> store = new HashMap<String, Integer>();

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
        String address = ctx.var().ID().get(0).getText();
        store.put(address, value);
        return 0;
    }

    @Override
    public Integer visitOp(CalcParser.OpContext ctx) {
        List<CalcParser.PrimContext> prims = ctx.prim();
        List<Token> ops = ctx.operator;
        int value = visit(prims.get(0));
        int numTerms = prims.size();
        for (int i = 1; i < numTerms; i++) {
            switch (ops.get(i - 1).getType()) {
                case CalcParser.PLUS:
                    value = value + visit(prims.get(i));
                    break;
                case CalcParser.MINUS:
                    value = value - visit(prims.get(i));
                    break;
                case CalcParser.TIMES:
                    value = value * visit(prims.get(i));
                    break;
                case CalcParser.DIV:
                    value = value / visit(prims.get(i));
                    break;
                default:
                    throw new RuntimeException("OPERATOR: " + ops.get(i - 1).getType() + "NOT IMPLEMENTED");
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
        String address = ctx.ID().get(0).getText();
        return store.get(address);
    }

    @Override
    public Integer visitParens(CalcParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

}
