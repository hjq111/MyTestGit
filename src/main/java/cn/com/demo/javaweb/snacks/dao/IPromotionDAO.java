package cn.com.demo.javaweb.snacks.dao;

import java.sql.Timestamp;
import java.util.List;

import cn.com.demo.javaweb.snacks.po.PromotionPO;

public interface IPromotionDAO {
	/**
	 * 正在进行中得活动
	 * */
    public List<PromotionPO> findPromotions(int fdId);
    /**
     * 查询除同begin-end重叠得所有活动
     * */
    public List<PromotionPO> findPromotions(int fdId, Timestamp begin, Timestamp end);
}
