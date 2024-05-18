package com.nascent.generator;

import java.io.*;

public class JarGenerator {
    public static void doGenerator(String dir) throws IOException, InterruptedException {
        String winMvnCommand = "mvn.cmd clean package -DskipTests=true;";
        String otherMvnCommand = "mvn clean package -DskipTests=true;";
        String mainMvnCommand = winMvnCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mainMvnCommand.split(" "));
        processBuilder.directory(new File(dir));

        Process process = processBuilder.start();

        // read command by inputStream
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("exit code: " + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        doGenerator("E:\\Projects\\CodeGenerator\\nt-generator-basic");
        doGenerator("E:\\Projects\\CodeGenerator\\nt-generator-maker\\generated\\acm-template-pro-generator");
    }
}
