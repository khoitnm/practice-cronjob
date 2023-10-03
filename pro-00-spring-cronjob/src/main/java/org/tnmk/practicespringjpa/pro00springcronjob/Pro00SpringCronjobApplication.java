package org.tnmk.practicespringjpa.pro00springcronjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Pro00SpringCronjobApplication {

  public static void main(String[] args) {
    SpringApplication.run(Pro00SpringCronjobApplication.class, args);
  }

  @PostConstruct
  public void init() {
    // Always run app in UTC timezone to solve the timezone problem
    // when loading Timestamp column (in UTC) from MySQL to a ZonedDateTime/OffsetDateTime
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
