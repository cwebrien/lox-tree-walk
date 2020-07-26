package com.lox;

public class AstPrinter implements Expression.Visitor<String> {
    @Override
    public String visitGrouping(Expression.Grouping grouping) {
        return parenthesize("grouping", grouping.expression);
    }

    @Override
    public String visitBinary(Expression.Binary binary) {
        return parenthesize(binary.token.getLexeme(), binary.left, binary.right);
    }

    @Override
    public String visitUnary(Expression.Unary unary) {
        return parenthesize(unary.operator.getLexeme(), unary.right);
    }
    
    @Override
    public String visitLiteral(Expression.Literal literal) {
        if (literal.value == null) return "nil";
        return literal.value.toString();
    }

    /**
     * Prints out the expression using the visitor paradigm.
     * @param expression Expression to print
     * @return String representation
     */
    public String expressionAsString(Expression expression) {
        return expression.accept(this);
    }

    /**
     * Wraps an ellipsis of expressions in parentheses."
     * @param name Name of the expression category being wrapped
     * @param expressions One or more expressions
     * @return String representation
     */
    private String parenthesize(String name, Expression... expressions) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expression expression : expressions) {
            builder.append(" ");
            builder.append(expression.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    /**
     * Small program to demonstrate an example of the printer.
     * @param args Command line arguments -- not used
     */
    public static void main(String[] args) {
        Expression expression = new Expression.Binary(
                new Expression.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expression.Literal(3.14)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expression.Grouping(
                        new Expression.Literal(2.718)));

        System.out.println(new AstPrinter().expressionAsString(expression));
    }
}
