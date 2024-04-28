package com.nascent.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "core", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {

    @Command(name = "add", description = "ADD", mixinStandardHelpOptions = true)
    static class doAddCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("do add command");
        }
    }

    @Command(name = "delete", description = "DELETE", mixinStandardHelpOptions = true)
    static class doDeleteCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("do delete command");
        }
    }

    @Command(name = "update", description = "UPDATE", mixinStandardHelpOptions = true)
    static class doUpdateCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("do update command");
        }
    }

    @Override
    public void run() {
        System.out.println("Run Core Program");
    }

    public static void main(String[] args) {
        String[] cmds = new String[]{"--help"};
        int exitCode = new CommandLine(new SubCommandExample())
                .addSubcommand(new doAddCommand())
                .addSubcommand(new doDeleteCommand())
                .addSubcommand(new doUpdateCommand())
                .execute(cmds);
        System.exit(exitCode);
    }
}
