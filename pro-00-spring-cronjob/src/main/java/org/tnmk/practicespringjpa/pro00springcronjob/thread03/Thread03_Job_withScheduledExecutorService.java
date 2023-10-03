package org.tnmk.practicespringjpa.pro00springcronjob.thread03;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.thread03.schedulerjob.SchedulerService;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Thread03_Job_withScheduledExecutorService {
    private final SchedulerService schedulerService;

    @Async
    public CompletableFuture<Void> startJob() {
        schedulerService.awaitUntilFinish(1, 8, (count) -> {
            log.info("Thread03_Job_withScheduledExecutorService "+ count);
            return null;
        });

        return CompletableFuture.completedFuture(null);
    }
}
