package me.akhil.jobframework.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Transient;

import me.akhil.jobframework.JobStatus;

@Entity
public class JobDAO {

	@Id
	private String jobId;
	private JobStatus jobStatus;

	@ElementCollection
	@JoinTable(name = "JOB_PARAMETERS", joinColumns = @JoinColumn(name = "ID"))
	@MapKeyColumn(name = "JOB_ID")
	@Column(name = "JOB_PARAMS")
	private Map<String, String> jobParameters = new HashMap<>();

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

	public Map<String, String> getJobParameters() {
		return jobParameters;
	}

	public void setJobParameters(Map<String, String> jobParameters) {
		this.jobParameters = jobParameters;
	}

}
