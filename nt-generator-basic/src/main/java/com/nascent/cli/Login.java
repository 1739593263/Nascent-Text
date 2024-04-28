package com.nascent.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

/**
 * Interactive CLI
 */
@Command(name = "Login", mixinStandardHelpOptions = true, version = "Login 1.0",
        description = "Login mini Program by user & password")
public class Login implements Callable {
    @Option(names = {"-u", "--user"}, defaultValue = "", description = "User Name")
    private String user;

    @Option(names = {"-p", "--pwd"}, description = "password", arity = "0..1", interactive = true)
    private String password;

    @Option(names = {"-cp", "--checkpwd"}, description = "check password", arity = "0..1", interactive = true)
    private String checkPassword;

    @Override
    public Object call() throws Exception {
        System.out.println("user = "+user);
        System.out.println("password = "+password);
        System.out.println("check_password = "+checkPassword);
        return null;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user", "-p", "-cp");
    }
}
