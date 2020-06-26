package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;
import cn.itcast.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{

	public DinnerTable findById(int id) {
		String sql ="SELECT * FROM dinnerTable where id=?";
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<DinnerTable> findbyStatus(TableStatus ts) {
		String sql ="SELECT * FROM dinnerTable WHERE tableStatus=?";
//		int status = -1;
//		
//		if(ts == TableStatus.Free){
//			status = 0;
//		}else{
//			status = 1;
//		}
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),ts.ordinal());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
