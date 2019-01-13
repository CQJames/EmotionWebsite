package com.ew.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Joke;
import com.ew.service.JokeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("jokeAction")
@Scope("prototype")
public class JokeAction extends ActionSupport implements ModelDriven<Joke>{

	/**
	 * 笑话action类
	 */
	private static final long serialVersionUID = 7323277723836160769L;

	private Joke joke;
	private JokeService jokeService;
	
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
	
	public String findAllShowJoke() throws Exception {
		
		//获得所有笑话
		List<Joke> jokeList = jokeService.findAllShowJoke();
		//放入request中
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("jokeList", jokeList);
		
		return "toForegroundFunTime";
	}
	
	public String findJokeById() throws Exception {
		
    	String jokeID = joke.getJokeID();
    	//根据笑话id找文章
    	joke = jokeService.findJokeById(jokeID);
		
		return "toForegroundShowArticle";
	}
}
