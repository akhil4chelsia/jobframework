package me.akhil.jobframework;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JobUtil {

	@Autowired
	private JobTracker tracker;

	@Autowired
	private ApplicationContext context;

	public String submitJob(Job job) {
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
