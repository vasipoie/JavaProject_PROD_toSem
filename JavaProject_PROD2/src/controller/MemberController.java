package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.Member;

public class MemberController extends Print {
	
	static public Map<String, Object> sessionStorage = Controller.sessionStorage;
	MemberService mbService = MemberService.getInstance();
	
	public View memberLogin() {
		List<Object> param = new ArrayList<Object>();
		System.out.println("----- 로그인 -------- ");
		param.add(ScanUtil.nextLine("id>>"));
		param.add(ScanUtil.nextLine("pass>>"));
		Member mb = mbService.login(param);
		if(mb == null) {
			System.out.println("회원 정보가 없습니다.");
			String selyn = ScanUtil.nextLine("회원가입 하시겠습니까(y/n)");
			if(selyn.equalsIgnoreCase("y")) {
				return View.MEMBER_JOIN;
			}
		}
		sessionStorage.put("member",mb);
		View v = (View) sessionStorage.get("view");
		return v;
	}
	
	public View memberOut() {
		Member mb = (Member) sessionStorage.get("member");
		if(mb == null) {
			sessionStorage.put("view", View.MEMBER_OUT);
			return View.MEMBER_LOGIN;
		}
		String syn = ScanUtil.nextLine(mb.getName()+ "님 회원 탈퇴하시겠습니까? y/n");
		if(syn.equalsIgnoreCase("y")) {
			List<Object> param = new ArrayList();
			param.add(mb.getNo());
			mbService.delete(param);
		}
		
		return View.MEMBER;
	}

	
	public View memberUpdate() {
		Member mb = (Member) sessionStorage.get("member");
		if(mb == null) {
			sessionStorage.put("view", View.MEMBER_UPDATE);
			return View.MEMBER_LOGIN;
		}
//		printMbUpdate(mb);
		String syn = ScanUtil.nextLine("수정하시겠습니까 y/n");
		if(syn.equalsIgnoreCase("y")) {
//			printMBUpdateDetail();
			int select = ScanUtil.nextInt("수정 정보를 선택하세요");
			List<Object> param = new ArrayList<>();
			if(select == 1 || select == 5) {
				param.add(ScanUtil.nextLine("pass>>"));
			}
			if(select == 2 || select == 5) {
				param.add(ScanUtil.nextLine("name>>"));
			}
			if(select == 3 || select == 5) {
				param.add(ScanUtil.nextLine("age>>"));
			}
			if(select == 4 || select == 5) {
				param.add(ScanUtil.nextLine("room>>"));
			}
			param.add(mb.getId());
			param.add(mb.getPass());
			mbService.update(param, select);
		}
		return View.MEMBER;
		
	}

	public View memberJoin() {
		List<Object> param = new ArrayList<Object>();
		param.add(ScanUtil.nextLine("id>>"));
		param.add(ScanUtil.nextLine("pass>>"));
		param.add(ScanUtil.nextLine("name>>"));
		param.add(ScanUtil.nextLine("age>>"));
		boolean chk = mbService.sign(param);
		if(chk) {
			Member ad = (Member) sessionStorage.get("member");
			System.out.println("403호에 가입을 환영합니다.");
			return View.MEMBER;
		}else {
			System.out.println("회원 가입에 실패하였습니다.");
			return View.MEMBER_JOIN;
		}
	}

	public View memberHome() {
		printMember();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 0:
			return View.MEMBER_LOGIN;
		case 1:
			return View.PROD_BUY;
		case 2:
			return View.CART_LIST;
		case 3:
			return View.CART_TEMP;
		case 4:
			return View.MEMBER_JOIN;
		case 5:
			sessionStorage.clear();
			return View.HOME;
		default :
			return View.MEMBER;
		}
	}

}
