package com.yupi.maker.generator.file;

import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {

    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        // follow-yuzi-generator
        File parentFile = new File(projectPath).getParentFile();
        // 静态文件路径
        String inputPath = new File(parentFile, "follow-yuzi-generator-demo-projects\\acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        //生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template\\src\\com\\yupi\\acm\\MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        //创建数据模型
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("tjh");
        // 不循环
        dataModel.setLoop(true);
        dataModel.setOutputText("求和结果：");

        doGenerator(dataModel);
    }
}
