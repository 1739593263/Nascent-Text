package ${mainTemplate.basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "list", description = "check the file list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        String rootPath = new File(projectPath).getParent();
        String inputPath = new File(rootPath, "nt-demo-projects/acm-template").getAbsolutePath();

        List<File> fileList = FileUtil.loopFiles(inputPath);

        for (File file:fileList) {
            System.out.println(file);
        }
    }
}
