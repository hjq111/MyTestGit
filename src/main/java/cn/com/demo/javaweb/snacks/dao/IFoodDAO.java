package cn.com.demo.javaweb.snacks.dao;

import java.util.List;

import cn.com.demo.javaweb.snacks.po.FoodPO;

public interface IFoodDAO {
	public FoodPO findFoodById(int fdId);
    public int foodCount(String keyword);
    
    /**
     * @param currPageNo int 当前页码
     * @param number int 每页显示的食品数量
     * @param keyword String 查询条件（食品名称 参数 模糊查询）
     * */
    public List<FoodPO> searchCurrPageFoods(int currPageNo, int number, String keyword);
    
    /**
     * 统计指定零食得销售量
     * */
    public int findFoodSaleCount(int fdId);
}
