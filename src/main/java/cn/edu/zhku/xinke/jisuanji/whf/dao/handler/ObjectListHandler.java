package cn.edu.zhku.xinke.jisuanji.whf.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class ObjectListHandler implements ResultSetHandler<List<Object>>{

	@Override
	public List<Object> handle(ResultSet rs) throws SQLException {
		List<Object> list = Collections.emptyList();
		boolean empty = true;
		while(rs.next()){
			if(empty == true){
				list = new ArrayList<>();
				empty = false;
			}
			Object obj = rs.getObject(1);
			list.add(obj);
		}
		return list;
	}

}
