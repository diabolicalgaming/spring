package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ConfigurationProperties(prefix = "")
public class KongaProperties 
{
	@Autowired
	private DataSourceProperties data;
	
	private Map<String,String> konga;
	
	public Map<String, String> getKonga()
	{
		return konga;
	}
	
	public void setKonga(Map<String, String> konga)
	{
		this.konga = konga;
	}
	
	public Map<String, Map<String,String>> addKongaToDataSource()
	{
		List<String> list = new ArrayList<String>(this.data.getDatabase().keySet());
		for(String client: list) 
		{
			this.data.getDatabase().get(client).put(client+".consumerName", this.konga.get(client+".consumerName"));
		}
		return this.data.getDatabase();
	}
}

