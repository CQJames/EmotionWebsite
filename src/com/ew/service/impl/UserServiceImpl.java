package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.ew.dao.UserDao;
import com.ew.entity.SexRatio;
import com.ew.entity.User;
import com.ew.entity.UserPage;
import com.ew.exception.ForgetPwd1Exception;
import com.ew.exception.ForgetPwd2Exception;
import com.ew.exception.UserLoginException;
import com.ew.exception.UserRegisterException;
import com.ew.service.UserService;
import com.ew.utils.EncryptionFactory;
import com.ew.utils.JavaMailFactory;
import com.ew.utils.SecurityCodeFactory;

@Service("userService")
public class UserServiceImpl implements UserService {
    
	private UserDao userDao;
	private UserPage userPage;
	private SexRatio sexRatio;

	@Resource(name="userPage")
	public void setUserPage(UserPage userPage) {
		this.userPage = userPage;
	}
	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Resource(name="sexRatio")
	public void setSexRatio(SexRatio sexRatio) {
		this.sexRatio = sexRatio;
	}

	@Override
	public UserPage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		userPage.setCurrPage(page);
		//设置每页显示记录数
		userPage.setPageSize(rows);
		//查询总的客户数
		Integer totalCount = userDao.findCount(detachedCriteria);
		//设置总记录数
		userPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		userPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<User> list = userDao.findByPage(detachedCriteria,begin,rows);
		userPage.setList(list);
		return userPage;
	}

	@Override
	public void save(User user) {
		//加密用户密码
		user.setPassword(EncryptionFactory.md5(user.getPassword()));
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		if(user.getPassword()!=null){
			//加密用户密码
			user.setPassword(EncryptionFactory.md5(user.getPassword()));
		}
		userDao.update(user);	
	}

	@Override
	public void delete(User user) {
		userDao.delete(user.getUserID());
	}

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User saveRegister(User user) {
		
		// 调用dao根据用户名查询是否有User对象
		User existUser = userDao.findByUserUsername(user.getUsername());
		if(existUser != null)
		{
			// 用户名已存在
			throw new UserRegisterException("用户已存在");
		}
		// 加密用户密码
		user.setPassword(EncryptionFactory.md5(user.getPassword()));
		// 给用户默认头像
		user.setIcon("/images/nothing.png");
		// 设置isDelete为0，即否
		user.setIsDelete(false);
		//保存客户信息到数据库
		userDao.save(user);
		// 将数据库User对象返回
		return user;
	}

	@Override
	public User findByUserUsername(User user) {
		
		// 调用dao根据用户名查询是否有User对象
		User existUser = userDao.findByUserUsername(user.getUsername());
		
		return existUser;
	}

	@Override
	public User login(User user) {
		
		// 调用dao根据用户名查询是否有User对象
		User existUser = userDao.findByUserUsername(user.getUsername());
		if(existUser == null)
		{
			// 用户名不存在
			throw new UserLoginException("用户名不存在");
		}
		// 加密用户密码
		user.setPassword(EncryptionFactory.md5(user.getPassword()));
		// 对比密码是否一致
		if(!existUser.getPassword().equals(user.getPassword()))
		{
			// 密码不一致
			throw new UserLoginException("密码错误");
		}
		// 将数据库User对象返回
		return existUser;
	}

	@Override
	public List<User> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		return userDao.findByIdExcludeComment(detachedCriteria);
	}

	@Override
	public Boolean validation(String username) {
		List<String> list = userDao.findAllUserUsername();
		for(String value:list){
            if(value.equals(username)){
            	return true;
            }    	
        }
        return false;
	}

	@Override
	public User findUserEmail(User user) {
		//通过名字找用户
		User userTemp = userDao.findByUserUsername(user.getUsername());
		if(userTemp == null){
			throw new ForgetPwd1Exception("用户名不存在");
		}
		return userTemp;
	}

	@Override
	public String sendEmailCheckCode(User user) {
		String email = user.getEmail();
		//判断用户是否已绑定邮箱
		if(email == null){
			throw new ForgetPwd2Exception("您没有绑定邮箱,联系我们或重新注册");
		}
		//生成验证码
		String securityCode = SecurityCodeFactory.getSecurityCode();
		//把验证码通过邮箱发送给用户
		int res = JavaMailFactory.sendMyEmail(new String[]{email}, "验证码", "您获取的验证码为：   " + securityCode);
		if(res == 0){
			throw new ForgetPwd2Exception("验证码发送至您的邮箱失败");
		}
		//往上一层返回验证码
		return securityCode;
	}

	@Override
	public User saveResetPassword(User user) {
		//加密
		user.setPassword(EncryptionFactory.md5(user.getPassword()));	
		userDao.update(user);
		return user;
	}

	@Override
	public void modifyUserMessage(User userTemp) {
		userDao.update(userTemp);
	}

	@Override
	public SexRatio findSexRatio(DetachedCriteria detachedCriteria) {
		//总人数
		Integer sum = userDao.findCount(detachedCriteria);
		//男人
		detachedCriteria.add(Restrictions.eq("sex", true));
		Integer men = userDao.findCount(detachedCriteria);
		//女人
		DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(User.class);
		detachedCriteria1.add(Restrictions.eq("sex", false));
		Integer women = userDao.findCount(detachedCriteria1);
		
		sexRatio.setMen(men);
		sexRatio.setWomen(women);
		sexRatio.setUnknow(sum - men - women);
		
		return sexRatio;
	}
    
}
