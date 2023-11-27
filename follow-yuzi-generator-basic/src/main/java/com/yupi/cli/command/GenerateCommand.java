package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.*;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-a", "--author"}, interactive = true, arity = "0..1", description = "作者", echo = true)
    private String author = "wtbabc";

    @Option(names = {"-l", "--loop"}, interactive = true, arity = "0..1", description = "是否循环", echo = true)
    private boolean loop = false;

    @Option(names = {"-o", "--outputText"}, interactive = true, arity = "0..1", description = "输出文本", echo = true)
    private String outputText = "结果：";

    @Override
    public Integer call() throws IOException, TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
