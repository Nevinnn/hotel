package cn.itcast.dao;
import java.util.List;

import cn.itcast.entity.FoodType;

public interface IFoodTypeDao {
	void text(FoodType foodType);
	
	void save(FoodType foodType);
	
	void update(FoodType foodType);

	void delete(int id);

	FoodType findById(int id);
	
	List<FoodType> getAll();
	
	List<FoodType> getAll(String typeName);
}
