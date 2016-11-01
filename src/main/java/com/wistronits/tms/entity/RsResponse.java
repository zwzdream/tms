package com.wistronits.tms.entity;

public class RsResponse {
    private boolean success=true;
    private int recordCount;
    private Object payLoad;
    private String errorMessage;
    private Object otherData0;
    
    public RsResponse(){}
    
    static public final RsResponse BLANKSUCCESS=new RsResponse(0,null);
    static public final RsResponse TIMEOUT=getErrorInstance("Session Not Exist"); 
    
    public RsResponse(int recordCount,Object payLoad) {
        this.recordCount=recordCount;
        this.payLoad=payLoad;
    }
    
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
}
