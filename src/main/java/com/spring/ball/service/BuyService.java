//package com.spring.ball.service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public interface BuyService {
//	
//	// 구매요청
//	public void requestBuy(HttpServletRequest req, HttpServletResponse res);
//	
//	// 구매목록 보기
//	public void viewBuyList(HttpServletRequest req, HttpServletResponse res);
//	
//	// 구매 확정 - 관리자, 고객
//	public void buyConfirm(HttpServletRequest req, HttpServletResponse res);
//	
//	// 환불요청
//	// 이 프로젝트에서는 환불요청 시 요청수량만큼 기존 구매요청된 수량이 줄어드는 것으로 한다.
//	public void refundReq(HttpServletRequest req, HttpServletResponse res);
//	
//	// 환불목록 보기
//	public void viewRefundList(HttpServletRequest req, HttpServletResponse res);
//	
//	// 환불 확정
//	public void confirmRefund(HttpServletRequest req, HttpServletResponse res);
//	
//	// 환불 취소
//	public void cancelRefund(HttpServletRequest req, HttpServletResponse res);
//	
//	// 결산
//	public void closing(HttpServletRequest req, HttpServletResponse res);
//	
//}
