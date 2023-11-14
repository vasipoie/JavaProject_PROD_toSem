package vo;

import java.util.Date;

import lombok.Data;

@Data
public class Cart {
	private int no;
	private String id;
	private int prod_no;
	private String yn;
	private Date purchase_date;
}
