package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import print.Print;
import service.ProdService;
import util.ScanUtil;
import util.View;
import vo.Admin;
import vo.Member;
import vo.Prod;

public class ProdController extends Print{
	
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	private ProdService   psService = ProdService.getInstance();
	
	public View prodInsert() {
		Admin mb = (Admin) sessionStorage.get("admin");
		if(mb == null) {
			sessionStorage.put("view", View.PROD_INSERT);
			return View.ADMIN_LOGIN;
		}
		List<Object> param = new ArrayList<>();
		param.add(ScanUtil.nextLine("name >> "));
		param.add(ScanUtil.nextLine("content>>"));
		param.add(ScanUtil.nextInt("price>>"));
		param.add(ScanUtil.nextInt("cnt >>"));
		
		printProdList();
		psService.prodInsert(param);
		return View.ADMIN_HOME;
	}
	
	public void printProdList() {
		List<Prod> list = psService.prodList();
		printProdList(list);
	}

	public View prodList() {
		printProdList();
		printProdListMenu();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.PROD_LIST;
		case 2:
			return View.ADMIN_HOME;
		default :
			return View.PROD_LIST;
		}
	}

	public View prodUpdate() {
		printProdList();
		
		String yn = ScanUtil.nextLine("수정 y/n");
		if(!yn.equalsIgnoreCase("y")) {
			return View.ADMIN_HOME;
		}
		
		int no = ScanUtil.nextInt("수정될 번호를 선택하세요");
		
		printProdUpdateDetail();
		int select = ScanUtil.nextInt("수정 정보를 선택하세요");
		List<Object> param = new ArrayList<>();
		if(select == 1 || select == 5) {
			param.add(ScanUtil.nextLine("name >> "));
		}
		if(select == 2 || select == 5) {
			param.add(ScanUtil.nextLine("content>>"));
		}
		if(select == 3 || select == 5) {
			param.add(ScanUtil.nextInt("price>>"));
		}
		if(select == 4 || select == 5) {
			param.add(ScanUtil.nextInt("cnt >>"));
		}
		param.add(no);
		psService.update(param, select);
		
		return View.PROD_LIST;
	}

	public View prodDel() {
		printProdList();

		String yn = ScanUtil.nextLine("삭제 y/n");
		if (!yn.equalsIgnoreCase("y")) {
			return View.ADMIN_HOME;
		}
		int no = ScanUtil.nextInt("삭제될 번호를 선택하세요");
		List<Object> param = new ArrayList();
		param.add(no);
		psService.prodDel(param);
		return View.PROD_LIST;
	}

	public View prodBuy() {
		Member mb = (Member) sessionStorage.get("member");
		if(mb == null) {
			sessionStorage.put("view", View.PROD_BUY);
			return View.MEMBER_LOGIN;
		}
		printProdBuy();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.CART_ADD;
		case 2:
			return View.CART_ADD_TEMP;
		default :
			return View.MEMBER;
		}
	}

	
}
