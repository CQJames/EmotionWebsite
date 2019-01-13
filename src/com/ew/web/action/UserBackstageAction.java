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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.SexRatio;
import com.ew.entity.User;
import com.ew.entity.UserPage;
import com.ew.service.UserService;
import com.ew.utils.UploadUtitls;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userBackstageAction")
@Scope("prototype")
public class UserBackstageAction extends ActionSupport implements ModelDriven<User> {
	
	/**
	 * 用户后台action
	 */
	private static final long serialVersionUID = 4220884060113828387L;
	
	private String checkCode;
	//模型驱动使用的对象
	private User user;
	private UserService userService;
	//接收当前页数和每页显示的记录数，这两个值在easyUI中已经有规定。page当前页数，rows每页显示记录数
	private Integer page;
	private Integer rows;
	// upload拦截器要求的名字规范，不能随便改，提供get/set
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String searchName;
	
	
	
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Resource(name="user")
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return user;
	}

	//注入Service
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Value("1")
	public void setPage(Integer page) {
		this.page = page;
	}

	@Value("3")
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	//查询所有用户，去掉评论属性
	public String findAllExcludeComment() throws IOException{
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class,"sub");
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("username","%"+searchName+"%"));
		}
		//调用业务层
		UserPage userPage = userService.findByPage(detachedCriteria,page,rows);
		//使用jsonlib或者fastjson
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", userPage.getTotalCount());
		map.put("rows", userPage.getList());
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String findByIdExcludeComment() throws IOException {
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class,"sub");
		//根据ID查
		detachedCriteria.add(Restrictions.eq("sub.userID", user.getUserID()));
		//由于criteria只能返回集合
		List<User> userTemp = userService.findByIdExcludeComment(detachedCriteria);
		//获得唯一用户对象
		user = userTemp.get(0);
		String json = new Gson().toJson(user);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}

	public String findById() throws IOException {
		user = userService.findById(user.getUserID());
		String json = new Gson().toJson(user);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String save() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		try{
			if(userService.validation(user.getUsername())){
				map.put("msg", "该用户已存在!");
			}
			else{
				userService.save(user);
				map.put("msg", "保存成功!");	
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg", "保存失败!");
		}
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String update() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.update(user);
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

	public String delete() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			user = userService.findById(user.getUserID());
			userService.delete(user);
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
	
	public String findSexRatio() throws IOException {
		
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		    
		SexRatio sexRatio = userService.findSexRatio(detachedCriteria);
		
		String json = new Gson().toJson(sexRatio);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
}
