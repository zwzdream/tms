package com.wistronits.tms.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestDemo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5111385523488642890L;

	private int id;
	
	private String field1;
	
	private String field2;

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date field3;
	
	@NumberFormat(style=Style.CURRENCY)
	private double field4;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getField4() {
		return field4;
	}

	public void setField4(double field4) {
		this.field4 = field4;
	}

	public Date getField3() {
		return field3;
	}

	public void setField3(Date field3) {
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
}
