package com.yupi.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.maker.generator.file.FileGenerator;
import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-a", "--author"}, interactive = true, arity = "0..1", description = "作者", echo = true, required = true)
    private String author = "wtbabc";

    @Option(names = {"-l", "--loop"}, interactive = true, arity = "0..1", description = "是否循环", echo = true, required = true)
    private boolean loop = false;

    @Option(names = {"-o", "--outputText"}, interactive = true, arity = "0..1", description = "输出文本", echo = true, required = true)
    private String outputText = "结果：";

    @Override
    public Integer call() throws IOException, TemplateException {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        System.out.println("配置信息：" + dataModel);
        FileGenerator.doGenerator(dataModel);
        return 0;
    }
}
