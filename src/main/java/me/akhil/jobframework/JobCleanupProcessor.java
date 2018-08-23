package me.akhil.jobframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCleanupProcessor implements Runnable {

	@Autowired
	private JobTracker jobtracker;
	private Job job;

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public void run() {

		try {
			System.out.println("cleanup job started for "+this.job.getJobId());
			job.cleanUp();
		} catch (Exception e) {

		}

		jobtracker.removeJobFromQueue(job);

	}

}
