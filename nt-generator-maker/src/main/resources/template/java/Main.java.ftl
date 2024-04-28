package ${mainTemplate.basePackage};

import ${mainTemplate.basePackage}.cli.CommandExecutor;

public class Main {
    public static void main(String[] args) {
//        args = new String[]{"list"};
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
