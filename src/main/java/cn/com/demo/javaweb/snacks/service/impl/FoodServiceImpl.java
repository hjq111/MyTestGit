package cn.com.demo.javaweb.snacks.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.com.demo.javaweb.snacks.dao.DAOFactory;
import cn.com.demo.javaweb.snacks.dao.IFoodDAO;
import cn.com.demo.javaweb.snacks.dao.IPictureDAO;
import cn.com.demo.javaweb.snacks.dao.IPromotionDAO;
import cn.com.demo.javaweb.snacks.po.FoodPO;
import cn.com.demo.javaweb.snacks.po.PicturePO;
import cn.com.demo.javaweb.snacks.po.PromotionPO;
import cn.com.demo.javaweb.snacks.service.IFoodService;
import cn.com.demo.javaweb.snacks.util.Constant;
import cn.com.demo.javaweb.snacks.vo.FoodVO;
import cn.com.demo.javaweb.snacks.vo.PromotionVO;

public class FoodServiceImpl implements IFoodService {
	private IPictureDAO picDAO = DAOFactory.buildDAOFactory().createPictureDAO();
	private IFoodDAO foodDAO = DAOFactory.buildDAOFactory().createFoodDAO();
	private IPromotionDAO promDAO = DAOFactory.buildDAOFactory().createPromotionDAO();

	@Override
	public FoodVO searchFoodById(int fdId) {
		FoodVO vo = null;
//	      * 1 fdId找到FoodPO对象
		FoodPO po = this.foodDAO.findFoodById(fdId);
		vo = new FoodVO(po);
//	      * 2 foodVO添加vo得picUrl和销售量
		int salesCount = this.foodDAO.findFoodSaleCount(fdId);
		vo.setSalesCount(salesCount);
//	      * 3 private List<String> bigPicUrl;
		List<PicturePO> bigPicPO = this.picDAO.findPictures(fdId, Constant.PIC_TYPE_BIGIMG);
		if (bigPicPO != null) {
			List<String> bigPicUrlList = new ArrayList<String>();
			for (PicturePO picPO : bigPicPO) {
				bigPicUrlList.add(picPO.getPicUrl());
			}
			vo.setBigPicUrl(bigPicUrlList);
		}
//			4 private List<String> detailPicUrl;
		bigPicPO = this.picDAO.findPictures(fdId, Constant.PIC_TYPE_DETAIL_IMG);
		if (bigPicPO != null) {
			List<String> bigPicUrlList = new ArrayList<String>();
			for (PicturePO picPO : bigPicPO) {
				bigPicUrlList.add(picPO.getPicUrl());
			}
			vo.setDetailPicUrl(bigPicUrlList);
		}
//			5 private List<PromotionVO> promList;
//			  Promotion相关得DAO PO VO
		List<PromotionPO> promPOList = this.promDAO.findPromotions(fdId);
		List<PromotionVO> promVOList = new ArrayList<PromotionVO>();
		for(PromotionPO po1:promPOList) {
			promVOList.add(new PromotionVO(po1));
		}
		vo.setPromList(promVOList);
//			6 private double promPrice;
		// 获取折扣
		double discountPrice = vo.getFdPrice();
		if(vo.getPromList() != null && vo.getPromList().size()>0) {
			discountPrice = discountPrice * vo.getPromList().get(0).getPrmDiscount();
		}
		return vo;
	}

	@Override
	public List<FoodVO> searchFoods(int currPageNo, String keyword) {
		List<FoodVO> foodList = null;

//		 * 1 校验页码的有效性
//		 *   1）基于DAO查询出符号条件的所有食品数量
//		 *   2）根据每页显示的食品数，可以计算出总页码
//		 *   3）根据总页码，调整有效的当前页码
		int sumCount = this.foodDAO.foodCount(keyword);
		// 计算总页码数
		int pageNumber = sumCount / Constant.PAGE_RECORD_COUNT;
		if (sumCount % Constant.PAGE_RECORD_COUNT != 0) {
			pageNumber++;
		}
		// 调整当前得有效页码
		if (currPageNo <= 0) {
			currPageNo = 1;
		} else if (currPageNo >= pageNumber) {
			currPageNo = pageNumber;
		}
//		 * 2 获取有效页码对应的食品List（PO）
//		 *   1）调用DAO条件查询方法，获取PO List（当前页码）
		List<FoodPO> fdPOList = this.foodDAO.searchCurrPageFoods(currPageNo, Constant.PAGE_RECORD_COUNT, keyword);
//		 * 3 将POList转变成VOList
		foodList = new ArrayList<FoodVO>();
		FoodVO vo = null;
		for (FoodPO po : fdPOList) {
			vo = new FoodVO(po);
			foodList.add(vo);
			// 添加vo得picUrl和销售量
			List<PicturePO> picList = this.picDAO.findPictures(vo.getFdId(), Constant.PIC_TYPE_LISTIMG);// 1表示列表图片
			if (picList != null && picList.size() == 1) {
				vo.setListPicUrl(picList.get(0).getPicUrl());
			}
			// 销售量
			int saleCount = this.foodDAO.findFoodSaleCount(vo.getFdId());
			vo.setSalesCount(saleCount);
		}

		return foodList;
	}

	public void setPicDAO(IPictureDAO picDAO) {
		this.picDAO = picDAO;
	}

	public void setFoodDAO(IFoodDAO foodDAO) {
		this.foodDAO = foodDAO;
	}
}
