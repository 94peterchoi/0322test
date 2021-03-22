package com.spring.ball.vo;

import java.sql.Date;

public class BuyVO {
	
	private int buyNum;			// 구매번호 BUYNUM	NUMBER
	private String guestId;		// 구매요청고객 BUYGUESTID	VARCHAR2(10 CHAR)
	private int pdNum;			// 제품번호(FK) PRODUCTNUM	NUMBER
	private Date BuyAuDate;		// 구매승인일자 BUYAUDATE	DATE
	private int buycount;	// 구매수량 BUYQUANTITY	NUMBER
	
	public int getBuyNum() {
		return buyNum;
	}
	
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
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
	
	public Date getBuyAuDate() {
		return BuyAuDate;
	}
	
	public void setBuyAuDate(Date buyAuDate) {
		BuyAuDate = buyAuDate;
	}
	
	public int getBuycount() {
		return buycount;
	}
	
	public void setBuycount(int buycount) {
		this.buycount = buycount;
	}
	
	
	
}
