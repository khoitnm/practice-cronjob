package org.tnmk.practicespringjpa.pro00springcronjob.thread01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practicespringjpa.pro00springcronjob.common.utils.ThreadUtils;

@Service
@Slf4j
public class Thread01_LongTask {
    public void doSomethingLong(){
        log.info("Thread01_LongTask start");
        ThreadUtils.sleep(5000);// 5 seconds
        log.info("Thread01_LongTask end");
    }
}
