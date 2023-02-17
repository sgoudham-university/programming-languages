// Generated from src/ast/Calc.g4 by ANTLR 4.9.1

	package ast;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalcParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CalcParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code put}
	 * labeled alternative in {@link CalcParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPut(CalcParser.PutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code set}
	 * labeled alternative in {@link CalcParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CalcParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(CalcParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code num}
	 * labeled alternative in {@link CalcParser#prim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(CalcParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CalcParser#prim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CalcParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link CalcParser#prim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(CalcParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(CalcParser.VarContext ctx);
}