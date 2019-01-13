package com.ew.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Utterance;
import com.ew.entity.UtterancePage;
import com.ew.service.UtteranceService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("utteranceBackstageAction")
@Scope("prototype")
public class UtteranceBackstageAction extends ActionSupport implements ModelDriven<Utterance> {

	/**
	 * 交往技巧文章action
	 */
	private static final long serialVersionUID = -4257242217011379268L;

	private Utterance utterance;
	private UtteranceService utteranceService;
	
	@Resource(name = "utterance")
	public void setUtterance(Utterance utterance) {
		this.utterance = utterance;
	}
	@Resource(name = "utteranceService")
	public void setUtteranceService(UtteranceService utteranceService) {
		this.utteranceService = utteranceService;
	}
	
	@Override
	public Utterance getModel() {
		// TODO Auto-generated method stub
		return utterance;
	}
	

	//接收分页的数据
	//接收当前页数和每页显示的记录数，这两个值在easyUI中已经有规定。page当前页数，rows每页显示记录数
	private Integer page = 1;
	private Integer rows = 3;
	private String searchName;
	

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String findAllExcludeComment() throws IOException {
		
		// 创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Utterance.class);
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("message","%"+searchName+"%"));
		}
		// 调用业务层
		UtterancePage utterancePage = utteranceService.findByPageBackstage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", utterancePage.getTotalCount());
		map.put("rows", utterancePage.getList());
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"reply"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);
		
		
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;

	}
	
	public String findByIdExcludeReply() throws IOException {
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Utterance.class);
		//根据ID查
		detachedCriteria.add(Restrictions.eq("utteranceID", utterance.getUtteranceID()));
		//由于criteria只能返回集合
		List<Utterance> utteranceTemp = utteranceService.findByIdExcludeReply(detachedCriteria);
		//获得唯一用户对象
		utterance = utteranceTemp.get(0);
		
		String json = new Gson().toJson(utterance);
		
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String save() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		try{
				utteranceService.save(utterance);
				map.put("msg", "保存成功!");	
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg", "保存失败!");
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String delete() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			utterance = utteranceService.findById(utterance.getUtteranceID());
			utteranceService.delete(utterance);
			map.put("msg", "用户信息删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息删除异常!");
		}	
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}	
	
	public String update() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			utteranceService.update(utterance);
			map.put("msg", "用户信息编辑成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息编辑异常!");
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String findById() throws IOException {
		utterance = utteranceService.findById(utterance.getUtteranceID());
		String json = new Gson().toJson(utterance);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String findTenIsSelected() throws IOException{
		
		// 创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Utterance.class);
		
		detachedCriteria.add(Restrictions.eq("isSelected",true));
		// 调用业务层
		UtterancePage utterancePage = utteranceService.findByPageBackstage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", utterancePage.getTotalCount());
		map.put("rows", utterancePage.getList());
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"reply"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);
		
		
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		
		return NONE;
	}
	
	public String  cancelIsSelected() throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		try {
			utterance = utteranceService.findById(utterance.getUtteranceID());
			utterance.setIsSelected(false);
			utteranceService.update(utterance);
			map.put("msg", "用户信息编辑成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息编辑异常!");
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}

}
