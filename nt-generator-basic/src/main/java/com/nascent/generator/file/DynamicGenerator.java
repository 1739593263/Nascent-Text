package com.nascent.generator.file;

import com.nascent.models.MainTemplateConfig;
import com.nascent.models.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DynamicGenerator {
    public static void main(String[] args) throws Exception {
        String inputPath = System.getProperty("user.dir")+File.separator+"src/main/resources/templates";
        String outputPath = System.getProperty("user.dir")+"/MainTemplate.java";

        MainTemplateConfig dataModel = new MainTemplateConfig();
        dataModel.setAuthor("KK");
        dataModel.setLoop(true);
        dataModel.setOutputText("SUM: ");
        doGenerate(inputPath, outputPath, dataModel);
    }

    public static void doGenerate(String inputPath, String outputPath, Object dataModel) throws Exception {
        // Configuration
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDirectoryForTemplateLoading(new File(inputPath));
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
        Template temp = cfg.getTemplate("MainTemplate.java.ftl");

        // write out
        Writer out = new FileWriter(outputPath);
        temp.process(root, out);
        out.close();
    }
}
