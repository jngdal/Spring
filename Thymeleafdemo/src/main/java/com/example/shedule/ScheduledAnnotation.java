package com.example.shedule;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledAnnotation {
	@Scheduled(fixedDelay = 36000)
	 public void fixedDelayTask() {
	 System.out.println(new Date() + " This runs in a fixed delay");
	 }

	 @Scheduled(fixedRate = 36000)
	 public void fixedRateTask() {
	 System.out.println(new Date() + " This runs in a fixed rate");
	 }

	 @Scheduled(fixedRate = 36000, initialDelay = 2000)
	 public void fixedRateWithInitialDelayTask(){
	 System.out.println(new Date() + " This runs in a fixed delay with a initial delay");
	 }

	 @Scheduled(cron = "0 36 11 ? * MON-FRI")
	 public void cronTask(){
	 System.out.println(new Date() + " This runs in a cron schedule");
	 }
}
