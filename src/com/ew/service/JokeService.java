package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.Joke;
import com.ew.entity.JokePage;

public interface JokeService {

	List<Joke> findAllShowJoke();

	Joke findJokeById(String jokeID);

	Joke updateAddClicks(String jokeID);

	JokePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	Joke findById(String jokeID);

	void delete(Joke joke);

	void update(Joke joke);

	void save(Joke joke);

}
