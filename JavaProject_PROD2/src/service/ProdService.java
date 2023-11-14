package service;

import java.util.List;

import dao.ProdDao;
import vo.Prod;

public class ProdService {
	private static ProdService singleTon = null;

	private ProdService() {
	};

	public static ProdService getInstance() {
		if (singleTon == null) {
			singleTon = new ProdService();
		}
		return singleTon;
	}

	ProdDao prodDao = ProdDao.getInstance();

	public void prodInsert(List<Object> param) {
		prodDao.prodInsert(param);
	}

	public List<Prod> prodList() {
		return prodDao.prodList();
	}
	
	public List<Prod> prodListNotDel() {
		return prodDao.prodListNotDel();
	}

	public void update(List<Object> param, int select) {
		prodDao.update(param, select);
	}

	public void prodDel(List<Object> param) {
		prodDao.delete(param);
	}
}
