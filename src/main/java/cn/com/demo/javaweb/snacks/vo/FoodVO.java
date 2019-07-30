package cn.com.demo.javaweb.snacks.vo;

import java.util.List;

import cn.com.demo.javaweb.snacks.po.FoodPO;

public class FoodVO extends FoodPO {
	private FoodPO foodPO;
	// 扩展得属性（列表页码）
	private String listPicUrl;
	private int salesCount;
	// 详细信息
	private List<String> bigPicUrl;
	private List<String> detailPicUrl;
	private List<PromotionVO> promList;
	private double promPrice;

	public FoodVO(FoodPO foodPO) {
		this.foodPO = foodPO;
	}

	public List<String> getBigPicUrl() {
		return bigPicUrl;
	}

	public void setBigPicUrl(List<String> bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}

	public List<String> getDetailPicUrl() {
		return detailPicUrl;
	}

	public void setDetailPicUrl(List<String> detailPicUrl) {
		this.detailPicUrl = detailPicUrl;
	}

	public List<PromotionVO> getPromList() {
		return promList;
	}

	public void setPromList(List<PromotionVO> promList) {
		this.promList = promList;
	}

	public double getPromPrice() {
		return promPrice;
	}

	public void setPromPrice(double promPrice) {
		this.promPrice = promPrice;
	}

	@Override
	public int getFdId() {
		return foodPO.getFdId();
	}

	@Override
	public String getFdName() {
		return foodPO.getFdName();
	}

	@Override
	public double getFdPrice() {
		return foodPO.getFdPrice();
	}

	@Override
	public String getFdTaste() {
		return foodPO.getFdTaste();
	}

	@Override
	public String getFdPackae() {
		return foodPO.getFdPackae();
	}

	@Override
	public int getFdInventory() {
		return foodPO.getFdInventory();
	}

	@Override
	public String getFdDetail() {
		return foodPO.getFdDetail();
	}

	public String getListPicUrl() {
		return listPicUrl;
	}

	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

}
