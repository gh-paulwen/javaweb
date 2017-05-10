package cn.edu.zhku.xinke.jisuanji.whf.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class ObjectHandler implements ResultSetHandler<Object>{

	@Override
	public Object handle(ResultSet rs) throws SQLException {
		Object obj = null;
		if(rs.next()){
			obj = rs.getObject(1);
		}
		return obj;
	}

}
