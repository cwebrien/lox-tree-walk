package com.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lox {
    private static boolean encounteredError = false;

    // Inspired by sysexits.h
    private static final int EX_USAGE = 64;
    private static final int EX_DATAERR = 65;

    /**
     * Main loop for our interpreter. Supports scripts and a REPL.
     * @param args Commandline arguments
     * @throws IOException Throws exception if we fail to handle execution
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 1){
            System.out.println("Usage: jlox [script_to_execute]");
            System.exit(EX_USAGE);
        }
        else if (args.length == 1) { // run a script
            runFile(args[0]);
        }
        else { // run in interactive mode
            runPrompt();
        }
    }

    /**
     * Reads in a script and executes it.
     * @param path Fully qualified path to the script
     * @throws IOException If we fail to read the script
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (encounteredError) {
            System.exit(EX_DATAERR);
        }
    }

    /**
     * Interactive REPL mode. Reads commands line by line and executes them.
     * @throws IOException If we fail to read from stdin
     */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line != null) {
                run(line);
                encounteredError = false; // toggle off errors since we don't want to kill the REPL
            }
            else {
                done = true;
            }
        }
    }

    /**
     * Execute lox code.
     * @param toExec Code to execute (may be a single line or an entire script)
     */
    private static void run(String toExec) {

    }

    /**
     * Report an error on a particular line of execution.
     * @param line The line number
     * @param message Error message
     */
    public static void error(int line, String message) {
        reportError(line, "", message);
        encounteredError = true;
    }

    /**
     * Reports an error event on a line.
     * @param line The line number
     * @param where File location of error
     * @param message Error message
     */
    private static void reportError(int line, String where, String message) {
        System.err.println("[line " + line + "] Error " + where + ": " + message);
    }
}
