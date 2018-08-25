package me.akhil.jobframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.akhil.jobframework.dao.DBUtils;
import me.akhil.jobframework.dao.JobRepository;

@Component
public class JobCleanupProcessor implements Runnable {

	@Autowired
	private JobTracker jobtracker;
	private Job job;
	
	@Autowired
	private JobRepository repo;

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

		repo.delete(DBUtils.getJobDAO(job));
		jobtracker.removeJobFromQueue(job);

	}

}
