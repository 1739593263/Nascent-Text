package ${mainTemplate.basePackage}.cli.command;

import cn.hutool.core.util.ReflectUtil;
import ${mainTemplate.basePackage}.models.DataModel;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

@Command(name = "config", description = "data model configuration", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("check the configuration of used data model");

        Field[] fields = ReflectUtil.getFields(DataModel.class);

        for (Field field:fields) {
            System.out.println("Field Name: "+field.getName());
            System.out.println("Field Type"+field.getType());
        }
    }
}
