package com.ew.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.SelfEnhancementCommentDao;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.User;

@Repository("selfEnhancementCommentDao")
public class SelfEnhancementCommentDaoImpl extends BaseDaoImpl<SelfEnhancementComment> implements SelfEnhancementCommentDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//利用Projections工具类的方法进行统计和分组
		//Projections.rowCount()--查询结果集中的记录条数
		detachedCriteria.setProjection(Projections.rowCount());//条件添加
		//Spring的框架提供了getHibernateTemplate ().findByCriteria(detachedCriteria)方法
		//可以很方便地根据DetachedCriteria来返回查询结果
		List<Long> list  = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		//取消查询总条数 以便等下进行正常分页查询
		detachedCriteria.setProjection(null);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelfEnhancementComment> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<SelfEnhancementComment> list = (List<SelfEnhancementComment>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
		for(int i=0;i<list.size();i++){
			list.get(i).getSelfEnhancementArticle().setSelfEnhancementComment(null);
			list.get(i).getUser().setCommunicationSkillsComment(null);
			list.get(i).getUser().setSelfEnhancementComment(null); 
		}			
		return list;
	}

	@Override
	public SelfEnhancementComment findById(DetachedCriteria detachedCriteria) {
		SelfEnhancementComment selfEnhancementComment = (SelfEnhancementComment) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
			selfEnhancementComment.getSelfEnhancementArticle().setSelfEnhancementComment(null);
			selfEnhancementComment.getUser().setCommunicationSkillsComment(null);
			selfEnhancementComment.getUser().setSelfEnhancementComment(null);
	
		return selfEnhancementComment;
	}
	
	@Override
	public List<SelfEnhancementComment> findByArticlePaging(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		//时间按最新排序
		detachedCriteria.addOrder(Order.desc("sub.saveTime"));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.selfEnhancementCommentID").as("selfEnhancementCommentID"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.applaud").as("applaud"));
		pList.add(Projections.property("sub.disapproval").as("disapproval"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("u.username").as("username"));
		pList.add(Projections.property("u.icon").as("icon"));
		detachedCriteria.setProjection(pList);
		List<Object[]> findByCriteria = (List<Object[]>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
		List<SelfEnhancementComment> selfEnhancementCommentList = new ArrayList<SelfEnhancementComment>();
		for(int i = 0 ; i < findByCriteria.size() ; i++) {
			Object[] objects = findByCriteria.get(i);
			String selfEnhancementCommentID = (String) objects[0];
			String message = (String) objects[1];
			Integer applaud = (Integer) objects[2];
			Integer disapproval = (Integer) objects[3];
			Date saveTime = (Date) objects[4];
			String username = (String) objects[5];
			String icon = (String) objects[6];
			SelfEnhancementComment selfEnhancementComment = new SelfEnhancementComment();
			selfEnhancementComment.setSelfEnhancementCommentID(selfEnhancementCommentID);
			selfEnhancementComment.setMessage(message);
			selfEnhancementComment.setApplaud(applaud);
			selfEnhancementComment.setDisapproval(disapproval);
			selfEnhancementComment.setSaveTime(saveTime);
			User user = new User();
			user.setUsername(username);
			user.setIcon(icon);
			selfEnhancementComment.setUser(user);
			selfEnhancementCommentList.add(selfEnhancementComment);
		}
		return selfEnhancementCommentList;
	}

}
