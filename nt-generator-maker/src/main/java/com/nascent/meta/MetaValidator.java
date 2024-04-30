package com.nascent.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.utility.CollectionUtils;
import freemarker.template.utility.StringUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        CoreInfoValidation(meta);

        FileConfigValidation(meta);

        ModelConfigValidation(meta);
    }

    private static void ModelConfigValidation(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig!=null) {
            List<Meta.ModelConfig.Models> models = modelConfig.getModels();

            if (models!=null) {
                for (Meta.ModelConfig.Models model:models) {
                    String fieldName = model.getFieldName();
                    if (StrUtil.isBlank(fieldName)) {
                        throw new MetaException("no fieldName detected");
                    }
                    String type = model.getType();
                    if (StrUtil.isBlank(type)) {
                        model.setType("String");
                    }
                }
            }
        }
    }

    private static void FileConfigValidation(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig!=null) {
            String sourceRootPath = fileConfig.getSourceRootPath();
            if (StrUtil.isBlank(sourceRootPath)) {
                throw new MetaException("no inputRootPath detected");
            }
            String inputRootPath = fileConfig.getInputRootPath();
            String defaultInputRootPath = ".source"+ File.separator +
                    FileUtil.getLastPathEle(Paths.get(inputRootPath)).getFileName().toString();
            if (StrUtil.isBlank(inputRootPath)) {
                fileConfig.setInputRootPath(defaultInputRootPath);
            }
            String outputRootPath = fileConfig.getOutputRootPath();
            String defaultOutputRootPath = "generated";
            if (StrUtil.isBlank(outputRootPath)) {
                fileConfig.setOutputRootPath(defaultOutputRootPath);
            }
            String fileConfigType = fileConfig.getFileConfigType();
            String defaultType = "dir";
            if (StrUtil.isBlank(fileConfigType)) {
                fileConfig.setFileConfigType(defaultType);
            }

            List<Meta.FileConfig.FilesInfo> files = fileConfig.getFiles();
            if (CollectionUtil.isNotEmpty(files)) {
                for (Meta.FileConfig.FilesInfo file: files) {
                    String inputPath = file.getInputPath();
                    if (StrUtil.isBlank(inputPath)) {
                        throw new MetaException("no InputPath detected");
                    }
                    String outputPath = file.getOutputPath();
                    if (StrUtil.isBlank(outputPath)) {
                        // default as input path;
                        file.setOutputPath(inputPath);
                    }
                    String type = file.getType();
                    if (StrUtil.isBlank(type)) {
                        if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                            file.setType("dir");
                        } else {
                            file.setType("file");
                        }
                    }
                    String generateType = file.getGenerateType();
                    if (StrUtil.isBlank(generateType)) {
                        if (inputPath.endsWith(".ftl")) {
                            file.setGenerateType("dynamic");
                        } else {
                            file.setGenerateType("statistic");
                        }
                    }
                }
            }
        }
    }

    private static void CoreInfoValidation(Meta meta) {
        String name = StrUtil.blankToDefault(meta.getName(), "acm-template-pro-generator");
        meta.setName(name);

        String description = StrUtil.blankToDefault(meta.getDescription(), "ACM template demo generator");
        meta.setName(description);

        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.nascent");
        meta.setName(basePackage);

        String version = StrUtil.blankToDefault(meta.getVersion(), "1.0");
        meta.setName(version);

        String author = StrUtil.blankToDefault(meta.getAuthor(), "anKen");
        meta.setName(author);

        String createTime = StrUtil.blankToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(createTime);
    }
}
