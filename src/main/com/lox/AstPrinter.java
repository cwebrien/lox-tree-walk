package com.lox;

public class AstPrinter implements Expression.Visitor<String> {

    @Override
    public String visitGrouping(Expression.Grouping grouping) {
        return null;
    }

    @Override
    public String visitBinary(Expression.Binary binary) {
        return null;
    }

    @Override
    public String visitUnary(Expression.Unary unary) {
        return null;
    }

    @Override
    public String visitLiteral(Expression.Literal literal) {
        return null;
    }
}
