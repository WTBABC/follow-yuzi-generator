package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        // follow-yuzi-generator
        File parentFile = new File(projectPath).getParentFile();
        // 静态文件路径
        String inputPath = new File(parentFile, "follow-yuzi-generator-demo-projects\\acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByReCurSion(inputPath, outputPath);

        //生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template\\src\\com\\yupi\\acm\\MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        //创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("tjh");
        // 不循环
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");

        doGenerator(mainTemplateConfig);
    }
}
