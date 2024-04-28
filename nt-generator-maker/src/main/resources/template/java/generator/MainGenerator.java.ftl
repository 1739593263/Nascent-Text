package ${mainTemplate.basePackage}.generator;

import java.io.File;
import ${mainTemplate.basePackage}.generator.file.StaticFileGenerator;
import ${mainTemplate.basePackage}.generator.file.DynamicFileGenerator;
/**
* Core Generator
*/
public class MainGenerator {

    public static void doGenerate(Object dataModel) throws Exception {
        String inputRootPath = "${mainTemplate.fileConfig.inputRootPath}";
        String outputRootPath = "${mainTemplate.fileConfig.outputRootPath}";
        String inputPath;
        String outputPath;
<#list mainTemplate.fileConfig.files as fileInfo>
        // ${fileInfo.inputPath}
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else>
        DynamicFileGenerator.doGenerate(inputPath, outputPath, dataModel);
    </#if>
</#list>
    }
}