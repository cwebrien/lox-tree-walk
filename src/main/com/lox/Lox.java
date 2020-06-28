package com.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lox {
    /**
     * Main loop for our interpreter. Supports scripts and a REPL.
     * @param args Commandline arguments
     * @throws IOException Throws exception if we fail to handle execution
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 1){
            System.out.println("Usage: jlox [script_to_execute]");
            System.exit(64);
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

}