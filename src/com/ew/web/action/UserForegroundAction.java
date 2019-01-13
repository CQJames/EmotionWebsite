package com.ew.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.util.FileUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.User;
import com.ew.exception.UserEmailException;
import com.ew.exception.UserLoginException;
import com.ew.exception.UserPasswordException;
import com.ew.exception.UserRegisterException;
import com.ew.service.UserService;
import com.ew.utils.EncryptionFactory;
import com.ew.utils.UploadUtitls;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userForegroundAction")
@Scope("prototype")
public class UserForegroundAction extends ActionSupport implements ModelDriven<User> {
	
	/**
	 * 用户前台action
	 */
	private static final long serialVersionUID = -7718293646682960091L;
	
	private String repassword;
	private String newPassword;
	private String checkCode;
	//模型驱动使用的对象
	private User user;
	private UserService userService;
	
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

	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public User getUser() {
		return user;
	}

	@Resource(name="user")
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	// 注册
	public String register() throws Exception {
		
		//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("username", user.getUsername());
        request.put("password", user.getPassword());
        //判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new UserRegisterException("验证码错误");
        }
		// 调用userService执行注册操作
    	User userTemp = userService.saveRegister(user);
   		// 将返回的user对象放入session域作为登录标识
   		ActionContext.getContext().getSession().put("user", userTemp);
   		// 转发到项目的首页
   		return "toForegroundIndex";  
	}
	
    public String login() throws Exception {
		
		//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("username", user.getUsername());
        request.put("password", user.getPassword());
        //判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new UserLoginException("验证码错误");
        }
        // 调用userService执行登录操作
    	User userTemp = userService.login(user);
   		// 将返回的user对象放入session域作为登录标识
   		ActionContext.getContext().getSession().put("user", userTemp);
   		// 转发到项目的首页
   		return "toForegroundIndex";  
	}
    
    public String loginOut() throws Exception {		
   		// 将用户登录消去
   		ActionContext.getContext().getSession().put("user", "");
   		// 转发到原页面
   		return "toForegroundIndex";  
	}
    
    public String modifyUserMessage() throws Exception {
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User userTemp = (User) session.get("user");
    	userTemp.setUsername(user.getUsername());
    	userTemp.setName(user.getName());
    	userTemp.setSex(user.getSex());
    	userTemp.setPhoneNumber(user.getPhoneNumber());
    	userTemp.setIcon(user.getIcon());
    	userService.modifyUserMessage(userTemp);
    	session.put("user", userTemp);
    	return "toPersonInformation";
    }
    
    public String modifyUserPassword() throws Exception {
    	//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("password", user.getPassword());
        request.put("newPassword", newPassword);
        request.put("repassword", repassword);
        //逻辑
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User userTemp = (User) session.get("user");
    	if(!EncryptionFactory.md5(user.getPassword()).equals(userTemp.getPassword())){
    		//原密码错误
    		throw new UserPasswordException("原密码错误");
    	}
    	//设置新密码
    	userTemp.setPassword(newPassword);
    	//传到Service
    	User updateUser = userService.saveResetPassword(userTemp);
    	//重新放入session
    	session.put("user", updateUser);
    	//成功标志
    	request.put("success", "true");
    	return "toPersonPassword";
    }
    
    public String modifyUserEmail() throws Exception {
    	//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("email", user.getEmail());
        //判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new UserEmailException("验证码错误");
        }
        Map<String, Object> session = ActionContext.getContext().getSession();
    	User userTemp = (User) session.get("user");
    	userTemp.setEmail(user.getEmail());
        userService.modifyUserMessage(userTemp);
        session.put("user", userTemp);
        //成功标志
    	request.put("success", "true");
		return "toPersonEmail";
    }
    
    public String upLoad() throws IOException{
    	Map<String, Object> session = ActionContext.getContext().getSession();
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
			
			User userTempIcon = (User) session.get("user");
			userTempIcon.setIcon(MKV);
			session.put("user", userTempIcon);
		}
		
		return "toPersonInformation";
	}
}
