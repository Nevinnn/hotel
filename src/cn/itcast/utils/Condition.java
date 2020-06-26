package cn.itcast.utils;

public class Condition {

	//菜的類別做為條件
	private int foodTypeId;
	//菜的名稱作為條件
	private String foodName;
	
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
