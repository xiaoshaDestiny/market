package com.rbxu.market.domain.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SleepSortTest {

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
