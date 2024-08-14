package com.rbxu.cola.market.textReplace;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TextDomainService {

    private static final Charset UTF_8_CHARSET = StandardCharsets.UTF_8;

    public static void transAndWriteToOutFile(TextTransJobDefine job) {
        List<String> strList = commonTrans(job);
        String res = strList.stream().collect(Collectors.joining(job.getLinesMergeChar()));
        whiteToFile(Lists.newArrayList(res), job.getFileOutputPath());
    }

    public static String transAndMergeToOneLine(TextTransJobDefine job) {
        List<String> strList = commonTrans(job);
        return strList.stream().collect(Collectors.joining(job.getLinesMergeChar()));
    }

    public static List<String> commonTrans(TextTransJobDefine job) {
        List<String> after = Lists.newArrayList();
        List<String> before = getTextContentElseEmpty(job.getFileInputPath());
        if (Objects.isNull(before) || before.isEmpty()) {
            return after;
        }
        for (String content : before) {
            Map<String, String> pairs = job.getTransPair();
            for (String beforeKey : pairs.keySet()) {
                content = content.replace(beforeKey, pairs.get(beforeKey));
            }
            after.add(content);
        }
        return after;
    }


    public static List<String> getTextContentElseEmpty(String filePath) {
        List<String> result = Lists.newArrayList();
        if (StringUtils.isBlank(filePath)) {
            return result;
        }
        Path path = Paths.get(filePath);

        try {
            BufferedReader reader = Files.newBufferedReader(path, UTF_8_CHARSET);
            while (true) {
                String line = reader.readLine();
                if (StringUtils.isNotBlank(line)) {
                    result.add(line);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            return result;
        }
        result.forEach(System.out::println);
        return result;
    }


    public static void whiteToFile(List<String> contents, String outFilePath) {
        File file = new File(outFilePath);
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), UTF_8_CHARSET);
            for (String content : contents) {
                writer.write(content);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
