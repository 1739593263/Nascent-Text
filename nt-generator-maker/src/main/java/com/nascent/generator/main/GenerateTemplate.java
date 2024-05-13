package com.nascent.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.nascent.generator.JarGenerator;
import com.nascent.generator.ScriptGenerator;
import com.nascent.meta.Meta;
import com.nascent.meta.MetaManager;
import com.nascent.generator.file.DynamicFileGenerator;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {
    public void doGenerate() throws Exception {
        Meta meta = new Meta();
        meta = MetaManager.getMeta();
        System.out.println(meta);
        // output path
        String rootPath = System.getProperty("user.dir");
        String outputPath = rootPath+ File.separator+"generated"+ File.separator+"acm-template-pro-generator";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // copy the original file
        String sourceCopyDesPath = copySource(meta, outputPath);

        // generate code
        generateCode(meta, outputPath);

        // build jars
        String jarPath = buildJar(meta, outputPath);

        // script
        String shellOutputPath = buildScript(jarPath, outputPath);


        // light version
        buildLight(outputPath, sourceCopyDesPath, jarPath, shellOutputPath);
    }

    protected void buildLight(String outputPath, String sourceCopyDesPath, String jarPath, String shellOutputPath) {
        String distOutputPath = outputPath + "-dist";
        // - copy jar packages
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath +File.separator+jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        // -copy script
        FileUtil.copy(shellOutputPath, distOutputPath, true);
        FileUtil.copy(shellOutputPath+".bat", distOutputPath, true);
        // -copy source Template files
        FileUtil.copy(sourceCopyDesPath, distOutputPath, true);
    }

    protected String buildScript(String jarPath, String outputPath) {
        String shellOutputPath =  outputPath+"/generator";
        ScriptGenerator.doGenerator(shellOutputPath, jarPath);
        return shellOutputPath;
    }

    protected String buildJar(Meta meta, String outputPath) throws IOException, InterruptedException {
        JarGenerator.doGenerator(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target" + File.separator + jarName;
        return jarPath;
    }

    protected void generateCode(Meta meta, String outputPath) throws Exception {
        // read the resou
        // rce dir
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

        // README.md
        inputFilePath = inputResourcePath + File.separator + "template/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDesPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDesPath, false);
        return sourceCopyDesPath;
    }
}
