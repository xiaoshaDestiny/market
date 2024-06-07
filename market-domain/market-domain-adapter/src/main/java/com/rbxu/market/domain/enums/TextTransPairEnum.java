package com.rbxu.market.domain.enums;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

import static com.rbxu.market.domain.constant.CommonConstant.*;

@Getter
public enum TextTransPairEnum {

    EN_TO_CN_COMMA("英转中-逗号", EN_COMMA, CN_COMMA),
    EN_TO_CN_SEMICOLON("英转中-分号", EN_SEMICOLON, CN_SEMICOLON),
    EN_TO_CN_QUE("英转中-问号", EN_QUE, CN_QUE),
    EN_TO_CN_COLON("英转中-冒号", EN_COLON, CN_COLON),
    EN_TO_CN_EXCLAMATION("英转中-感叹号", EN_EXCLAMATION, CN_EXCLAMATION),

    ;


    private String desc;

    private String before;

    private String after;

    TextTransPairEnum(String desc, String before, String after) {
        this.before = before;
        this.after = after;
        this.desc = desc;
    }


    /**
     * 英文 - 中文 标点符号转换
     * @return
     */
    public static List<TextTransPairEnum> commonEnToCnSignTrans() {
        return Lists.newArrayList(EN_TO_CN_COMMA, EN_TO_CN_SEMICOLON, EN_TO_CN_QUE, EN_TO_CN_COLON, EN_TO_CN_EXCLAMATION);
    }

}