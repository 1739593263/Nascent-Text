package com.nascent.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.nascent.generator.file.FileGenerator;
import com.nascent.generator.MainGenerator;
import com.nascent.models.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


@Command(name = "generate", mixinStandardHelpOptions = true, description = "Generate Code")
@Data
public class GenerateCommand implements Callable<Integer> {

         @Option(names = {"-gn", "--gitNeeded"}, arity = "0..1", interactive = true, description = "if generating .gitignore file", echo = true)
         private boolean gitNeeded = true;
         @Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, description = "Loop or not", echo = true)
         private boolean loop = false;
    /**
     * Main Template
     */
    static DataModel.MainTemplate mainTemplate = new DataModel.MainTemplate();

    @Command(name = "mainTemplate")
    @Data
    public static class MainTemplateCommand implements Runnable {
    @Option(names = {"-a", "--author"}, arity = "0..1", interactive = true, description = "Author annotation", echo = true)
    private String author = "aNDre";
    @Option(names = {"-o", "--outputText"}, arity = "0..1", interactive = true, description = "output text", echo = true)
    private String outputText = "Code result: ";

        @Override
        public void run() {
            mainTemplate.author = this.author;
            mainTemplate.outputText = this.outputText;
        }
    }

    @Override
    public Integer call() throws Exception {
        if (loop) {
        System.out.println("Inputting Main Template's Conf: ");
        CommandLine commandLine = new CommandLine(MainTemplateCommand.class);
        commandLine.execute("--author", "--outputText");
        }

        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        mainTemplateConfig.mainTemplate = mainTemplate;
        System.out.println("Conf: "+mainTemplateConfig);
        // FileGenerator.doGenerate(mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
