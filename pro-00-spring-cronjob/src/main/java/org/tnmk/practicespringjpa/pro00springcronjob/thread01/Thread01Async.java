package org.tnmk.practicespringjpa.pro00springcronjob.thread01;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.common.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class Thread01Async {

    private final Thread01_LongTask thread01LongTask;
    @Async
    public CompletableFuture<Void> asyncDoSomethingLong(){
        thread01LongTask.doSomethingLong();
        return CompletableFuture.completedFuture(null);
    }
}
