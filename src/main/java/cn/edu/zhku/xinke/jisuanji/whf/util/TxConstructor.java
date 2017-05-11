package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 事务集成工具，commit提交事务，需要在service中手动调用
 * @author Paul 
 * 
 * */
public class TxConstructor {
	
	private List<JdbcAction> actions = new ArrayList<>();
	
	private static final JdbcUtil JDBC_UTIL = JdbcUtil.getInstance();
	
	public void addAction(JdbcAction action){
		actions.add(action);
	}
	
	public void commit(){
		JDBC_UTIL.execute(actions);
	}

}
