package com.lox.tool;

import java.io.IOException;

public class AstGenerator {
    /**
     * Generates Java class source for abstract syntax trees into the specified output directory.
     * @param args A single argument, the output directory for the .java source files
     * @throws IOException If we cannot successfully write the files
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output_directory>");
            System.exit(64);
        }
        String outputDir = args[0];
    }
}
