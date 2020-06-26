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
		//��������H
		Condition condition = pb.getCondition();
		//�������Oid
		int typeId = condition.getFoodTypeId();
		//���󤧵�~�W��
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

		//�x�s�d�߱����������
		List<Object> list = new ArrayList<Object>();
		/////////�����d�߱���
		//���OID����
		if(typeId > 0){
			sb.append(" AND f.foodType_id=?");
			list.add(typeId);
		}
		//��~�W��
		if(foodName != null && !"".equals(foodName.trim())){
			sb.append(" AND f.foodName LIKE ?");
			list.add(foodName);
		}
		//////��������
		sb.append(" LIMIT ?,?");
		
		///////�P�_:��e��<1--�]�m��e����1   ��e��>�`�ƭ� �]��e�����`����//////////
		int totalCount = getTotalCount(pb);
		pb.setTotalCount(totalCount);
		
		if (pb.getCurrentPage() < 1) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		
		int index = (pb.getCurrentPage() - 1) * pb.getCurrentPage();
		int count = pb.getPageCount();
		
		list.add(index);//�_�l��
		list.add(count);//�d�ߪ�^����

		try {
			List<Food> pageData = JdbcUtils.getQuerrRunner().query(sb.toString(), new BeanListHandler<Food> (Food.class),list.toArray());
			pb.setPageData(pageData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		
	}

	public int getTotalCount(PageBean<Food> pb) {
		//��������H
		Condition condition = pb.getCondition();
		//�������Oid
		int typeId = condition.getFoodTypeId();
		//���󤧵�~�W��
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" count(*)");
		sb.append(" FROM");
		sb.append(" food f,");
		sb.append("foodtype t");
		sb.append(" WHERE 1=1");
		sb.append(" AND f.foodType_id=t.id");

		//�x�s�d�߱����������
		List<Object> list = new ArrayList<Object>();
		/////////�����d�߱���
		//���OID����
		if(typeId > 0){
			sb.append(" AND f.foodType_id=?");
			list.add(typeId);
		}
		//��~�W��
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
