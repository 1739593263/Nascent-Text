package com.nascent.generator;

import cn.hutool.core.io.FileUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class ScriptGenerator {
    public static void doGenerator(String outputPath, String jarPath) {
        // linux
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash");
        sb.append("\n");
        sb.append("java -jar " + jarPath + " \"$@\"");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);
        // linux permission
        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {

        }


        // windows
        sb= new StringBuilder();
        sb.append("@echo off");
        sb.append("\n");
        sb.append("java -jar " + jarPath + " %*");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath+".bat");
    }

}
