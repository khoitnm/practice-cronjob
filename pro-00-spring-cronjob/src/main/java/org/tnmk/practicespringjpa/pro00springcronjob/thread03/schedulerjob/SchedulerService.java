package org.tnmk.practicespringjpa.pro00springcronjob.thread03.schedulerjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;


@Slf4j
@Service
public class SchedulerService {
  static AtomicInteger methodCount = new AtomicInteger(0);

  public <O> List<TaskResult<O>> awaitUntilFinish(int executionsPerSecond, int totalDurationSeconds, Supplier<O> supplier) {
    List<TaskResult<O>> results = Collections.synchronizedList(new ArrayList<>());
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    int totalExecutions = executionsPerSecond * totalDurationSeconds;

    Runnable methodTrigger = () -> {
      try {
        log.info("methodCount: " + methodCount + "/" + totalExecutions);
        methodCount.incrementAndGet();

        O result = supplier.get();
        results.add(TaskResult.<O>builder()
          .data(result)
          .build()
        );
      } catch (RuntimeException ex) {
        // The log will be handled in the report, so we don't need to write log here.
        results.add(TaskResult.<O>builder()
          .exception(Optional.of(ex))
          .build()
        );
        if (ex instanceof AbortTaskException) {
          // Don't continue executing new tasks anymore.
          // Note: the current submitted tasks will still continue processing.
          scheduler.shutdown();
          log.error("The whole job is aborted. Caused by: " + ex.getMessage(), ex);
        }
      }
    };

    scheduler.scheduleAtFixedRate(methodTrigger, 0, 1000 / executionsPerSecond, TimeUnit.MILLISECONDS);

    try {
      scheduler.awaitTermination(totalDurationSeconds, TimeUnit.SECONDS);
      log.info("awaitTermination {} seconds.", totalDurationSeconds);
    } catch (InterruptedException e) {
      log.error("InterruptedException: " + e.getMessage(), e);
      Thread.currentThread().interrupt();
    }

    // After this duration, the scheduler will be shutdown, and the function won't be executed anymore.
    // In theory, having awaitTermination() is enough.
    // However, after awaitTermination, there could be a few more runnable threads that will be submitted.
    // So having shutdown will make sure no more submitted threads.
    scheduler.shutdown();
    methodCount.set(0);
    return results;
  }
}
