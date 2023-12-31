package org.tnmk.practicespringjpa.pro00springcronjob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.common.utils.ThreadUtils;
import org.tnmk.practicespringjpa.pro00springcronjob.thread01.Thread01Async;
import org.tnmk.practicespringjpa.pro00springcronjob.thread02.Thread02_Job_withSpring;
import org.tnmk.practicespringjpa.pro00springcronjob.thread03.Thread03_Job_withScheduledExecutorService;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Starter {
    private final ApplicationContext context;

    private final Thread01Async thread01Async;
    private final Thread03_Job_withScheduledExecutorService thread03;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        log.info("Starter: starting...");

        CompletableFuture.allOf(
                thread01Async.asyncDoSomethingLong(),
                thread03.startJob()
        ).join();

        /**
         * This method will force {@link Thread02_Job_withSpring} to end;
         * otherwise, it won't be stopped because it's the scheduled job (at fixed rate).
         *
         * Note that after triggering context.close(), that job still won't be finished immediately.
         * It is only finished after {@link Thread02_Job_withSpring#end()} is finished.
         */
        ((ConfigurableApplicationContext) context).close();
        log.info("Starter: finished");
    }

    @EventListener(ContextClosedEvent.class)
    public void end() {
        log.info("App: start ending");
        ThreadUtils.sleep(2000);
        log.info("App: finish ending");
    }
}
