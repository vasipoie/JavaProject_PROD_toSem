package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.Prod;

public class ProdDao {
	private static ProdDao singleTon = null;

	private ProdDao() {
	};

	public static ProdDao getInstance() {
		if (singleTon == null) {
			singleTon = new ProdDao();
		}
		return singleTon;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public void prodInsert(List<Object> param) {
		String sql = "insert into  java_prod (no, name,content,price,cnt)\r\n" + 
					"VALUES(JP_SEQ.nextval, ?,?,?,?)";
		jdbc.update(sql, param);
	}

	public List<Prod> prodList() {
		String sql = "select * from java_prod";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}

	public void update(List<Object> param, int select) {
		String sql_front = "update java_prod set";
		if(select == 1 || select == 5) {
			sql_front += " name = ? ";
		}
		if(select == 2 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " content = ? ";
		}
		if(select == 3 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " price = ? ";
		}
		if(select == 4 || select == 5) {
			if(select == 5) sql_front +=",";
			sql_front += " cnt = ? ";
		}
		String sql_where = " where no=? ";
		String sql = sql_front + sql_where;
		jdbc.update(sql, param);
	}

	public void delete(List<Object> param) {
		String sql= "update java_prod set delyn = 'y' where no = ?";
		jdbc.update(sql, param);
	}

	public List<Prod> prodListNotDel() {
		String sql = "select * from java_prod where delyn is null and cnt != 0";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}

}
