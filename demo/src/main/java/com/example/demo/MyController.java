package com.example.demo;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController 
{
	@Autowired
	private DataSourceProperties data;
	
	@Autowired
	private KongaProperties konga;
	
	@GetMapping("/data")
	public Map<Object, Object> data()
	{
		return this.data.testDataMap();	
	}
	
	@GetMapping("/konga")
	public Map<String, Map<String, String>> konga()
	{
		return this.konga.addKongaToDataSource();
	}
}
