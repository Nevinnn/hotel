package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;

public interface IDinnerTableDao {
	
	List<DinnerTable> findbyStatus(TableStatus ts);
	
	DinnerTable findById(int id);
	
}
