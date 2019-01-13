package com.ew.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsArticlePage;
import com.ew.service.CommunicationSkillsArticleService;
import com.ew.utils.UploadUtitls;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller("communicationSkillsArticleBackstageAction")
@Scope("prototype")
public class CommunicationSkillsArticleBackstageAction extends ActionSupport implements ModelDriven<CommunicationSkillsArticle> {

	/**
	 * 交往技巧文章action
	 */
	private static final long serialVersionUID = -4257242217011379268L;

	private CommunicationSkillsArticle communicationSkillsArticle;
	private CommunicationSkillsArticleService communicationSkillsArticleService;
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

	@Resource(name = "communicationSkillsArticle")
	public void setCommunicationSkillsArticle(CommunicationSkillsArticle communicationSkillsArticle) {
		this.communicationSkillsArticle = communicationSkillsArticle;
	}

	@Resource(name = "communicationSkillsArticleService")
	public void setCommunicationSkillsArticleService(CommunicationSkillsArticleService communicationSkillsArticleService) {
		this.communicationSkillsArticleService = communicationSkillsArticleService;
	}

	public CommunicationSkillsArticle getModel() {
		return communicationSkillsArticle;
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
		
		System.out.println("sasafs");
		// 创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsArticle.class);
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("title","%"+searchName+"%"));
		}
		// 调用业务层
		CommunicationSkillsArticlePage communicationSkillsArticlePage = communicationSkillsArticleService.findByPageBackstage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", communicationSkillsArticlePage.getTotalCount());
		map.put("rows", communicationSkillsArticlePage.getList());
		
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"communicationSkillsComment"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);
		
		
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;

	}
	
	public String findByIdExcludeComment() throws IOException {
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsArticle.class);
		//根据ID查
		detachedCriteria.add(Restrictions.eq("communicationSkillsArticleID", communicationSkillsArticle.getCommunicationSkillsArticleID()));
		//由于criteria只能返回集合
		List<CommunicationSkillsArticle> communicationSkillsArticleTemp = communicationSkillsArticleService.findByIdExcludeComment(detachedCriteria);
		//获得唯一用户对象
		communicationSkillsArticle = communicationSkillsArticleTemp.get(0);
		

		String json = new Gson().toJson(communicationSkillsArticle);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String save() throws IOException{
		System.out.println("waw");
		Map<String,String> map = new HashMap<String,String>();
		try{
				communicationSkillsArticleService.save(communicationSkillsArticle);
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
			communicationSkillsArticle = communicationSkillsArticleService.findById(communicationSkillsArticle.getCommunicationSkillsArticleID());
			communicationSkillsArticleService.delete(communicationSkillsArticle);
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
			communicationSkillsArticleService.update(communicationSkillsArticle);
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
		communicationSkillsArticle = communicationSkillsArticleService.findById(communicationSkillsArticle.getCommunicationSkillsArticleID());
		String json = new Gson().toJson(communicationSkillsArticle);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	
	public String findIdByTitle() throws IOException{
		System.out.println(communicationSkillsArticle.getTitle());
		List<String> matchID = communicationSkillsArticleService.findIdByTitle(communicationSkillsArticle.getTitle());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", matchID);
		String json = new Gson().toJson(map);
		System.out.println(json);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	
	
	public String upLoad() throws IOException{
		System.out.println(upload);
		System.out.println(uploadFileName);
		System.out.println(uploadContentType);
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
			System.out.println(json);
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().println(json);
		}
		return NONE;
	}

////////////////////////////////////////////////////////////////////
	private File imgFile;
	private String imgFileContentType;
	private String localUrl;
	private String dir;
	private String imgFileFileName;

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String kingeditor() throws IOException{
		//System.out.println(dir);
		System.out.println(imgFile);
		//System.out.println(imgFileContentType);
		//System.out.println(localUrl);
		System.out.println(imgFileFileName);
		// 如果有图片
		if(imgFile != null){
			//获取到tomcat的WEB-INF/classes
			String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
			//截取掉包括/WEB-INF/后面的内容
			String rootPath = classpath.substring(0, classpath.indexOf("/WEB-INF/"));
			//webapps/项目名/resource
			String path = rootPath + "/resource";
			//生成唯一文件名ID
			String uuidFileName = UploadUtitls.getUuidFileName(imgFileFileName);
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
			FileUtil.copyFile(imgFile, dictFile);
			//截取包括/resource/后的路径
			String MKV = URL.substring(URL.indexOf("/resource/"), URL.length());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", 0);
			map.put("url", "."+MKV);
			String json = new Gson().toJson(map);
			System.out.println(json);
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().println(json);
		}
		return NONE;
		
	}
	

}
