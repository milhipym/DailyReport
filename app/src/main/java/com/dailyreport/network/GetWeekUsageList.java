package com.dailyreport.network;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWeekUsageList {

    @SerializedName("countPerPage")
    @Expose
    private Integer countPerPage;
    @SerializedName("currentPageNo")
    @Expose
    private Integer currentPageNo;
    @SerializedName("rowState")
    @Expose
    private Object rowState;
    @SerializedName("totCnt")
    @Expose
    private Integer totCnt;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("rnum")
    @Expose
    private Integer rnum;
    @SerializedName("frstIpmnEmpno")
    @Expose
    private Object frstIpmnEmpno;
    @SerializedName("frstInptDttm")
    @Expose
    private Object frstInptDttm;
    @SerializedName("fnalAmdrEmpno")
    @Expose
    private Object fnalAmdrEmpno;
    @SerializedName("fnalUpdtDttm")
    @Expose
    private String fnalUpdtDttm;
    @SerializedName("messageShowYN")
    @Expose
    private Object messageShowYN;
    @SerializedName("planNo")
    @Expose
    private Object planNo;
    @SerializedName("rltdSno")
    @Expose
    private Object rltdSno;
    @SerializedName("sno")
    @Expose
    private Object sno;
    @SerializedName("dsmBzDvcd")
    @Expose
    private Object dsmBzDvcd;
    @SerializedName("elrnSigPrgStpDvcd")
    @Expose
    private Object elrnSigPrgStpDvcd;
    @SerializedName("procCn")
    @Expose
    private Object procCn;
    @SerializedName("srchStrDt")
    @Expose
    private Object srchStrDt;
    @SerializedName("srchFinDt")
    @Expose
    private Object srchFinDt;
    @SerializedName("elrnSigTsHistMngtcd")
    @Expose
    private Object elrnSigTsHistMngtcd;
    @SerializedName("rgtrNm")
    @Expose
    private Object rgtrNm;
    @SerializedName("dailyCnt")
    @Expose
    private String dailyCnt;


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "{" +
                "fnalUpdtDttm=" + fnalUpdtDttm +
                ", dailyCnt='" + dailyCnt + '\'' +
                '}';
    }


    public Integer getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(Integer countPerPage) {
        this.countPerPage = countPerPage;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Object getRowState() {
        return rowState;
    }

    public void setRowState(Object rowState) {
        this.rowState = rowState;
    }

    public Integer getTotCnt() {
        return totCnt;
    }

    public void setTotCnt(Integer totCnt) {
        this.totCnt = totCnt;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getRnum() {
        return rnum;
    }

    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }

    public Object getFrstIpmnEmpno() {
        return frstIpmnEmpno;
    }

    public void setFrstIpmnEmpno(Object frstIpmnEmpno) {
        this.frstIpmnEmpno = frstIpmnEmpno;
    }

    public Object getFrstInptDttm() {
        return frstInptDttm;
    }

    public void setFrstInptDttm(Object frstInptDttm) {
        this.frstInptDttm = frstInptDttm;
    }

    public Object getFnalAmdrEmpno() {
        return fnalAmdrEmpno;
    }

    public void setFnalAmdrEmpno(Object fnalAmdrEmpno) {
        this.fnalAmdrEmpno = fnalAmdrEmpno;
    }

    public String getFnalUpdtDttm() {
        return fnalUpdtDttm;
    }

    public void setFnalUpdtDttm(String fnalUpdtDttm) {
        this.fnalUpdtDttm = fnalUpdtDttm;
    }

    public Object getMessageShowYN() {
        return messageShowYN;
    }

    public void setMessageShowYN(Object messageShowYN) {
        this.messageShowYN = messageShowYN;
    }

    public Object getPlanNo() {
        return planNo;
    }

    public void setPlanNo(Object planNo) {
        this.planNo = planNo;
    }

    public Object getRltdSno() {
        return rltdSno;
    }

    public void setRltdSno(Object rltdSno) {
        this.rltdSno = rltdSno;
    }

    public Object getSno() {
        return sno;
    }

    public void setSno(Object sno) {
        this.sno = sno;
    }

    public Object getDsmBzDvcd() {
        return dsmBzDvcd;
    }

    public void setDsmBzDvcd(Object dsmBzDvcd) {
        this.dsmBzDvcd = dsmBzDvcd;
    }

    public Object getElrnSigPrgStpDvcd() {
        return elrnSigPrgStpDvcd;
    }

    public void setElrnSigPrgStpDvcd(Object elrnSigPrgStpDvcd) {
        this.elrnSigPrgStpDvcd = elrnSigPrgStpDvcd;
    }

    public Object getProcCn() {
        return procCn;
    }

    public void setProcCn(Object procCn) {
        this.procCn = procCn;
    }

    public Object getSrchStrDt() {
        return srchStrDt;
    }

    public void setSrchStrDt(Object srchStrDt) {
        this.srchStrDt = srchStrDt;
    }

    public Object getSrchFinDt() {
        return srchFinDt;
    }

    public void setSrchFinDt(Object srchFinDt) {
        this.srchFinDt = srchFinDt;
    }

    public Object getElrnSigTsHistMngtcd() {
        return elrnSigTsHistMngtcd;
    }

    public void setElrnSigTsHistMngtcd(Object elrnSigTsHistMngtcd) {
        this.elrnSigTsHistMngtcd = elrnSigTsHistMngtcd;
    }

    public Object getRgtrNm() {
        return rgtrNm;
    }

    public void setRgtrNm(Object rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    public String getDailyCnt() {
        return dailyCnt;
    }

    public void setDailyCnt(String dailyCnt) {
        this.dailyCnt = dailyCnt;
    }

}