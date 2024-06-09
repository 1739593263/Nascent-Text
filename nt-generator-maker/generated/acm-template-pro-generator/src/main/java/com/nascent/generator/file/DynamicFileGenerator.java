package com.nascent.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DynamicFileGenerator {

    public static void doGenerate(String inputPath, String outputPath, Object dataModel) throws Exception {
        String inputFolder = new File(inputPath).getParent();
        String inputFile = new File(inputPath).getName();
        // Configuration
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File(inputFolder));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setNumberFormat("0.######");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());

        // data model
        Map<String, Object> root = new HashMap<>();
        root.put("mainTemplate", dataModel);

        // get template
        Template temp = cfg.getTemplate(inputFile);

        // check if its valid path
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        // write out
        Writer out = new FileWriter(outputPath);
        temp.process(root, out);
        out.close();
    }
}
