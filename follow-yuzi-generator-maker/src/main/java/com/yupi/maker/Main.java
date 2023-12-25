package com.yupi.maker;

//import com.yupi.maker.cli.CommandExecutor;

import com.yupi.maker.generator.main.MainGenerator;
import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
//        args = new String[] {"generate", "-l", "-a", "-o"};
//        args = new String[] {"generate"};
//        args = new String[] {"config"};
//        args = new String[] {"list"};
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("wtbabc");
        dataModel.setLoop(true);
        dataModel.setOutputText("结果：");
        MainGenerator.doGenerate(dataModel);
    }
}
