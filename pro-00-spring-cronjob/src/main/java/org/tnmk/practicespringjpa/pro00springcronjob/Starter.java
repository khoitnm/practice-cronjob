package org.tnmk.practicespringjpa.pro00springcronjob;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.thread01.Thread01Async;

@Service
@RequiredArgsConstructor
public class Starter {
    private final Thread01Async thread01Async;
    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        thread01Async.asyncDoSomethingLong();
    }
}
