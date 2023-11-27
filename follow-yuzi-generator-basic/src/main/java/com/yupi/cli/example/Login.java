package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import sun.security.util.ArrayUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, description = "Passphrase", interactive = true, arity = "0..1")
    String password;

    @Option(names = {"-cp", "--checkPassword"}, description = "Check Password", interactive = true, arity = "0..1")
    String checkPassword;

    String redundant;

    public Integer call() {
        System.out.println(user);
        System.out.println(password);
        System.out.println(checkPassword);
        return 0;
    }

    // 利用反射，强制交互选项必填
    public String[] fillArgs(String[] args) {
        // 记录 @Option interactive=true 的属性
        List<String> array = Arrays.asList(args);
        List<String> ret = new ArrayList<>(array);

        Class<Login> login = com.yupi.cli.example.Login.class;

        // 遍历每个属性
        for (Field field : login.getDeclaredFields()) {
            Option option = field.getAnnotation(Option.class);
            // 记录有 @Option 的且 interactive 为 true 的属性对应的选项名
            if (option != null) {
                if (option.interactive()) {
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

    public static void main(String[] args) {
        // 用户输入
        args = new String[] {"-u", "user123"};

        Login login = new Login();
        // 校验填充选项
        new CommandLine(login).execute(login.fillArgs(args));
    }
}
