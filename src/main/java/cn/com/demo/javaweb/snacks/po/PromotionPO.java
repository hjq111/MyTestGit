package cn.com.demo.javaweb.snacks.po;

import java.sql.Timestamp;

public class PromotionPO {
    private int prmId;
    private String prmName;
    private Timestamp prmStartTime;
    private Timestamp prmEndTime;
    private double prmDiscount;
    private String prmDetail;
    
	public int getPrmId() {
		return prmId;
	}
	public void setPrmId(int prmId) {
		this.prmId = prmId;
	}
	public String getPrmName() {
		return prmName;
	}
	public void setPrmName(String prmName) {
		this.prmName = prmName;
	}
	public Timestamp getPrmStartTime() {
		return prmStartTime;
	}
	public void setPrmStartTime(Timestamp prmStartTime) {
		this.prmStartTime = prmStartTime;
	}
	public Timestamp getPrmEndTime() {
		return prmEndTime;
	}
	public void setPrmEndTime(Timestamp prmEndTime) {
		this.prmEndTime = prmEndTime;
	}
	public double getPrmDiscount() {
		return prmDiscount;
	}
	public void setPrmDiscount(double prmDiscount) {
		this.prmDiscount = prmDiscount;
	}
	public String getPrmDetail() {
		return prmDetail;
	}
	public void setPrmDetail(String prmDetail) {
		this.prmDetail = prmDetail;
	}
    
    
}
