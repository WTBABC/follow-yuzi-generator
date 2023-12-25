package com.yupi.maker.generator;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        String mvnCmd;
        // 根据操作系统执行不同命令
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            mvnCmd = "mvn.cmd clean package -DskipTests=true";
        } else {
            mvnCmd = "mvn clean package -DskipTests=true";
        }

        // 空格分割命令
        ProcessBuilder processBuilder = new ProcessBuilder(mvnCmd.split(" "));
        // 设置执行命令的路径
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        // 读取输入
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;        // todo 读取去哪了
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("F:/github/follow-yuzi-generator/follow-yuzi-generator-maker/generated/acm-template-pro-generator");
    }
}
