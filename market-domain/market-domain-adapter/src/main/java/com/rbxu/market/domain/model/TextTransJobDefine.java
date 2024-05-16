package com.rbxu.market.domain.model;

import com.rbxu.market.domain.constant.CommonConstant;
import com.rbxu.market.domain.enums.TextTransPairEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class TextTransJobDefine {

    /**
     * 文件路径（输入）
     */
    private String fileInputPath;

    /**
     * 文件路径（输出）
     */
    private String fileOutputPath;


    /**
     * 转换符号
     */
    private Map<String, String> transPair;


    /**
     * 结果合并符号
     */
    private String linesMergeChar;


    public static TextTransJobDefine buildSimple(String fileInputPath, String fileOutputPath) {
        List<TextTransPairEnum> pairs = TextTransPairEnum.commonEnToCnSignTrans();
        Map<String, String> transPair = pairs.stream()
                .collect(Collectors.toMap(TextTransPairEnum::getBefore, TextTransPairEnum::getAfter, (x,y) -> x));
        return TextTransJobDefine.builder()
                .fileInputPath(fileInputPath)
                .fileOutputPath(fileOutputPath)
                .transPair(transPair)
                .linesMergeChar(CommonConstant.EMPTY_STR)
                .build();
    }

}
