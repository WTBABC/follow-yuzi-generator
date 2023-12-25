package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;

public class StaticFileGenerator {
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
