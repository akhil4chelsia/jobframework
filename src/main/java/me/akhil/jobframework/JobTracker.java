package me.akhil.jobframework;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JobTracker {

	Map<String, Job> jobQueue = new ConcurrentHashMap<>();

	public String addToJobQueue(Job job) {
		jobQueue.put(job.getJobId(), job);
		return job.getJobId();
	}

	public Job getJob(String jobId) {
		return jobQueue.get(jobId);
	}

	public JobStatus getJobStatus(String jobId) {
		return jobQueue.get(jobId).getJobStatus();
	}
	
	public void updateJob(Job job){
		jobQueue.put(job.getJobId(), job);
	}

	public List<Job> getPendingJobs() {
		return jobQueue.values().stream().filter(x -> x.getJobStatus() == JobStatus.PENDING)
				.collect(Collectors.toList());
	}
	
	public void removeJobFromQueue(Job job){
		jobQueue.remove(job.getJobId());
	}

	public List<Job> getCompletedAndFailedJobs() {		
		return jobQueue.values().stream().filter(x -> (x.getJobStatus() == JobStatus.COMPLETED || x.getJobStatus() == JobStatus.COMPLETED))
				.collect(Collectors.toList());
	}
}
