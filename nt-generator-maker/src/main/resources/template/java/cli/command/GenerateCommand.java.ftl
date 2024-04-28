package ${mainTemplate.basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${mainTemplate.basePackage}.generator.file.FileGenerator;
import ${mainTemplate.basePackage}.models.DataModel;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true, description = "Generate Code")
@Data
public class GenerateCommand implements Callable {

<#list mainTemplate.modelConfig.models as modelInfo>
    @Option(names = {"-${modelInfo.abbr}", "--${modelInfo.fieldName}"}, arity = "0..1", interactive = true, description = "${modelInfo.description}")
    private ${modelInfo.type} ${modelInfo.fieldName};
</#list>

    @Override
    public Object call() throws Exception {
        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("Conf: "+mainTemplateConfig);
        FileGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
