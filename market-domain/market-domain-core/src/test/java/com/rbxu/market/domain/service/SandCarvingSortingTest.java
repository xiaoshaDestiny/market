package com.rbxu.market.domain.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 沙雕排序算法
 */
@Slf4j
public class SandCarvingSortingTest {

    /**
     * 面条排序：将输入分别对应到不同长度的面条上，每根面条的长度即为对应的数字的大小。
     * 比如，对于[1, 4, 2, 8, 9]这个输入，则分别做出长度为1cm、4cm、2cm、8cm、9cm的面条。
     * 然后，将这些面条的一头对其，用手抓住，另一头向下。然后慢慢地将手向下垂直下降，
     * 第一个触碰到桌面的面条对应的数字则为最大的数字，第二个触碰到的就是第二大的，依次类推。
     *
     * (1) 搞不定负数
     * (2) 只能从大到小
     */
    @Test
    public void testNoodleSort() {
        List<Integer> waitSort = Lists.newArrayList(1, 4, 2, 8, 9);
        log.info("before: {}", waitSort);

        List<List<String>> noodles = Lists.newArrayList();
        for (Integer x : waitSort) {
            List<String> aNoodle = Lists.newArrayList();
            for (int i = 0; i <= x-1 ; i++) {
                aNoodle.add("面的一部分");
            }
            noodles.add(aNoodle);
        }

        List<Integer> afterSort = Lists.newArrayList();

        // 场地高度100
        int placeHeight = 100;
        for (int i = placeHeight; i >= 0 ; i--) {
            for (List<String> noodle : noodles) {
                if (noodle.size() == i) {
                    afterSort.add(noodle.size());
                }
            }
        }

        log.info("after: {}", afterSort);
    }

    /**
     * 猴子排序法：进来就检查是不是有序的，不是的话随机打乱，再检查
     * 理论依据：一只猴子随机敲打打字机键盘，如果时间足够长，总是能打出特定的文本，比如莎士比亚全集。
     * 最好执行时间是O(1), 最坏执行时间是地球爆炸
     */
    @Test
    public void testMonkeySort() {
        List<Integer> waitSort = Lists.newArrayList(5, 3 ,1, 2);
        log.info("before: {}", waitSort);

        while (!isSorted(waitSort)) {
            Collections.shuffle(waitSort);
        }
        log.info("after: {}", waitSort);
    }

    public static boolean isSorted(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 睡觉排序法：进来就睡觉，谁先醒谁小
     * (1) 搞不定负数
     * (2) 输入数据很相近时会有误差
     * (3) 输入数据很多时，这些线程不能看作是同时启动的
     * (4) 只能从小到大排序
     */
    @Test
    public void testSleepSort() throws InterruptedException {
        List<Integer> waitSort = Lists.newArrayList(4, 7, 2, 10, 0, 1);
        log.info("before: {}", waitSort);

        List<Integer> afterSort = Lists.newArrayList();
        waitSort.forEach(x-> {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(x * 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                afterSort.add(x);
            });
            thread.start();
        });

        while (afterSort.size() != waitSort.size()) {
            Thread.sleep(10);
        }
        log.info("after: {}", afterSort);
    }
}
