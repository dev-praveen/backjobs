package com.dev.backjobs;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.scheduling.BackgroundJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackJobsApplication {

  private static final Logger log =
      new JobRunrDashboardLogger(LoggerFactory.getLogger(BackJobsApplication.class));

  public static void main(String[] args) {
    SpringApplication.run(BackJobsApplication.class, args);
    BackgroundJob.enqueue(BackJobsApplication::executeSomeTask);
    log.info("Job queuing by.......{}", Thread.currentThread().getName());
    BackgroundJob.enqueue(BackJobsApplication::executeComplexTask);
  }

  @Job(name = "simple job task", retries = 2)
  public static void executeSomeTask() throws InterruptedException {

    log.info("Simple Job is executing.......{}", Thread.currentThread().getName());
    Thread.sleep(30000);
    log.info("Simple Job is executed.......{}", Thread.currentThread().getName());
  }

  @Job(name = "complex job task", retries = 2)
  public static void executeComplexTask() throws InterruptedException {

    log.info("Complex Job is executing.......{}", Thread.currentThread().getName());
    Thread.sleep(300000);
    log.info("Complex Job is executed.......{}", Thread.currentThread().getName());
  }
}
