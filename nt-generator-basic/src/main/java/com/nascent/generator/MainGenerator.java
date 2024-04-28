package com.nascent.generator;

import com.nascent.generator.file.DynamicGenerator;
import com.nascent.generator.file.StaticGenerator;
import com.nascent.models.MainTemplateConfig;

import java.io.File;

public class MainGenerator {
    public static void main(String[] args) throws Exception {
        doGenerate(new MainTemplateConfig());
    }

    public static void doGenerate(Object dataModel) throws Exception {
        String inputPath = System.getProperty("user.dir")+File.separator+"src/main/resources/templates";
        String outputPath = System.getProperty("user.dir")+"/MainTemplate.java";

        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        DynamicGenerator.doGenerate(inputPath, outputPath, dataModel);
    }
}
