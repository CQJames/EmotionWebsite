package com.ew.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Collection;
import com.ew.entity.CollectionPage;
import com.ew.service.CollectionService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("collectionBackstageAction")
@Scope("prototype")
public class CollectionBackstageAction extends ActionSupport implements ModelDriven<Collection> {

	/**
	 * 交往技巧文章action
	 */
	private static final long serialVersionUID = -4257242217011379268L;

	private Collection collection;
	
	private CollectionService collectionService;

	@Resource(name = "collection")
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
	@Resource(name = "collectionService")
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}



	@Override
	public Collection getModel() {
		// TODO Auto-generated method stub
		return collection;
	}

	// 接收分页的数据
	// 接收当前页数和每页显示的记录数，这两个值在easyUI中已经有规定。page当前页数，rows每页显示记录数
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

	public String findAll() throws IOException {
		// 创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Collection.class);
		detachedCriteria.createCriteria("user");

		if (searchName != null) {
			detachedCriteria.add(Restrictions.like("title", "%" + searchName+ "%"));
		}
		// 调用业务层
		CollectionPage collectionPage = collectionService.findByPage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", collectionPage.getTotalCount());
		map.put("rows", collectionPage.getList());

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"communicationSkillsComment","selfEnhancementComment","reply","collection"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;

	}

	public String delete() throws IOException {
		System.out.println("删除");
		Map<String, String> map = new HashMap<String, String>();
		try {
			collection = collectionService.findById(collection.getCollectionID());
			collectionService.delete(collection);
			map.put("msg", "用户信息删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息删除异常!");
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}

	public String findById() throws IOException {

		collection = collectionService.findById(collection.getCollectionID());
		String json = new Gson().toJson(collection);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}

}
