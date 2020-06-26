package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.FoodType;

public interface IFoodTypeService {

	void text(FoodType foodType);
	
	void save(FoodType foodType);

	void update(FoodType foodType);

	void delete(int id);

	FoodType findById(int id);

	List<FoodType> getAll();
	
	List<FoodType> getAll(String typeName);
}
