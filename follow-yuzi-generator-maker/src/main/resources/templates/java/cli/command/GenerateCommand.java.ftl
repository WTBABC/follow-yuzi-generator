package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.FileGenerator;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

<#list modelConfig.models as model>
    @Option(names = {<#if model.abbr??>"-${model.abbr}",</#if> "--${model.fieldName}"}, interactive = true, arity = "0..1", <#if model.description??>description = "${model.description}", </#if>echo = true, required = true)
    private ${model.type} ${model.fieldName}<#if model.defaultValue??> = ${model.defaultValue?c}</#if>;
</#list>

    @Override
    public Integer call() throws IOException, TemplateException {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        System.out.println("配置信息：" + dataModel);
        FileGenerator.doGenerate(dataModel);
        return 0;
    }
}
