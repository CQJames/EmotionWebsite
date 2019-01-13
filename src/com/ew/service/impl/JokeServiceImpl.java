package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.JokeDao;
import com.ew.entity.Joke;
import com.ew.entity.JokePage;
import com.ew.service.JokeService;

@Service("jokeService")
public class JokeServiceImpl implements JokeService {

	private JokeDao jokeDao;
	
	private JokePage jokePage;

	public JokeDao getJokeDao() {
		return jokeDao;
	}
	@Resource(name="jokeDao")
	public void setJokeDao(JokeDao jokeDao) {
		this.jokeDao = jokeDao;
	}
	
	@Resource(name="jokePage")
	public void setJokePage(JokePage jokePage) {
		this.jokePage = jokePage;
	}

	@Override
	public List<Joke> findAllShowJoke() {
		
		List<Joke> jokeList = jokeDao.findAllShowJoke();
		
		return jokeList;
	}
	
	@Override
	public Joke findJokeById(String jokeID) {
		
		Joke jokeTemp = jokeDao.findJokeById(jokeID);
		
		return jokeTemp;
	}
	
	@Override
	public Joke updateAddClicks(String jokeID) {
		
		Joke jokeTemp = jokeDao.findJokeById(jokeID);
		//加一个赞
		jokeTemp.setClicks(jokeTemp.getClicks() + 1);
		//更新数据库
		jokeDao.update(jokeTemp);
		
		return jokeTemp;
	}
	@Override
	public JokePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		jokePage.setCurrPage(page);
		//设置每页显示记录数
		jokePage.setPageSize(rows);
		//查询总的客户数
		Integer totalCount = jokeDao.findCount(detachedCriteria);
		//设置总记录数
		jokePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		jokePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Joke> list = jokeDao.findByPage(detachedCriteria,begin,rows);
		jokePage.setList(list);
		return jokePage;
	}

	@Override
	public Joke findById(String jokeID) {
		return jokeDao.findById(jokeID);
	}

	@Override
	public void delete(Joke joke) {
		jokeDao.delete(joke.getJokeID());	
	}

	@Override
	public void update(Joke joke) {
		jokeDao.update(joke);	
		
	}

	@Override
	public void save(Joke joke) {
		jokeDao.save(joke);	
		
	}
}
