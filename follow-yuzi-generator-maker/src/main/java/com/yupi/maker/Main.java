package com.yupi;

import cn.hutool.core.io.FileUtil;
import com.yupi.cli.CommandExecutor;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        args = new String[] {"generate", "-l", "-a", "-o"};
        args = new String[] {"generate"};
//        args = new String[] {"config"};
//        args = new String[] {"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
