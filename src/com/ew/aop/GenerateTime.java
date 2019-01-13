package com.ew.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Repository;

import com.ew.entity.BaseEntity;

@Repository("generateTime")
public class GenerateTime{
	
	//为save方法增强生成保存和修改时间
	public void generateSaveTimeAndChangeTime(ProceedingJoinPoint point){
		//改变原方法参数的值
		//获取参数数组
		Object[] args = point.getArgs();
		//将第一个参数对象强转为BaseEntity对象
		BaseEntity baseEntity = (BaseEntity) args[0];
		//获取当前时间
		Date currentTime = new Date();
		baseEntity.setSaveTime(currentTime);
		baseEntity.setChangeTime(currentTime);
		//用改变后的参数执行目标方法
		try {
			point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	//为update方法增强生成修改时间
	public void generateChangeTime(ProceedingJoinPoint point){
		//改变原方法参数的值
		//获取参数数组
		Object[] args = point.getArgs();
		//将第一个参数对象强转为BaseEntity对象
		BaseEntity baseEntity = (BaseEntity) args[0];
		//获取当前时间
		Date currentTime = new Date();
		//生成新的修改时间
		baseEntity.setChangeTime(currentTime);
		//用改变后的参数执行目标方法
		try {
			point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
}