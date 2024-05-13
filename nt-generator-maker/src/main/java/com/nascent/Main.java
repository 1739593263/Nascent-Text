package com.nascent;

import com.nascent.generator.MainGenerator;

public class Main {
    public static void main(String[] args) throws Exception {
//        args = new String[]{"list"};
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);

//        Meta meta = MetaManager.getMeta();
//        System.out.println(meta);

        MainGenerator generator = new MainGenerator();
        generator.doGenerate();
    }
}
