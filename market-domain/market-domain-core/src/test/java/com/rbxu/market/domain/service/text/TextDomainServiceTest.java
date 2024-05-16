package com.rbxu.market.domain.service.text;

import com.google.common.collect.Lists;
import com.rbxu.market.domain.model.TextTransJobDefine;
import org.junit.Test;

public class TextDomainServiceTest {

    @Test
    public void test_LC() {
        String waitReplace = "C:\\Users\\ASUS\\Desktop\\waitReplace.txt";
        String afterReplace = "C:\\Users\\ASUS\\Desktop\\afterReplace.txt";

        TextTransJobDefine jobDefine = TextTransJobDefine.buildSimple(waitReplace, afterReplace);
        TextDomainService.transAndWriteToOutFile(jobDefine);
    }
}