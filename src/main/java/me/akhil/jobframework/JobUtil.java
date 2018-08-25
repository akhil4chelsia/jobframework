package me.akhil.jobframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import me.akhil.jobframework.dao.DBUtils;
import me.akhil.jobframework.dao.JobRepository;

@Component
public class JobUtil {

	@Autowired
	private JobTracker tracker;

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JobRepository repo;

	public String submitJob(Job job) {
		repo.save(DBUtils.getJobDAO(job));
		return tracker.addToJobQueue(job);
	}

	public Job getJob(String jobId) {
		if (!isJobExists(jobId)) {
			throw new RuntimeException("Invalid job id");
		}
		return tracker.getJob(jobId);
	}

	public JobStatus getJobStatus(String jobId) {
		return tracker.getJobStatus(jobId);
	}

	public boolean isJobExists(String id) {
		return tracker.getJob(id) != null;
	}

	public <T extends Job> Job createJob(Class<T> bean) {
		Job job = context.getBean(bean);
		job.resetJob();
		return job;
	}
}
