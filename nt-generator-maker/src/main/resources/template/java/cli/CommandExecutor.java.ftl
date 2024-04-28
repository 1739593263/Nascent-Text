package ${mainTemplate.basePackage}.cli;

import ${mainTemplate.basePackage}.cli.command.ConfigCommand;
import ${mainTemplate.basePackage}.cli.command.GenerateCommand;
import ${mainTemplate.basePackage}.cli.command.ListCommand;
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