package com.wistronits.tms.entity;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public class RsResponse<T> {
    private boolean success=true;
    private int recordCount;
    private Object payLoad;
    private String errorMessage;
    private Object otherData0;
    
    private int iTotalDisplayRecords;
    private int iTotalRecords;
    private List<T> aaData;
    private String sEcho;
    
    public RsResponse(){}
    
    @SuppressWarnings("rawtypes")
	static public final RsResponse BLANKSUCCESS=new RsResponse(0,null);
    @SuppressWarnings("rawtypes")
	static public final RsResponse TIMEOUT=getErrorInstance("Session Not Exist"); 
    static public final int PAGE_SIZE = 2;
    
    public RsResponse(int recordCount,Object payLoad) {
        this.recordCount=recordCount;
        this.payLoad=payLoad;
    }
    
    @SuppressWarnings("rawtypes")
	static public RsResponse getErrorInstance(String errorMessage) {
        RsResponse ret=new RsResponse(0,null);
        ret.setSuccess(false);
        ret.setErrorMessage(errorMessage);
        return ret;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }

    public Object getOtherData0() {
        return otherData0;
    }

    public void setOtherData0(Object otherData0) {
        this.otherData0 = otherData0;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
    
}
