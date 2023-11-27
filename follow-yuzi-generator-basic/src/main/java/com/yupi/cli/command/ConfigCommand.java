package com.yupi.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yupi.model.MainTemplateConfig;
import picocli.CommandLine.*;

import java.lang.reflect.Field;

@Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("查看参数信息");

        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("---");
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
        }
    }
}
