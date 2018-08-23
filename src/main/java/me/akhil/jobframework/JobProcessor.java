package me.akhil.jobframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobProcessor implements Runnable {

	@Autowired
	private JobTracker jobtracker;
	private Job job;

	public JobProcessor() {
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public void run() {
		job.setJobStatus(JobStatus.IN_PROGRESS);
		jobtracker.updateJob(job);
		try {
			job.process();
			job.setJobStatus(JobStatus.COMPLETED);
		} catch (Exception e) {
			job.setJobStatus(JobStatus.FAILED);
			job.addJobParameter("error", e.getMessage());
		}

		jobtracker.updateJob(job);
	}

}
