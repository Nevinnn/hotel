package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;

public interface IDinnerTableService {
	
	List<DinnerTable> findNoUseTable();
	
	DinnerTable findById(int id);
}
