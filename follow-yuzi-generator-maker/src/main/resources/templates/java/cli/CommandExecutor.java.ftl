package com.yupi.maker.cli;

import com.yupi.maker.cli.command.ConfigCommand;
import com.yupi.maker.cli.command.GenerateCommand;
import com.yupi.maker.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

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
        return commandLine.execute(args);
    }
}
