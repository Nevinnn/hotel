package cn.itcast.dao;

import java.util.List;
import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

public interface IFoodDao {
 
	void getAll(PageBean<Food> pb);
	
	int getTotalCount(PageBean<Food> pb);
	
	Food findById(int id);
	
}
