package cn.com.demo.javaweb.snacks.vo;

import java.sql.Timestamp;

import cn.com.demo.javaweb.snacks.po.PromotionPO;

public class PromotionVO extends PromotionPO{
    private PromotionPO po = null;
    public PromotionVO(PromotionPO po) {
    	this.po = po;
    }
	@Override
	public int getPrmId() {
		return po.getPrmId();
	}
	@Override
	public String getPrmName() {
		return po.getPrmName();
	}
	@Override
	public Timestamp getPrmStartTime() {
		return po.getPrmStartTime();
	}
	@Override
	public Timestamp getPrmEndTime() {
		return po.getPrmEndTime();
	}
	@Override
	public double getPrmDiscount() {
		return po.getPrmDiscount();
	}
	@Override
	public String getPrmDetail() {
		return po.getPrmDetail();
	}
}
