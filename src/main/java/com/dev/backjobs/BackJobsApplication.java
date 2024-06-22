package com.dev.backjobs;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackJobsApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackJobsApplication.class, args);
    BackgroundJob.enqueue(BackJobsApplication::executeSomeTask);
    System.out.println("Job queuing by......." + Thread.currentThread().getName());
    BackgroundJob.enqueue(BackJobsApplication::executeComplexTask);
  }

  @Job(name = "simple job task", retries = 2)
  public static void executeSomeTask() throws InterruptedException {

    Thread.sleep(30000);
    System.out.println("Simple Job is executing......." + Thread.currentThread().getName());
  }

  @Job(name = "complex job task", retries = 2)
  public static void executeComplexTask() throws InterruptedException {

    Thread.sleep(300000);
    System.out.println("Complex Job is executing......." + Thread.currentThread().getName());
  }
}
