package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import print.Print;
import service.AdminService;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.Admin;
import vo.Member;

public class AdminController extends Print {

	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	private MemberService mbService = MemberService.getInstance();
	private AdminService  adService = AdminService.getInstance();
	
	public View adminHome() {
		List<Member> list = mbService.selectList();
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("1. 홈");
		System.out.println("2. 다시보기");
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.HOME;
		case 2:
			return View.ADMIN_HOME;
		default :
			return View.ADMIN_HOME;
		}
	}

	public View adminLogin() {
		
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		boolean chk = adService.login(param);
		if(chk) {
			Admin ad = (Admin) sessionStorage.get("admin");
			System.out.println(ad.getName()+"님 환영합니다.");
			View v = (View) sessionStorage.get("view");
			return v;
		}else {
			System.out.println("해당 아이디가 없습니다.");
			return View.ADMIN_HOME;
		}
	}

	public View admin() {
		
		printAdmin();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.ADMIN_LOGIN;
		case 2:
			return View.PROD_LIST;
		case 3:
			return View.PROD_INSERT;
		case 4:
			return View.PROD_UPDATE;
		case 5:
			return View.PROD_DEL;
		case 6:
			sessionStorage.clear();
			return View.HOME;
		default :
			return View.ADMIN_HOME;
		}
	}
}
