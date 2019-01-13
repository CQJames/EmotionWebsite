package com.ew.entity;

import org.springframework.stereotype.Component;

@Component("sexRatio")
public class SexRatio {

	private Integer men;
	private Integer women;
	private Integer unknow;
	
	public Integer getMen() {
		return men;
	}
	public void setMen(Integer men) {
		this.men = men;
	}
	public Integer getWomen() {
		return women;
	}
	public void setWomen(Integer women) {
		this.women = women;
	}
	public Integer getUnknow() {
		return unknow;
	}
	public void setUnknow(Integer unknow) {
		this.unknow = unknow;
	}
		
}
