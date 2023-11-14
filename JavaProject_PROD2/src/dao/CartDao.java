package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.Cart;

public class CartDao {
	private static CartDao singleTon = null;

	private CartDao() {
	};

	public static CartDao getInstance() {
		if (singleTon == null) {
			singleTon = new CartDao();
		}
		return singleTon;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public void cartAdd(List<Object> param) {
		String sql = "insert into java_cart (no, id, prod_no, yn) values(JC_SEQ.nextval, ?,?,?)";
		jdbc.update(sql, param);
	}

	public List<Cart> cartList(List<Object> param) {
		String sql = "select * from java_cart where id = ? and yn='y'";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Cart.class);
	}
	
	public List<Cart> tempList(List<Object> param) {
		String sql = "select c.* from java_cart c, java_prod p\r\n" + 
					"where c.prod_no = p.no \r\n" + 
					"and id = ?\r\n" + 
					"and yn='n'\r\n" + 
					"and p.cnt !=0";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Cart.class);
	}
}
