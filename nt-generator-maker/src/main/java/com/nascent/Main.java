package com.nascent;

import com.nascent.meta.Meta;
import com.nascent.meta.MetaManager;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
//        args = new String[]{"list"};
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);

        Meta meta = MetaManager.getMeta();
        System.out.println(meta);

//        String rootPath = System.getProperty("user.dir");
//        String parentPath = String.valueOf(Paths.get(rootPath).getParent());
//        String filePath = parentPath + File.separator + "nt-demo-projects"+ File.separator+"acm-template";
//
//        String inputPath = filePath;
//        String outputPath = System.getProperty("user.dir")+"/MainTemplate.java";
//        System.out.println(inputPath +"\n" + outputPath);
    }
}
