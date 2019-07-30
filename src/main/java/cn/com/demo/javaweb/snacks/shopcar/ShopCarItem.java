package cn.com.demo.javaweb.snacks.shopcar;

import cn.com.demo.javaweb.snacks.vo.FoodVO;

public class ShopCarItem {
    private FoodVO food;
    private int count;
	public FoodVO getFood() {
		return food;
	}
	public void setFood(FoodVO food) {
		this.food = food;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
    
    
}
