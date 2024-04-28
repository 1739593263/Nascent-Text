package com.nascent.generator.file;

import java.io.File;

public class FileGenerator {
    public static void doGenerate(Object dataModel) throws Exception {
        String inputPath = System.getProperty("user.dir")+File.separator+"src/main/resources/templates";
        String outputPath = System.getProperty("user.dir")+"/MainTemplate.java";
        // dynamic
        DynamicFileGenerator.doGenerate(inputPath, outputPath, dataModel);
        // static
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    }
}
