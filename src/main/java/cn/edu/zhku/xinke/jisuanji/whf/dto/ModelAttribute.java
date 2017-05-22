package cn.edu.zhku.xinke.jisuanji.whf.dto;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ModelAttribute {
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	private String destination;
	
	public ModelAttribute(){}
	
	public ModelAttribute(String dest){
		destination = dest;
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setAttribute(String key,Object value){
		map.put(key, value);
	}
	
	public void removeAttribute(String key){
		if(map.containsKey(key)){
			map.remove(key);
		}
	}
	
	public void pollute(HttpServletRequest req){
		for(String key:map.keySet()){
			req.setAttribute(key, map.get(key));
		}
	}
	
	public Map<String,Object> get(){
		return map;
	}

}
