package com.ew.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementArticlePage;
import com.ew.service.SelfEnhancementArticleService;
import com.ew.utils.UploadUtitls;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("selfEnhancementArticleBackstageAction")
@Scope("prototype")
public class SelfEnhancementArticleBackstageAction extends ActionSupport implements ModelDriven<SelfEnhancementArticle> {

	/**
	 * 交往技巧文章action
	 */
	private static final long serialVersionUID = -4257242217011379268L;

	private SelfEnhancementArticle selfEnhancementArticle;
	private SelfEnhancementArticleService selfEnhancementArticleService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Resource(name = "selfEnhancementArticle")
	public void setSelfEnhancementArticle(SelfEnhancementArticle selfEnhancementArticle) {
		this.selfEnhancementArticle = selfEnhancementArticle;
	}

	@Resource(name = "selfEnhancementArticleService")
	public void setSelfEnhancementArticleService(SelfEnhancementArticleService selfEnhancementArticleService) {
		this.selfEnhancementArticleService = selfEnhancementArticleService;
	}

	public SelfEnhancementArticle getModel() {
		return selfEnhancementArticle;
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
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementArticle.class);
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("title","%"+searchName+"%"));
		}
		// 调用业务层
		SelfEnhancementArticlePage selfEnhancementArticlePage = selfEnhancementArticleService.findByPageBackstage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", selfEnhancementArticlePage.getTotalCount());
		map.put("rows", selfEnhancementArticlePage.getList());
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"selfEnhancementComment"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);

		
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;

	}
	
	public String findByIdExcludeComment() throws IOException {
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementArticle.class);
		//根据ID查
		detachedCriteria.add(Restrictions.eq("selfEnhancementArticleID", selfEnhancementArticle.getSelfEnhancementArticleID()));
		//由于criteria只能返回集合
		List<SelfEnhancementArticle> selfEnhancementArticleTemp = selfEnhancementArticleService.findByIdExcludeComment(detachedCriteria);
		//获得唯一用户对象
		selfEnhancementArticle = selfEnhancementArticleTemp.get(0);
		
		String json = new Gson().toJson(selfEnhancementArticle);
		
		
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String save() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		try{
			selfEnhancementArticleService.save(selfEnhancementArticle);
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
			selfEnhancementArticle = selfEnhancementArticleService.findById(selfEnhancementArticle.getSelfEnhancementArticleID());
			selfEnhancementArticleService.delete(selfEnhancementArticle);
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
			selfEnhancementArticleService.update(selfEnhancementArticle);
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
		selfEnhancementArticle = selfEnhancementArticleService.findById(selfEnhancementArticle.getSelfEnhancementArticleID());
		String json = new Gson().toJson(selfEnhancementArticle);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String upLoad() throws IOException{
		// 如果有图片
		if(upload != null){
			//获取到tomcat的WEB-INF/classes
			String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
			//截取掉包括/WEB-INF/后面的内容
			String rootPath = classpath.substring(0, classpath.indexOf("/WEB-INF/"));
			//webapps/项目名/resource
			String path = rootPath + "/resource";
			//生成唯一文件名ID
			String uuidFileName = UploadUtitls.getUuidFileName(uploadFileName);
			//生成该唯一ID目录名
			String realPath = UploadUtitls.getPath(uuidFileName);
			//webapps/项目名/resource/目录名/
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()){
				//如果目录文件夹不存在，生成文件夹
				file.mkdirs();
			}
			//具体文件路径
			String URL = url + "/" + uuidFileName;
			//生成目标文件
			File dictFile = new File(URL);
			//复制上传文件给目标文件
			FileUtil.copyFile(upload, dictFile);
			//截取包括/resource/后的路径
			String MKV = URL.substring(URL.indexOf("/resource/"), URL.length());
			//将相对路径put到map中
			Map<String, String> map = new HashMap<String, String>();
			map.put("url", MKV);
			String json = new Gson().toJson(map);
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().println(json);
		}
		return NONE;
	}
	

}
