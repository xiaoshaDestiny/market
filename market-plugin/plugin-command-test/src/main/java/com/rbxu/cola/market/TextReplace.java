package com.rbxu.cola.market;

import com.google.common.collect.Lists;
import com.rbxu.cola.market.textReplace.CommonConstant;
import com.rbxu.cola.market.textReplace.TextDomainService;
import com.rbxu.cola.market.textReplace.TextTransJobDefine;

import java.util.List;

public class TextReplace {

    public static void execute(String waitReplace, String afterReplace) {
        System.out.println("text-replace start ......");

        System.out.println("text-replace before multi-line is:");
        TextTransJobDefine job = TextTransJobDefine.buildSimple(waitReplace, afterReplace);
        List<String> strList = TextDomainService.commonTrans(job);
        System.out.println();


        System.out.println("text-replace after one-line is: ");
        strList.forEach(System.out::print);
        System.out.println();
        System.out.println();


        String res = String.join(CommonConstant.NEXT_LINE_STR, strList);
        System.out.println("text-replace after multi-line is: ");
        System.out.println(res);
        System.out.println();


        TextDomainService.whiteToFile(Lists.newArrayList(res), job.getFileOutputPath());
        System.out.println("text-replace end ...");
    }

    public static void main(String[] args) {
        execute(args[0], args[1]);
    }
}