package com.spring.ball.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductVO {

	private int pdNum;			// 제품번호
	private String title;		// 제품명
	private int p_count;		// 수량
	private int p_price;		// 가격
	private Timestamp p_date;	// 등록일
	private String pdImage;		// 제품사진
	private int totPrice;		// 총액
	private int cartNum;		// 장바구니 번호
	private int buyNum;			// 구매번호
	private Date auDate;		// 승인일
	private String clientId;	// 고객명
	private int state;			// 제품상태
	private int refundNum;		// 환불번호
	private Date refundReqDate;	// 환불신청일
	
	
	public int getPdNum() {
		return pdNum;
	}
	public void setPdNum(int pdNum) {
		this.pdNum = pdNum;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getP_count() {
		return p_count;
	}
	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public Timestamp getP_date() {
		return p_date;
	}
	
	public void setP_date(Timestamp p_date) {
		this.p_date = p_date;
	}
	public String getPdImage() {
		return pdImage;
	}
	public void setPdImage(String pdImage) {
		this.pdImage = pdImage;
	}
	public int getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public Date getAuDate() {
		return auDate;
	}
	public void setAuDate(Date auDate) {
		this.auDate = auDate;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRefundNum() {
		return refundNum;
	}
	public void setRefundNum(int refundNum) {
		this.refundNum = refundNum;
	}
	public Date getRefundReqDate() {
		return refundReqDate;
	}
	public void setRefundReqDate(Date refundReqDate) {
		this.refundReqDate = refundReqDate;
	}
	
	
	
	
	
	
}
