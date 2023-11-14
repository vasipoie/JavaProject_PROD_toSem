package print;


import java.util.List;

import vo.Cart;
import vo.Prod;

public class Print {
	
	public void printVar() {
		System.out.println("------------------------------------");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++) System.out.println();
	}
	
	public void printMember() {
		printVar();
		System.out.println("0. 로그인 ");
		System.out.println("1. 상품 구매 ");
		System.out.println("2. 구매 상품 조회");
		System.out.println("3. 장바구니");
		System.out.println("4. 회원가입");
		System.out.println("5. 로그아웃/홈");
		printVar();
	}
	
	public void printAdmin() {
		printVar();
		System.out.println("1. 관리자 로그인 ");
		System.out.println("2. 상품 정보 조회");
		System.out.println("3. 신규 상품 등록");
		System.out.println("4. 상품 정보 수정");
		System.out.println("5. 상품 정보 삭제");
		System.out.println("6. 홈/로그아웃");
		printVar();
	}
	
	public void printHome() {
		printVar();
		System.out.println("1. 관리자");
		System.out.println("2. 일반회원");
		printVar();
	}
	
	public void printProdList(List<Prod> l) {
		for (Prod prod : l) {
			System.out.println(prod);
		}
	}
	
	public void printCartList(List<Cart> l) {
		System.out.println("-------------------");
		for (Cart prod : l) {
			System.out.println(prod);
		}
		System.out.println("-------------------");
	}
	
	public void printProdListMenu() {
		System.out.println("1. 다시보기");
		System.out.println("2. 홈");
	}
	
	public void printProdUpdateDetail() {
		printVar();
		printLn(2);
		System.out.println("1. 상품 이름 ");
		System.out.println("2. 내용");
		System.out.println("3. 가격");
		System.out.println("4. 수량");
		System.out.println("5. 전체");
		printLn(3);
		printVar();
	}
	
	public void printProdBuy() {
		printVar();
		printLn(2);
		System.out.println("1. 상품 구매 ");
		System.out.println("2. 장바구니 담기");
		System.out.println("3. 홈");
		printLn(3);
		printVar();
	}
	
	public void prinCartHome() {
		printVar();
		printLn(2);
		System.out.println("1. 홈 ");
		printLn(3);
		printVar();
	}
	
	
	
}
