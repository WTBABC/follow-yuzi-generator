package ${basePackage}.generator;

import ${basePackage}.generator.DynamicFileGenerator;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    /**
     * 
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

<#list fileConfig.files as fileInfo>
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "dynamic">
        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
    <#else>
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    </#if>
</#list>
    }
}
