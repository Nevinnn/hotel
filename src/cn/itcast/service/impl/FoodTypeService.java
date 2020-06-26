package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IFoodTypeDao;
import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService{

	private IFoodTypeDao foodTypeDao = BeanFactory.getInstance("foodtypeDao", IFoodTypeDao.class);

	public void save(FoodType foodType) {
		try {
			foodTypeDao.save(foodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void update(FoodType foodType) {
		try {
			foodTypeDao.update(foodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void delete(int id) {
		try {
			foodTypeDao.delete(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public FoodType findById(int id) {
		try {
			return foodTypeDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll() {
		try {
			return foodTypeDao.getAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll(String typeName) {
		try {
			return foodTypeDao.getAll(typeName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public void text(FoodType foodType) {
		try {
			foodTypeDao.text(foodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
