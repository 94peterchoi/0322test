//package com.spring.ball.persistence;
//
//import java.util.List;
//
//import mvc.buy.vo.BuyVO;
//import mvc.buy.vo.ClosingVO;
//import mvc.product.vo.ProductVO;
//
//public interface BuyDAO {
//	
//	// 구매요청
//	public int insertBuy(BuyVO vo);
//	
//	// 구매목록 수 세기
//	public int getBuyCnt(String clientId);
//	
//	// 구매목록 보기
//	public List<ProductVO> selectBuyList(int start, int end, String clientId);
//	
//	// 환불요청 - 기존 구매요청된 수량 감소 처리
//	public int refundUpdate(int buyNum, int refQuantity);
//	
//	// 환불요청 - 환불 목록 등록
//	public int insertRefund(int pdNum, String clientId, int refQuantity);
//	
//	// 환불목록 수 세기
//	public int getRefundCnt(String clientId);
//	
//	// 환불목록 보기
//	public List<ProductVO> selectRefundList(int start, int end, String clientId);
//	
//	// 환불 확정
//	public int confirmRefund(int refundNum);
//	
//	// 환불취소 - 현재 구매목록에 남아있는 구매요청 수량 불러오기
//	public int getBuyQuantity(int buyNum);
//	
//	// 환불취소 - 환불목록에서 삭제
//	public int deleteRefund(int refundNum);
//	
//	// 환불취소 - 구매요청수량에 환불취소 수량 더하기
//	public int refundToBuy(int buyNum, int changeBuyQuantity);
//	
//	// 재고 수량 조회
//	public int getStockQuantity(int pdNum);
//	
//	// 재고 수량 변경
//	public int confirmReflect(int pdNum, int quantity);
//	
//	// 상태 변경 - 구매 확정
//	public int updateBuyState(int buyNum);
//	
//	// 결산 - 연간 월별 구매확정 기록
//	public List<ClosingVO> getClosingSalesData(String year);
//
//	// 결산 - 연간 월별 결제대기 기록
//	public List<ClosingVO> getClosingStandbyData(String year);
//	
//	// 결산 - 연간 매출
//	public int annualSales(String year);
//	
//	// 결산 - 이달 매출
//	public double monthlySales(String year, String month);
//	
//	// 결산 - 이달 결제대기
//	public int monthlyStandby(String year, String month);
//	
//}
