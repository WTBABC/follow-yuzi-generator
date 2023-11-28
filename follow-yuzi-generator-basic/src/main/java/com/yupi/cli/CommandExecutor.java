package com.yupi.cli;

import com.yupi.cli.command.ConfigCommand;
import com.yupi.cli.command.GenerateCommand;
import com.yupi.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Command(name = "yuzi", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;

    public CommandExecutor() {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }


    // 利用反射，强制交互选项必填
    public String[] fillArgs(String[] args) {
        // 记录 @Option required=true 的属性
        List<String> array = Arrays.asList(args);
        List<String> ret = new ArrayList<>(array);

        Class<GenerateCommand> commandClass = GenerateCommand.class;

        // 遍历每个属性
        for (Field field : commandClass.getDeclaredFields()) {
            Option option = field.getAnnotation(Option.class);
            // 记录有 @Option 的且 interactive 为 true 的属性对应的选项名
            if (option != null) {
                if (option.required()) {
                    String opName = option.names()[0];
                    // 用户输入中无该选项，才加入
                    if (!ret.contains(opName)) {
                        ret.add(opName);
                    }
                }
            }
        }

        String[] retOptions = new String[ret.size()];
        return ret.toArray(retOptions);
    }

    @Override
    public void run() {

    }

    public Integer doExecute(String[] args) {
        // 若无输入或只输入了命令没有选项时，自动补全 -h 提供命令提示
        if (args == null || args.length == 1 && args[0].equals("yuzi")) {
            args = new String[] {"yuzi", "-h"};
        }
        if (args != null && args[0].equals("generate")) {
            args = this.fillArgs(args);
        }
        return commandLine.execute(args);
    }
}
