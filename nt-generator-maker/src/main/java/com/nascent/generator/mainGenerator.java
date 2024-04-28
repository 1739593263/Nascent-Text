package com.nascent.generator;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.nascent.generator.file.DynamicFileGenerator;
import com.nascent.meta.Meta;
import com.nascent.meta.MetaManager;
import freemarker.template.utility.StringUtil;

import java.io.File;

public class mainGenerator {

    public static void main(String[] args) throws Exception {
        Meta meta = new Meta();
        meta = MetaManager.getMeta();
        System.out.println(meta);
        // output path
        String rootPath = System.getProperty("user.dir");
        String outputPath = rootPath+ File.separator+"generated";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // read the resource dir
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // Java base Path
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, '.'));
        String outputBaseStringPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // data model
        inputFilePath = inputResourcePath + File.separator + "template/java/models/DataModel.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "models/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli/command/ConfigCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli/command/GenerateCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli/command/ListCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli/CommandExecutor.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main.java
        inputFilePath = inputResourcePath + File.separator + "template/java/Main.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator/file/DynamicFileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "template/java/generator/file/DynamicFileGenerator.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "generator/file/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator/file/StaticFileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "template/java/generator/file/StaticFileGenerator.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "generator/file/StaticFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator/file/FileGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "template/java/generator/file/FileGenerator.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "generator/file/FileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator/MainGenerator.java.ftl
        inputFilePath = inputResourcePath + File.separator + "template/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseStringPackagePath + File.separator + "generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml.ftl
        inputFilePath = inputResourcePath + File.separator + "template/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // build jars
        jarGenerator.doGenerator(outputPath);

        // script
        String shellOutputPath = outputPath+"/generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target" + File.separator + jarName;
        scriptGenerator.doGenerator(shellOutputPath, jarPath);
    }

}
