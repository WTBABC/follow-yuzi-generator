package com.yupi.cli;

import com.yupi.cli.command.ConfigCommand;
import com.yupi.cli.command.GenerateCommand;
import com.yupi.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(name = "yuzi", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;

    public CommandExecutor() {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {

    }

    public Integer doExecute(String[] args) {
        // 若无输入或只输入了命令没有选项时，自动补全 -h 提供命令提示
        if (args == null || args.length == 1 && args[0].equals("yuzi")) {
            args = new String[] {"yuzi", "-h"};
        }
        return commandLine.execute(args);
    }
}
