package com.yupi.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void main(String[] args) {
        // 获取项目根目录，获取的是 follow-yuzi-generator-basic
        String projectPath = System.getProperty("user.dir");
        // 取 follow-yuzi-generator 的路径
        File parenetFile = new File(projectPath).getParentFile();
        // 复制的文件路径
        String inputPath = new File(parenetFile, "follow-yuzi-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        // 指定复制输出的路径，项目的根目录
        String outputPath = projectPath;
//        copyFilesByHutool(inputPath, outputPath);
        copyFilesByReCurSion(inputPath, outputPath);
    }

    public static void copyFilesByReCurSion(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);

        try {
            copyFilesByReCurSion(srcFile, destFile);
        } catch (Exception e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    public static void copyFilesByReCurSion(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory()) {
            File nextDestFile = new File(destFile, srcFile.getName());
            if (!nextDestFile.exists()) {
                // 创建文件夹
                nextDestFile.mkdirs();
            }
            File[] files = srcFile.listFiles();
            if (files != null && 0 != files.length) {
                for (File file : files) {
                    copyFilesByReCurSion(file, nextDestFile);
                }
            }
        } else {
//            File copyFile = new File(destFile, srcFile.getName());
//            Files.copy(srcFile.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Path nextDestPath = destFile.toPath().resolve(srcFile.getName());
            Files.copy(srcFile.toPath(), nextDestPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
