package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "")
public class DataSourceProperties 
{
	private Map<String, Map<String,String>> database;
	
	public Map<String, Map<String,String>> getDatabase()
	{
		return database;
	}
	
	public void setDatabase(Map<String, Map<String, String>> database)
	{
		this.database = database;
	}
	
	public Map<String, DataSource> dataMap()
	{
		List<String> list = new ArrayList<String>(this.database.keySet());
		
		Map<String, DataSource> dataSourceMap = new HashMap<>();
		DataSourceBuilder dsb = DataSourceBuilder.create();
		
		for(String s: list)
		{
			dsb.driverClassName(this.database.get(s).get("driverName"));
			dsb.url(this.database.get(s).get("url"));
			dsb.username(this.database.get(s).get("username"));
			dsb.password(this.database.get(s).get("password"));
			
			dataSourceMap.put(s, dsb.build());
		}
		return dataSourceMap;
	}
	
	public Map<Object, Object> testDataMap()
	{
		List<String> list = new ArrayList<String>(this.database.keySet());
		
		Map<Object, Object> dataSourceMap = new HashMap<>();
		
		for(String s: list)
		{
			MyData data = new MyData();
			HashMap<String, String> map = new HashMap<>();
			map.put("driverName",this.database.get(s).get("driverName"));
			map.put("url",this.database.get(s).get("url"));
			map.put("username",this.database.get(s).get("username"));
			map.put("password",this.database.get(s).get("password"));
			
			dataSourceMap.put(s, map);
		}
		return dataSourceMap;
	}
}
