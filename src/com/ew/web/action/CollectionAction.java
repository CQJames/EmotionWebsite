package com.ew.web.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Collection;
import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.Joke;
import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.User;
import com.ew.entity.Utterance;
import com.ew.service.CollectionService;
import com.ew.service.CommunicationSkillsArticleService;
import com.ew.service.JokeService;
import com.ew.service.SelfEnhancementArticleService;
import com.ew.service.UserService;
import com.ew.service.UtteranceService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("collectionAction")
@Scope("prototype")
public class CollectionAction extends ActionSupport implements ModelDriven<Collection>{

	/**
	 * 笑话action类
	 */
	private static final long serialVersionUID = 7323277723836160769L;

	private Collection collection;
	private CollectionService collectionService;
	private String userID;
	private String collection_ID;
	
	private Joke joke;
	private JokeService jokeService;
	
	private CommunicationSkillsArticle communicationSkillsArticle;
	private CommunicationSkillsArticleService communicationSkillsArticleService;
	
	private SelfEnhancementArticle selfEnhancementArticle;
	private SelfEnhancementArticleService selfEnhancementArticleService;
	
	private Utterance utterance;
	private UtteranceService utteranceService;
	
	private User user;
	private UserService userService;
	
	@Resource(name="selfEnhancementArticle")
	public void setSelfEnhancementArticle(
			SelfEnhancementArticle selfEnhancementArticle) {
		this.selfEnhancementArticle = selfEnhancementArticle;
	}

	@Resource(name="selfEnhancementArticleService")
	public void setSelfEnhancementArticleService(
			SelfEnhancementArticleService selfEnhancementArticleService) {
		this.selfEnhancementArticleService = selfEnhancementArticleService;
	}

	@Resource(name="utterance")
	public void setUtterance(Utterance utterance) {
		this.utterance = utterance;
	}
	
	@Resource(name="utteranceService")
	public void setUtteranceService(UtteranceService utteranceService) {
		this.utteranceService = utteranceService;
	}

	@Resource(name="communicationSkillsArticle")
	public void setCommunicationSkillsArticle(
			CommunicationSkillsArticle communicationSkillsArticle) {
		this.communicationSkillsArticle = communicationSkillsArticle;
	}

	@Resource(name="communicationSkillsArticleService")
	public void setCommunicationSkillsArticleService(
			CommunicationSkillsArticleService communicationSkillsArticleService) {
		this.communicationSkillsArticleService = communicationSkillsArticleService;
	}

	@Resource(name="user")
	public void setUser(User user) {
		this.user = user;
	}
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource(name="joke")
	public void setJoke(Joke joke) {
		this.joke = joke;
	}

	@Resource(name="jokeService")
	public void setJokeService(JokeService jokeService) {
		this.jokeService = jokeService;
	}

	public void setCollection_ID(String collection_ID) {
		this.collection_ID = collection_ID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public Collection getModel() {
		return collection;
	}
	
	@Resource(name="collection")
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
	@Resource(name="collectionService")
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}


	
	
	public String findAllCollection() throws Exception {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
//		detachedCriteria.add(Restrictions.eq("isCollect", true)).createAlias("user","u" ).add(Restrictions.eq("u.username", "sss"));
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("isCollect", true));
		detachedCriteria.addOrder(Order.desc("saveTime"));
		
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("collectionList", collectionList);
		
		return "toPersonCollection";
	}
	
	public String cancelCollection(){

		
		collection = collectionService.findById(collection_ID);
		collection.setIsCollect(false);
		collectionService.update(collection);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
//		detachedCriteria.add(Restrictions.eq("isCollect", true)).createAlias("user","u" ).add(Restrictions.eq("u.username", "sss"));
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("isCollect", true));
		detachedCriteria.addOrder(Order.desc("saveTime"));
		
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("collectionList", collectionList);
	
		return "toPersonCollection";
		
	}
	
	
	public String findAllHistory(){
		
		DetachedCriteria detachedCriteriaToday = DetachedCriteria.forClass(Collection.class);
		DetachedCriteria detachedCriteriaWeek = DetachedCriteria.forClass(Collection.class);
		DetachedCriteria detachedCriteriaMonth = DetachedCriteria.forClass(Collection.class);
		detachedCriteriaToday.createCriteria("user");
		detachedCriteriaWeek.createCriteria("user");
		detachedCriteriaMonth.createCriteria("user");
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//      System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
		Date nowDate = new Date();
	        
		Calendar Time = new GregorianCalendar();
		Time.setTime(nowDate);
		Time.add(Calendar.SECOND, -24*3600);
		Date todayTime = Time.getTime();
		
		Time.setTime(nowDate);
		Time.add(Calendar.SECOND, -7*24*3600);
		Date weekTime = Time.getTime();
		
		Time.setTime(nowDate);
		Time.add(Calendar.SECOND, -7*24*3600*30);
		Date monthTime = Time.getTime();

//      long nowDate= Time.getTime();//把时间换算成毫秒

        
		detachedCriteriaToday.add(Restrictions.eq("user.userID", userID));
		detachedCriteriaToday.add(Restrictions.between("saveTime",todayTime,nowDate));
		detachedCriteriaToday.addOrder(Order.desc("saveTime"));
		
		detachedCriteriaWeek.add(Restrictions.eq("user.userID", userID));
		detachedCriteriaWeek.add(Restrictions.between("saveTime",weekTime,todayTime));
		detachedCriteriaWeek.addOrder(Order.desc("saveTime"));
		
		detachedCriteriaMonth.add(Restrictions.eq("user.userID", userID));
		detachedCriteriaMonth.add(Restrictions.between("saveTime",monthTime,weekTime));
		detachedCriteriaMonth.addOrder(Order.desc("saveTime"));
		
		
		List<Collection> historyTodayList = collectionService.findAllCollection(detachedCriteriaToday);
		List<Collection> historyWeekList = collectionService.findAllCollection(detachedCriteriaWeek);
		List<Collection> historyMonthList = collectionService.findAllCollection(detachedCriteriaMonth);
		
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("historyTodayList", historyTodayList);
		request.put("historyWeekList", historyWeekList);
		request.put("historyMonthList", historyMonthList);
		
		return "toPersonFoot";
		
	}
	
	
	/**
	 * 
	 * @joke的浏览与收藏方法
	 * @throws IOException
	 */
	public String JokeHistory() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		if(collectionService.validation(collection.getMessageID(),detachedCriteria)){
			detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
			List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
			Collection collection = collectionList.get(0);
			collectionService.delete(collection);
			collectionService.save(collectionList.get(0));
			
			if(collectionList.get(0).getIsCollect()){
				map.put("msg","取消收藏笑话");
			}else{
				map.put("msg","点击收藏笑话");
			}
		}else{
			joke = jokeService.findJokeById(collection.getMessageID());
			user = userService.findById(userID);

			collection.setIsCollect(false);
			collection.setTitle(joke.getTitle());
			collection.setImage(joke.getImage());
			collection.setType("每日一笑");
			collection.setUser(user);
			collectionService.save(collection);
			
			map.put("msg","点击收藏笑话");
			
			
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
		
	}
	
	
	public String JokeCollection() throws IOException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		collectionList.get(0).setIsCollect(!collectionList.get(0).getIsCollect());
		collectionService.update(collectionList.get(0));
		
	
    	joke = jokeService.findJokeById(joke.getJokeID());
    	
    	Map<String,String> map = new HashMap<String,String>();	
    	if(collectionList.get(0).getIsCollect()){
			map.put("msg","取消收藏笑话");
		}else{
			map.put("msg","点击收藏笑话");
		}
    	String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	/**
	 * 
	 * @交往技巧的浏览与收藏方法
	 * @throws IOException
	 */
	
	public String CommunicationSkillsArticleHistory() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		if(collectionService.validation(collection.getMessageID(), detachedCriteria)){
			detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
			List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
			collectionService.delete(collectionList.get(0));
			collectionService.save(collectionList.get(0));
			
			if(collectionList.get(0).getIsCollect()){
				map.put("msg","取消收藏文章");
			}else{
				map.put("msg","点击收藏文章");
			}
			
		}else{
			communicationSkillsArticle = communicationSkillsArticleService.findById(collection.getMessageID());
			user = userService.findById(userID);

			collection.setIsCollect(false);
			collection.setTitle(communicationSkillsArticle.getTitle());
			collection.setImage(communicationSkillsArticle.getImage());
			collection.setType("交往技巧");
			collection.setUser(user);
			collectionService.save(collection);
			
			map.put("msg","点击收藏文章");
			
			
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
		
	}
	
	
	public String CommunicationSkillsArticleCollection() throws IOException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		collectionList.get(0).setIsCollect(!collectionList.get(0).getIsCollect());
		collectionService.update(collectionList.get(0));
		
	
		communicationSkillsArticle = communicationSkillsArticleService.findById(communicationSkillsArticle.getCommunicationSkillsArticleID());
    	
    	Map<String,String> map = new HashMap<String,String>();	
    	if(collectionList.get(0).getIsCollect()){
			map.put("msg","取消收藏文章");
		}else{
			map.put("msg","点击收藏文章");
		}
    	String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	/**
	 * 
	 * @自我提升的浏览与收藏方法
	 * @throws IOException
	 */
	

	public String SelfEnhancementArticleHistory() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		if(collectionService.validation(collection.getMessageID(), detachedCriteria)){
			detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
			List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
			collectionService.delete(collectionList.get(0));
			collectionService.save(collectionList.get(0));
			
			if(collectionList.get(0).getIsCollect()){
				map.put("msg","取消收藏文章");
			}else{
				map.put("msg","点击收藏文章");
			}
			
		}else{
			selfEnhancementArticle = selfEnhancementArticleService.findById(collection.getMessageID());
			user = userService.findById(userID);

			collection.setIsCollect(false);
			collection.setTitle(selfEnhancementArticle.getTitle());
			collection.setImage(selfEnhancementArticle.getImage());
			collection.setType("自我提升");
			collection.setUser(user);
			collectionService.save(collection);
			
			map.put("msg","点击收藏文章");
			
			
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
		
	}
	
	
	public String SelfEnhancementArticleCollection() throws IOException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		collectionList.get(0).setIsCollect(!collectionList.get(0).getIsCollect());
		collectionService.update(collectionList.get(0));
		
	
		selfEnhancementArticle = selfEnhancementArticleService.findById(selfEnhancementArticle.getSelfEnhancementArticleID());
    	
    	Map<String,String> map = new HashMap<String,String>();	
    	if(collectionList.get(0).getIsCollect()){
			map.put("msg","取消收藏文章");
		}else{
			map.put("msg","点击收藏文章");
		}
    	String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	/**
	 * 
	 * @话语的浏览与收藏方法
	 * @throws IOException
	 */
	

	public String UtteranceHistory() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		if(collectionService.validation(collection.getMessageID(),detachedCriteria)){
			detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
			List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
			collectionService.delete(collectionList.get(0));
			collectionService.save(collectionList.get(0));
			
			if(collectionList.get(0).getIsCollect()){
				map.put("msg","取消收藏话语");
			}else{
				map.put("msg","点击收藏话语");
			}
			
		}else{
			utterance = utteranceService.findById(collection.getMessageID());
			user = userService.findById(userID);

			collection.setIsCollect(false);
			collection.setTitle(utterance.getMessage());
			collection.setImage("/images/tree-hole2.jpg");
			collection.setType("匿名树洞");
			collection.setUser(user);
			collectionService.save(collection);
			
			map.put("msg","点击收藏话语");
			
			
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
		
	}
	
	
	public String UtteranceCollection() throws IOException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");
		detachedCriteria.add(Restrictions.eq("user.userID", userID));
		detachedCriteria.add(Restrictions.eq("messageID", collection.getMessageID()));
		List<Collection> collectionList = collectionService.findAllCollection(detachedCriteria);
		collectionList.get(0).setIsCollect(!collectionList.get(0).getIsCollect());
		collectionService.update(collectionList.get(0));
		
	
		utterance = utteranceService.findById(utterance.getUtteranceID());
    	
    	Map<String,String> map = new HashMap<String,String>();	
    	if(collectionList.get(0).getIsCollect()){
			map.put("msg","取消收藏话语");
		}else{
			map.put("msg","点击收藏话语");
		}
    	String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
}
