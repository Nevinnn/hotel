package cn.itcast.service;

import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

public interface IFoodService {
	
	Food findById(int id);
	
	void getAll(PageBean<Food> pb);
}
