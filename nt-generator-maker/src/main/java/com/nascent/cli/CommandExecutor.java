package com.nascent.cli;

import com.nascent.cli.command.ConfigCommand;
import com.nascent.cli.command.GenerateCommand;
import com.nascent.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "adam", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this).addSubcommand(new ConfigCommand())
                                           .addSubcommand(new GenerateCommand())
                                           .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("Please Input command or send --help to check the manual");
    }

    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }

}
