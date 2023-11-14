package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import print.Print;
import service.CartService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import vo.Cart;
import vo.Member;
import vo.Prod;

public class CartController extends Print {
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	CartService   csService = CartService.getInstance();
	ProdService   psService = ProdService.getInstance();
	
	public View cartAdd() {
		Member member = (Member)sessionStorage.get("member");
		if(member == null) {
			sessionStorage.put("view", View.CART_ADD);
			return View.MEMBER_LOGIN;
		}
		List<Prod> list = psService.prodListNotDel();
		printProdList(list);
		
		int no = ScanUtil.nextInt("구메 번호를 선택하세요");
		Prod selProd = new Prod();
		for (Prod p : list) {
			if(p.getNo() == no) selProd = p;
		}
		List<Object> param = new ArrayList();
		param.add(member.getId());
		param.add(selProd.getNo());
		param.add("y");
		csService.cartAdd(param);
		List<Object> param2 = new ArrayList();
		param2.add(selProd.getCnt()-1);
		param2.add(selProd.getNo());
		psService.update(param2, 4);
		
		return View.CART_LIST;
	}

	public View cartAddTemp() {
		Member member = (Member)sessionStorage.get("member");
		if(member == null) {
			sessionStorage.put("view", View.CART_ADD_TEMP);
			return View.MEMBER_LOGIN;
		}
		List<Prod> list = psService.prodListNotDel();
		printProdList(list);
		
		int no = ScanUtil.nextInt("구메 번호를 선택하세요");
		Prod selProd = new Prod();
		for (Prod p : list) {
			if(p.getNo() == no) selProd = p;
		}
		List<Object> param = new ArrayList();
		param.add(member.getId());
		param.add(selProd.getNo());
		param.add("n");
		csService.cartAdd(param);
		return View.CART_LIST;
	}

	public View cartList() {
		Member member = (Member)sessionStorage.get("member");
		if(member == null) {
			sessionStorage.put("view", View.CART_LIST);
			return View.MEMBER_LOGIN;
		}
		List<Object> param = new ArrayList<Object>();
		param.add(member.getId());
		List<Cart> l = csService.cartList(param);
		printCartList(l);
		prinCartHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.MEMBER;
		default :
			return View.CART_LIST;
		}
	}

	public View cartTempList() {
		Member member = (Member)sessionStorage.get("member");
		if(member == null) {
			sessionStorage.put("view", View.CART_TEMP);
			return View.MEMBER_LOGIN;
		}
		List<Object> param = new ArrayList<Object>();
		param.add(member.getId());
		List<Cart> l = csService.tempList(param);
		printCartList(l);
		prinCartHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.MEMBER;
		default :
			return View.CART_LIST;
		}
	}
}
