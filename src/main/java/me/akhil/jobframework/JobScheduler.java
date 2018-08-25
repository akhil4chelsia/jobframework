package me.akhil.jobframework;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.akhil.jobframework.dao.DBUtils;
import me.akhil.jobframework.dao.JobRepository;

@Component
public class JobScheduler {

	@Autowired
	private JobTracker jobtracker;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private Executor executor;
		
	@Scheduled(initialDelay = 500, fixedDelay = 1000)
	public void scheduleJob() {
		List<Job> pendingJobs = jobtracker.getPendingJobs();
		pendingJobs.stream().forEach(job -> {
			JobProcessor jobProcessor = context.getBean(JobProcessor.class);
			jobProcessor.setJob(job);					
			executor.execute(jobProcessor);
		});
	}

	@Scheduled(initialDelay = 500, fixedDelay = 1000*60*15)
	public void scheduleCleanUp() {
		try{
			List<Job> pendingJobs = jobtracker.getCompletedAndFailedJobs();
			pendingJobs.stream().forEach(job -> {
			JobCleanupProcessor jobCleanupProcessor = context.getBean(JobCleanupProcessor.class);
			jobCleanupProcessor.setJob(job);
			executor.execute(jobCleanupProcessor);	
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
