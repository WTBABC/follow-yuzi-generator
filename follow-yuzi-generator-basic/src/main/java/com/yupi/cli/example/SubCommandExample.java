package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Callable {

//    若存在该选项，会覆盖掉 --help 选项
//    @Option(names = {"-h", "--height"}, interactive = true)
//    private int height;

    @Override
    public Object call() throws Exception {
        System.out.println("执行主命令");
        return null;
    }

    @Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddComand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @Command(name = "del", description = "删除", mixinStandardHelpOptions = true)
    static class DeleteCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行删除命令");
        }
    }

    @Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QueryCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行查询命令");
        }
    }

    public static void main(String[] args) {
        // 主命令
//        String[] someArgs = new String[] {};
        // 主命令帮助手册
//        String[] someArgs = new String[] {"--help"};
        // 执行增加命令
//        String[] someArgs = new String[] {"add"};
        // 执行增加命令的帮助手册
//        String[] someArgs = new String[] {"add", "--help"};
        // 执行不存在的命令，报错
        String[] someArgs = new String[] {"update"};
        int execute = new CommandLine(new SubCommandExample())
                .addSubcommand(new AddComand())
                .addSubcommand(new DeleteCommand())
                .addSubcommand(new QueryCommand())
                .execute(someArgs);
        System.exit(execute);
    }
}
