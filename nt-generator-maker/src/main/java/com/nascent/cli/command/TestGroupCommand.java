package com.nascent.cli.command;

import com.nascent.models.DataModel;
import lombok.Data;
import picocli.CommandLine;

@CommandLine.Command(name = "test", mixinStandardHelpOptions = true)
public class TestGroupCommand implements Runnable {

    @CommandLine.Option(names = {"--gitNeeded"}, arity = "0..1", interactive = true, description = "whether or not add .gitignore", echo = true)
    private boolean gitNeeded = true;

    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, description = "whether or not add loop", echo = true)
    private boolean loop = true;

    static DataModel.MainTemplate mainTemplate = new DataModel.MainTemplate();

    @CommandLine.Command(name = "mainTemplate")
    @Data
    public static class MainTemplateCommand implements Runnable{

        @CommandLine.Option(names = {"-o", "--output"}, arity = "0..1", interactive = true, description = "output text", echo = true)
        private String outputText = "result = ";

        @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", interactive = true, description = "code author", echo = true)
        private String author = "anKen";

        @Override
        public void run() {
            mainTemplate.outputText = this.outputText;
            mainTemplate.author = this.author;
        }
    }
//    @Data
//    static class MainTemplate {
//        @CommandLine.Option(names = {"-mainTemplate.o", "--mainTemplate.output"}, arity = "0..1", interactive = true, description = "output text", echo = true)
//        private String outputText;
//
//        @CommandLine.Option(names = {"-mainTemplate.a", "--mainTemplate.author"}, arity = "0..1", interactive = true, description = "code author", echo = true)
//        private String author;
//    }

    @Override
    public void run() {
        System.out.println(gitNeeded);
        System.out.println(loop);
        if (true) {
            CommandLine commandLine = new CommandLine(MainTemplateCommand.class);
            commandLine.execute("-a", "-o");
            System.out.println(mainTemplate);
        }
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(TestGroupCommand.class);
        commandLine.execute("-l");
//        commandLine.execute("--help");
    }
}
