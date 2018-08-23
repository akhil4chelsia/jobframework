package me.akhil.jobframework;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;


@Component
public abstract class Job {
	
	private String jobId;	
	private JobStatus jobStatus;	
	private Map<String, String> jobParameters = new HashMap<>();		

	protected Job() {
		setJobId(UUID.randomUUID().toString());
		setJobStatus(JobStatus.PENDING);
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public void addJobParameter(String key, String value) {
		jobParameters.put(key, value);
	}
	
	public String getJobParameter(String key) {
		return jobParameters.get(key);
	}	
	
	public Map<String, String> getJobParameters() {
		return jobParameters;
	}
	
	public void resetJob() {
		setJobId(UUID.randomUUID().toString());
		setJobStatus(JobStatus.PENDING);
		jobParameters = new HashMap<>();
	}

	public abstract void process() throws Exception;
	
	public abstract void cleanUp();
	
}
