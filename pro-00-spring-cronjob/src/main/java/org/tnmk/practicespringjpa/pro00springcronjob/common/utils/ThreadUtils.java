package org.tnmk.practicespringjpa.pro00springcronjob.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadUtils {
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Thread cannot sleep {} milliseconds. Caused by {}", milliseconds, e.getMessage(), e);
            Thread.interrupted();
        }
    }
}
