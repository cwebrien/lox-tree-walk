package com.lox;

import java.io.IOException;

public class Lox
{
    public static void main(String[] args) throws IOException
    {
        if (args.length > 1)
        {
            System.out.println("Usage: jlox [script_to_execute]");
            System.exit(64);
        }
        else if (args.length == 1) // run a script
        {
            runFile(args[0]);
        }
        else // run in interactive mode
        {
            runPrompt();
        }
    }

    public static void runFile(String s)
    {

    }

    public static void runPrompt()
    {

    }

}