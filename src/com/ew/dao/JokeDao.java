package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Joke;

public interface JokeDao extends BaseDao<Joke> {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Joke> findByPage(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

	List<Joke> findAllShowJoke();

	Joke findJokeById(String jokeID);
	

}
