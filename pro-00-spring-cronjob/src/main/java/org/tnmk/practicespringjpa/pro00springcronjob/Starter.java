package org.tnmk.practicespringjpa.pro00springcronjob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.thread01.Thread01Async;
import org.tnmk.practicespringjpa.pro00springcronjob.thread03.Thread03_Job_withScheduledExecutorService;

import java.util.concurrent.CompletableFuture;

import static org.apache.commons.lang3.StringUtils.join;

@Slf4j
@Service
@RequiredArgsConstructor
public class Starter {
    private final Thread01Async thread01Async;
    private final Thread03_Job_withScheduledExecutorService thread03;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        log.info("Starter: starting...");

        thread03.startJob();

        CompletableFuture.allOf(
                thread01Async.asyncDoSomethingLong()
        ).join();

        log.info("Starter: finished");
    }

    @EventListener(ContextClosedEvent.class)
    public void end() {
        log.info("App: end");
    }
}
