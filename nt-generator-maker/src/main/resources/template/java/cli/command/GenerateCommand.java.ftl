package ${mainTemplate.basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${mainTemplate.basePackage}.generator.file.FileGenerator;
import ${mainTemplate.basePackage}.generator.MainGenerator;
import ${mainTemplate.basePackage}.models.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

<#-- Generating Options -->
<#macro generateOptions indent modelInfo>
${indent}@Option(names = {"-${modelInfo.abbr}", "--${modelInfo.fieldName}"}, arity = "0..1", interactive = true, description = "${modelInfo.description}", echo = true)
${indent}private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#macro>

<#-- Generating Commands executions-->
<#macro generateCommands indent modelInfo>
${indent}System.out.println("Inputting ${modelInfo.groupName}'s Conf: ");
${indent}CommandLine commandLine = new CommandLine(${modelInfo.type}Command.class);
${indent}commandLine.execute(${modelInfo.allArgStr});
</#macro>

@Command(name = "generate", mixinStandardHelpOptions = true, description = "Generate Code")
@Data
public class GenerateCommand implements Callable<Integer> {

<#list mainTemplate.modelConfig.models as modelInfo>
    <#-- Group Type -->
    <#if modelInfo.groupKey??>
    /**
     * ${modelInfo.groupName}
     */
    static DataModel.${modelInfo.type} ${modelInfo.groupKey} = new DataModel.${modelInfo.type}();

    <#-- Generating Options -->
    @Command(name = "${modelInfo.groupKey}")
    @Data
    public static class ${modelInfo.type}Command implements Runnable {
    <#list modelInfo.models as subModelInfo>
    <@generateOptions indent="    " modelInfo=subModelInfo/>
    </#list>

        @Override
        public void run() {
        <#list modelInfo.models as subModelInfo>
            ${modelInfo.groupKey}.${subModelInfo.fieldName} = this.${subModelInfo.fieldName};
        </#list>
        }
    }
    <#else>
    <@generateOptions indent="         " modelInfo=modelInfo/>
    </#if>
</#list>

    <#-- Generating Commands -->
    @Override
    public Integer call() throws Exception {
        <#list mainTemplate.modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
        <#if modelInfo.condition??>
        if (${modelInfo.condition}) {
            <@generateCommands indent="        " modelInfo=modelInfo/>
        }
        <#else>
        <@generateCommands indent="        " modelInfo=modelInfo/>
        </#if>
        </#if>
        </#list>

        <#-- Fill dataModel -->
        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        <#list mainTemplate.modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
        mainTemplateConfig.${modelInfo.groupKey} = ${modelInfo.groupKey};
        </#if>
        </#list>
        System.out.println("Conf: "+mainTemplateConfig);
        // FileGenerator.doGenerate(mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
