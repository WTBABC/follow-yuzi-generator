package com.yupi.maker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class ScriptGenerator {
    public static void doGenerate(String outputPath, String jarPath) {
        StringBuilder sb = new StringBuilder();
        // 根据操作系统生成不同的脚本文件
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            //@echo off
            //java -jar target/follow-yuzi-generator-basic-1.0-SNAPSHOT-jar-with-dependencies.jar %*
            sb.append("@echo off").append("\n");
            sb.append(String.format("java -jar %s %%*", jarPath));
            FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");
        } else {
            //#!/bin/bash
            //java -jar target/follow-yuzi-generator-basic-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"
            sb.append("#!/bin/bash").append("\n");
            sb.append(String.format("java -jar %s \"$@\"", jarPath));
            FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);

            // linux需增加可执行文件
            try {
                Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
                Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        String outputPath = System.getProperty("user.dir") + File.separator + "generated";
        doGenerate(outputPath, "");
    }
}
