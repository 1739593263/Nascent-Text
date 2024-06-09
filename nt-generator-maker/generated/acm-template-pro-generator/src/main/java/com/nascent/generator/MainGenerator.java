package com.nascent.generator;

import java.io.File;
import com.nascent.generator.file.StaticFileGenerator;
import com.nascent.generator.file.DynamicFileGenerator;
import com.nascent.models.DataModel;


/**
* Core Generator
*/
public class MainGenerator {

    public static void doGenerate(DataModel model) throws Exception {
        String inputRootPath = ".source/acm-template";
        String outputRootPath = "generated";

        String inputPath;
        String outputPath;

        boolean gitNeeded = model.gitNeeded;
        boolean loop = model.loop;
        String author = model.mainTemplate.author;
        String outputText = model.mainTemplate.outputText;


        if (gitNeeded) {
            inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
            outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
            StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
            inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
            outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
            StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        }


        // src/com/yupi/acm/MainTemplate.java.ftl
        inputPath = new File(inputRootPath, "src/com/yupi/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/nascent/acm/MainTemplate.java").getAbsolutePath();
        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);

    }
}