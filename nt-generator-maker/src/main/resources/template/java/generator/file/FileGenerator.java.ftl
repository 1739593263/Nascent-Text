package ${mainTemplate.basePackage}.generator.file;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.file.Paths;

public class FileGenerator {
    public static void doGenerate(Object dataModel) throws Exception {
        String rootPath = System.getProperty("user.dir");
        String parentPath = String.valueOf(Paths.get(rootPath).getParent().getParent());
        String filePath = rootPath + File.separator + ".source"+ File.separator+"acm-template";

        String inputPath = filePath + "/src/com/yupi/acm/MainTemplate.java.ftl";
        String outputPath = System.getProperty("user.dir")+"/MainTemplate.java";

        System.out.println("Copying " + inputPath+" to "+outputPath);
        // dynamic
        DynamicFileGenerator.doGenerate(inputPath, outputPath, dataModel);
        // static
<#--        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);-->
    }
}
