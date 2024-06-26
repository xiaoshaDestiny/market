package com.rbxu.market.domain.service.text;

import com.google.common.collect.Lists;
import com.rbxu.market.domain.model.TextTransJobDefine;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextDomainService {

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
        if (CollectionUtils.isEmpty(before)) {
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
        Charset charset = StandardCharsets.UTF_8;

        try {
            BufferedReader reader = Files.newBufferedReader(path, charset);
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
        return result;
    }


    public static void whiteToFile(List<String> contents, String outFilePath) {
        File file = new File(outFilePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (String content : contents) {
                writer.write(content);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
