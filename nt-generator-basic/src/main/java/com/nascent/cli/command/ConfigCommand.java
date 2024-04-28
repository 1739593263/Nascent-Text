package com.nascent.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.nascent.models.MainTemplateConfig;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.lang.reflect.Field;

@Command(name = "config", description = "data model configuration", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("check the configuration of used data model");

        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field:fields) {
            System.out.println("Field Name: "+field.getName());
            System.out.println("Field Type"+field.getType());
        }
    }
}
