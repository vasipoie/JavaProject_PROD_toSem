package controller;

import java.util.HashMap;
import java.util.Map;

import print.Print;
import service.CartService;
import util.ScanUtil;
import util.View;

public class Controller extends Print {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	MemberController mc = new MemberController();
	AdminController  ac = new AdminController();
	ProdController   pc = new ProdController();
	CartController   cc = new CartController();
	
	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN_HOME:
				view = ac.admin();
				break;
			case PROD_LIST:
				view = pc.prodList();
				break;
			case PROD_INSERT:
				view = pc.prodInsert();
				break;
			case PROD_UPDATE:
				view = pc.prodUpdate();
				break;
			case PROD_DEL:
				view = pc.prodDel();
				break;
			case ADMIN_LOGIN:
				view = ac.adminLogin();
				break;
			case MEMBER:
				view = mc.memberHome();
				break;	
			case MEMBER_JOIN:
				view = mc.memberJoin();
				break;	
			case MEMBER_UPDATE:
				view = mc.memberUpdate();
				break;	
			case MEMBER_OUT:
				view = mc.memberOut();
				break;	
			case MEMBER_LOGIN:
				view = mc.memberLogin();
				break;	
			case PROD_BUY:
				view = pc.prodBuy();
				break;	
			case CART_ADD:
				view = cc.cartAdd();
				break;	
			case CART_ADD_TEMP:
				view = cc.cartAddTemp();
				break;	
			case CART_LIST:
				view = cc.cartList();
				break;	
			case CART_TEMP:
				view = cc.cartTempList();
				break;		
			}
		}
	}


	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.ADMIN_HOME;
		case 2:
			return View.MEMBER;
		default :
			return View.HOME;
		}
	}
}
