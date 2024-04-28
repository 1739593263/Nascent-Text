package com.nascent.generator.file;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.file.Paths;

public class StaticGenerator {

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        String parentPath = String.valueOf(Paths.get(rootPath).getParent());
        String filePath = parentPath + File.separator + "nt-demo-projects"+ File.separator+"acm-template";
        String sre = filePath;
        String des = rootPath;
//        System.out.println(parentPath);
        System.out.println(sre);
        System.out.println(des);
        copyFilesByHutool(sre, des);
    }

    public static void copyFilesByHutool(String sre, String des) {
        FileUtil.copy(sre, des, false);
    }
}
