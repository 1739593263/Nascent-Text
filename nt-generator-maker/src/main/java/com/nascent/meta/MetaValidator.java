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
        String name = meta.getName();
        if (StrUtil.isBlank(name)) {
            name = "acm-template-pro-generator";
            meta.setName(name);
        }
        String description = meta.getDescription();
        if (StrUtil.isBlank(description)) {
            description = "ACM template demo generator";
            meta.setName(description);
        }
        String basePackage = meta.getBasePackage();
        if (StrUtil.isBlank(basePackage)) {
            basePackage = "com.nascent";
            meta.setName(basePackage);
        }
        String version = meta.getVersion();
        if (StrUtil.isBlank(version)) {
            version = "1.0.0";
            meta.setName(version);
        }
        String author = meta.getAuthor();
        if (StrUtil.isBlank(author)) {
            author = "anKen";
            meta.setName(author);
        }
        String createTime = meta.getCreateTime();
        if (StrUtil.isBlank(createTime)) {
            createTime = DateUtil.now();
            meta.setName(createTime);
        }

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
}
