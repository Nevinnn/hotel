package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.IFoodDao;
import cn.itcast.entity.Food;
import cn.itcast.utils.Condition;
import cn.itcast.utils.JdbcUtils;
import cn.itcast.utils.PageBean;

public class FoodDao implements IFoodDao{

	public void getAll(PageBean<Food> pb) {
		//獲取條件對象
		Condition condition = pb.getCondition();
		//條件之類別id
		int typeId = condition.getFoodTypeId();
		//條件之菜品名稱
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" f.id,");
		sb.append("f.foodName,");
		sb.append("f.price,");
		sb.append("f.mprice,");
		sb.append("f.remark,");
		sb.append("f.img,");
		sb.append("f.foodType_id,");
		sb.append("t.typeName");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append("foodtype t");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=t.id");

		//儲存查詢條件對應的值
		List<Object> list = new ArrayList<Object>();
		/////////拼接查詢條件
		//類別ID條件
		if(typeId > 0){
			sb.append(" AND f.foodType_id=?");
			list.add(typeId);
		}
		//菜品名稱
		if(foodName != null && !"".equals(foodName.trim())){
			sb.append(" AND f.foodName LIKE ?");
			list.add(foodName);
		}
		//////分頁條件
		sb.append(" LIMIT ?,?");
		
		///////判斷:當前頁<1--設置當前頁為1   當前頁>總數頁 設當前頁為總頁數//////////
		int totalCount = getTotalCount(pb);
		pb.setTotalCount(totalCount);
		
		if (pb.getCurrentPage() < 1) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		
		int index = (pb.getCurrentPage() - 1) * pb.getCurrentPage();
		int count = pb.getPageCount();
		
		list.add(index);//起始行
		list.add(count);//查詢返回的行

		try {
			List<Food> pageData = JdbcUtils.getQuerrRunner().query(sb.toString(), new BeanListHandler<Food> (Food.class),list.toArray());
			pb.setPageData(pageData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		
	}

	public int getTotalCount(PageBean<Food> pb) {
		//獲取條件對象
		Condition condition = pb.getCondition();
		//條件之類別id
		int typeId = condition.getFoodTypeId();
		//條件之菜品名稱
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" count(*)");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append("foodtype t");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=t.id");

		//儲存查詢條件對應的值
		List<Object> list = new ArrayList<Object>();
		/////////拼接查詢條件
		//類別ID條件
		if(typeId > 0){
			sb.append(" AND f.foodType_id=?");
			list.add(typeId);
		}
		//菜品名稱
		if(foodName != null && !"".equals(foodName.trim())){
			sb.append(" AND f.foodName LIKE ?");
			list.add(foodName);
		}
		    
		
		try {
			 Long num = JdbcUtils.getQuerrRunner().query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			 return num.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Food findById(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" f.id,");
		sb.append("f.foodName,");
		sb.append("f.price,");
		sb.append("f.mprice,");
		sb.append("f.remark,");
		sb.append("f.img,");
		sb.append("f.foodType_id");
		sb.append("t.typeName");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append("foodType t");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=t.id");

		try {
			return JdbcUtils.getQuerrRunner().query(sb.toString(), new BeanHandler<Food> (Food.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
