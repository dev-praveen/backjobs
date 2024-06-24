package com.dev.backjobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BackJobsApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackJobsApplication.class, args);
    BackgroundJob.enqueue(BackJobsApplication::executeSomeTask);
    log.info("Job queuing by.......{}", Thread.currentThread().getName());
    BackgroundJob.enqueue(BackJobsApplication::executeComplexTask);
  }

  @Job(name = "simple job task", retries = 2)
  public static void executeSomeTask() throws InterruptedException {

    Thread.sleep(30000);
    log.info("Simple Job is executing.......{}", Thread.currentThread().getName());
  }

  @Job(name = "complex job task", retries = 2)
  public static void executeComplexTask() throws InterruptedException {

    Thread.sleep(300000);
    log.info("Complex Job is executing.......{}", Thread.currentThread().getName());
  }
}
