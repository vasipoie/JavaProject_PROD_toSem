package service;

import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.CartDao;
import vo.Cart;

public class CartService {
	private static CartService singleTon = null;
	private CartService() {
	};

	public static CartService getInstance() {
		if (singleTon == null) {
			singleTon = new CartService();
		}
		return singleTon;
	}

	CartDao cartDao = CartDao.getInstance();

	public void cartAdd(List<Object> param) {
		cartDao.cartAdd(param);
	}

	public List<Cart> cartList(List<Object> param) {
		return cartDao.cartList(param);
	}
	
	public List<Cart> tempList(List<Object> param) {
		return cartDao.tempList(param);
	}
}
