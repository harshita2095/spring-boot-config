package com.javabrains.springbootconfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {
	
	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${my.list.values}")
	private List<String> listValues;
	
	@Value("some static message")
	private String someStaticMessage;
	
	@Value("#{${dbValues}}")
	private Map<String, String> dbValues;
	
	@Autowired
	private DbSettings dbSettings;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greeting")
	public String greeting() {
		//return "Hello";
		return greetingMessage+" "+listValues+" "+someStaticMessage+"\n"
				+dbValues+"\n"+dbSettings.getConnection()+" "
				+dbSettings.getHost()+" "+dbSettings.getPort();
	}
	
	@GetMapping("/envdetails")
	public String[] getEnvDetails() {
		return env.getActiveProfiles();
	}

}
