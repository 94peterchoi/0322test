package com.spring.ball.vo;

public class CartVO {
	
	private int cartNum;		// 카트번호
	private String guestId;		// 고객아이디
	private int pdNum;		// 제품번호
	private int cartcount;		// 카트수량

	
	public int getCartNum() {
		return cartNum;
	}
	
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	
	public String getGuestId() {
		return guestId;
	}
	
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	
	public int getPdNum() {
		return pdNum;
	}
	
	public void setPdNum(int pdNum) {
		this.pdNum = pdNum;
	}
	
	public int getCartcount() {
		return cartcount;
	}
	
	public void setCartcount(int cartcount) {
		this.cartcount = cartcount;
	}
	
	
}
