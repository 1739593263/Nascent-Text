package com.nascent.cli;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;



@Command(name = "ASCIIArt", mixinStandardHelpOptions = true, version = "ASCIIArt 1.0",
        description = "ASCII art interpreter")
public class ASCIIArt implements Runnable {

    @Parameters(paramLabel = "<word>", defaultValue = "hello, Kun", description = "Words to be translated into ascii arts")
    private String[] word;

    @Option(names = {"-s", "--size"}, description = "font size")
    private int fontSize = 19;

    @Override
    public void run() { // your business logic goes here...
        System.out.println("font size: "+fontSize);
        System.out.println("Component: "+String.join(",", word));
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String[] args) {
//        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
//        System.exit(exitCode);
        new CommandLine(new ASCIIArt()).execute(args);
    }
}