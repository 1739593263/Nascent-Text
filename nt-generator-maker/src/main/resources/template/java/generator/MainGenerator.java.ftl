package ${mainTemplate.basePackage}.generator;

import java.io.File;
import ${mainTemplate.basePackage}.generator.file.StaticFileGenerator;
import ${mainTemplate.basePackage}.generator.file.DynamicFileGenerator;
import ${mainTemplate.basePackage}.models.DataModel;

<#macro generatefile indent fileInfo>
${indent}inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
${indent}outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
<#if fileInfo.generateType == "static">
${indent}StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
<#else>
${indent}DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
</#if>
</#macro>

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
    <#if fileInfo.groupKey??>

    <#if fileInfo.condition??>
        if (${fileInfo.condition}) {
            <#list fileInfo.files as file>
            <@generatefile fileInfo=file indent="            "/>
            </#list>
        }
    <#else>
        <#list fileInfo.files as file>
        // ${fileInfo.inputPath}
        <@generatefile fileInfo=file indent="        "/>
        </#list>
    </#if>

    <#else>

    <#if fileInfo.condition??>
        if (${fileInfo.condition}) {
        <@generatefile fileInfo=fileInfo indent="            "/>
        }
        <#else>
        // ${fileInfo.inputPath}
        <@generatefile fileInfo=fileInfo indent="        "/>
    </#if>

    </#if>
</#list>
    }
}