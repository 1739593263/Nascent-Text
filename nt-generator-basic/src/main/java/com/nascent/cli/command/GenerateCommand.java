package com.nascent.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.nascent.generator.MainGenerator;
import com.nascent.models.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true, description = "Generate Code")
@Data
public class GenerateCommand implements Callable {


    @Option(names = {"-o", "--output"}, arity = "0..1", interactive = true, description = "output text")
    private String outputText;

    @Option(names = {"-a", "--author"}, arity = "0..1", interactive = true, description = "code author")
    private String author;

    @Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, description = "whether or not add loop")
    private boolean loop;

    @Override
    public Object call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("Conf: "+mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
