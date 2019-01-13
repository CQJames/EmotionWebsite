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
import com.ew.dao.ReplyDao;
import com.ew.entity.Reply;
import com.ew.entity.User;

@Repository("replyDao")
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao {
	
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
	public List<Reply> findByPage(DetachedCriteria detachedCriteria,Integer begin, Integer rows) {
		List<Reply> list = (List<Reply>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
		for(int i=0;i<list.size();i++){
			list.get(i).getUtterance().setReply(null);
			list.get(i).getUser().setCommunicationSkillsComment(null);
			list.get(i).getUser().setSelfEnhancementComment(null);
		}			
		
		return list;
	}

	@Override
	public List<Reply> findByUtterancePaging(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		//时间按最新排序
		detachedCriteria.addOrder(Order.desc("sub.saveTime"));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.replyID").as("replyID"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("u.username").as("username"));
		pList.add(Projections.property("u.icon").as("icon"));
		detachedCriteria.setProjection(pList);
		List<Object[]> findByCriteria = (List<Object[]>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
		List<Reply> replyList = new ArrayList<Reply>();
		for(int i = 0 ; i < findByCriteria.size() ; i++) {
			Object[] objects = findByCriteria.get(i);
			String replyID = (String) objects[0];
			String message = (String) objects[1];
			Date saveTime = (Date) objects[2];
			String username = (String) objects[3];
			String icon = (String) objects[4];
			Reply reply = new Reply();
			reply.setReplyID(replyID);
			reply.setMessage(message);
			reply.setSaveTime(saveTime);
			User user = new User();
			user.setUsername(username);
			user.setIcon(icon);
			reply.setUser(user);
			replyList.add(reply);
		}
		return replyList;
	}
}
