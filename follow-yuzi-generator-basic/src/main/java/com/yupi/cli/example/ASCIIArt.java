package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "asciiart", mixinStandardHelpOptions = true, version = "ASCIIArt 1.0")
public class ASCIIArt implements Callable<Integer> {

    @Option(names = {"-s", "--font-size"}, description = "Font Size")
    private int fontSize = 19;

    @Parameters(paramLabel = "<word>", defaultValue = "hello, picocli",
                description = "words to translated into ASCII art.")
    private String[] words = {"hello", "picocli"};

    @Option(names = {"-option"})
    private int[] values;

    @Parameters
    private int noIndex; //两个无index属性的@Parameters，优先赋值单值

    @Override
    public Integer call() throws Exception {
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + String.join(",", words));
        System.out.println(Arrays.toString(values));
        return 0;
    }

    public static void main(String... args) {
        args = new String[] {"--font-size", "20", "hello", "wtbabc", "-option", "1", "-option", "2", "-option", "3"};
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}
