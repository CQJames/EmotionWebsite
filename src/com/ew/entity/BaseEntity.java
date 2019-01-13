package com.ew.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{

	/**
	 * 基本实体，所有其他实体继承这实体
	 */
	private static final long serialVersionUID = 6096050328780487334L;
	// 保存时间
	private Date saveTime;
	// 修改时间
	private Date changeTime;
	
	public Date getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	
}
