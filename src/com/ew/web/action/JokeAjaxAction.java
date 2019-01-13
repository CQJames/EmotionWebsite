package com.ew.web.action;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Joke;
import com.ew.service.JokeService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("jokeAjaxAction")
@Scope("prototype")
public class JokeAjaxAction extends ActionSupport implements ModelDriven<Joke> {
	
	/**
	 * 笑话AjaxAction
	 */
	private static final long serialVersionUID = 3125158540997160672L;
	
	private Joke joke;
	private JokeService jokeService;
	//存储将click转为json的字符串
	private String json;
	
	@Override
	public Joke getModel() {
		return joke;
	}
	public Joke getJoke() {
		return joke;
	}
	@Resource(name="joke")
	public void setJoke(Joke joke) {
		this.joke = joke;
	}
	public JokeService getJokeService() {
		return jokeService;
	}
	@Resource(name="jokeService")
	public void setJokeService(JokeService jokeService) {
		this.jokeService = jokeService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String addClicks() throws Exception {
		
    	String jokeID = joke.getJokeID();
    	//根据笑话id找文章并加一个赞
    	joke = jokeService.updateAddClicks(jokeID);
    	//将点赞放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("like", joke.getClicks());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
}
