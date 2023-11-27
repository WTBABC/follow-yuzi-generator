package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";

        //创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("tjh");
        // 不循环
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");

        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    /**
     *
     * @param inputPath 模板文件路径
     * @param outputPath 生成文件输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 模板文件所在路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.toString());

        // 创建模板对象，加载模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
        template.process(model, out);

        out.close();
    }
}
