package com.nascent.cli.command;

import lombok.Data;
import picocli.CommandLine;

@CommandLine.Command(name = "test", mixinStandardHelpOptions = true)
public class TestArgGroupCommand implements Runnable {

    @CommandLine.Option(names = {"--gitNeeded"}, arity = "0..1", interactive = true, description = "whether or not add .gitignore", echo = true)
    private boolean gitNeeded = true;

    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, description = "whether or not add loop", echo = true)
    private boolean loop = true;

    @CommandLine.ArgGroup(exclusive = false, heading = "main template")
    MainTemplate mainTemplate;

    @Data
    static class MainTemplate {
        @CommandLine.Option(names = {"-mainTemplate.o", "--mainTemplate.output"}, arity = "0..1", interactive = true, description = "output text", echo = true)
        private String outputText;

        @CommandLine.Option(names = {"-mainTemplate.a", "--mainTemplate.author"}, arity = "0..1", interactive = true, description = "code author", echo = true)
        private String author;
    }

    @Override
    public void run() {
        System.out.println(gitNeeded);
        System.out.println(loop);
        System.out.println(mainTemplate);
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(TestArgGroupCommand.class);
//        commandLine.execute("-l", "-mainTemplate.o", "--mainTemplate.author");
        commandLine.execute("--help");
    }
}
