package cn.com.demo.javaweb.snacks.shopcar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.demo.javaweb.snacks.service.IFoodService;
import cn.com.demo.javaweb.snacks.service.ServiceFactory;
import cn.com.demo.javaweb.snacks.vo.FoodVO;

public class ShopCar {
	private Map<Integer, ShopCarItem> itemMap = new HashMap<Integer, ShopCarItem>();
	private IFoodService foodService = ServiceFactory.buildFactory().createFoodService();

//  1 添加商品
	public void addSnacks(int fdId, int count) {
		// 获取以前得item
		ShopCarItem item = this.itemMap.get(fdId);
		// 根据是否存在，不同操作
		if (item == null) {
			item = new ShopCarItem();
			FoodVO food = this.foodService.searchFoodById(fdId);
			item.setFood(food);
			item.setCount(count);
			itemMap.put(fdId, item);
		} else {
			item.setCount(item.getCount() + count);
		}

	}

//    2 删除商品
	public void removeSnacks(int fdId) {
		this.itemMap.remove(fdId);
	}

//    3 修改数量
	public void updateSnacks(int fdId, int count) {
		ShopCarItem item = this.itemMap.get(fdId);
		if(item == null) {
			item = new ShopCarItem();
			FoodVO food = this.foodService.searchFoodById(fdId);
			item.setFood(food);
			this.itemMap.put(fdId, item);
		}
		// 如果有，更新以前得数字
		// 如果没有，添加添加商品
		item.setCount(count);
	}

//    4 清空购物车
	public void clear() {
       this.itemMap.clear();
	}

//    5 查询购物车列表
	public List<ShopCarItem> getItems() {
		List<ShopCarItem> items = null;
		
		Collection<ShopCarItem> values = this.itemMap.values();
		if(values != null) {
			items = new ArrayList<ShopCarItem>();
			items.addAll(values);
		}
		
		return items;
	}
	
	public int getCount() {
		return this.itemMap.size();
	}
}
