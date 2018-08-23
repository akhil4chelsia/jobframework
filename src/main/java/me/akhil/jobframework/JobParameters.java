package me.akhil.jobframework;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JobParameters {

	private Map<String, String> jobParameters = new HashMap<>();

	public void add(String key, String value) {
		jobParameters.put(key, value);
	}

	public String get(String key) {
		return jobParameters.get(key);
	}
}
