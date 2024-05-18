package ${mainTemplate.basePackage}.generator;

import java.io.File;
import ${mainTemplate.basePackage}.generator.file.StaticFileGenerator;
import ${mainTemplate.basePackage}.generator.file.DynamicFileGenerator;
import ${mainTemplate.basePackage}.models.DataModel;
/**
* Core Generator
*/
public class MainGenerator {

    public static void doGenerate(DataModel model) throws Exception {
        String inputRootPath = "${mainTemplate.fileConfig.inputRootPath}";
        String outputRootPath = "${mainTemplate.fileConfig.outputRootPath}";
        String inputPath;
        String outputPath;

<#list mainTemplate.modelConfig.models as modelInfo>
        ${modelInfo.type} ${modelInfo.fieldName} = model.${modelInfo.fieldName};
</#list>

<#list mainTemplate.fileConfig.files as fileInfo>
    <#if fileInfo.condition??>
        if (${fileInfo.condition}) {
            inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
            outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        <#if fileInfo.generateType == "static">
            StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        <#else>
            DynamicFileGenerator.doGenerate(inputPath, outputPath, dataModel);
        </#if>
        }
    <#else>
        // ${fileInfo.inputPath}
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else>
        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
    </#if>
    </#if>
</#list>
    }
}