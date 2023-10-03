package org.tnmk.practicespringjpa.pro00springcronjob.thread02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class Thread02_CronJob {
    private static AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000)
    public void doSomething() {
        log.info("Thread02_CronJob doSomething {}", count.getAndIncrement());
    }
}
