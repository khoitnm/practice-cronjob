package org.tnmk.practicespringjpa.pro00springcronjob.thread03;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.thread03.schedulerjob.SchedulerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class Thread03_Job_withScheduledExecutorService {
    private final SchedulerService schedulerService;

    public void startJob() {
        schedulerService.awaitUntilFinish(1, 3, () -> {
            log.info("Thread03_Job_withScheduledExecutorService");
            return null;
        });
    }
}
