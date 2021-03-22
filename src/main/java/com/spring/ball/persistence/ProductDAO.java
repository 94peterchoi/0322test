//package com.spring.ball.persistence;
//
//import java.util.List;
//
//import mvc.product.vo.ProductVO;
//
//public interface ProductDAO {
//
//	// 제품 등록
//	public int insertProduct(ProductVO vo);
//	
//	// 제품번호 가져오기
//	public int getPdNum();
//	
//	// 제품 등록 수 구하기
//	public int getProductCnt();
//	
//	// 제품 목록 보기
//	public List<ProductVO> productList(int start, int end);
//	
//	// 수량 변경
//	public int quantityChange(int pdNum, int quantity);
//	
//	// 제품 정보 보기
//	public ProductVO productView(int pdNum);
//	
//	// 제품 정보 수정
//	public int updateProduct(ProductVO vo);
//		
//	// 제품 정보 삭제
//	public int deleteProduct(int pdNum);
//	
//	// 환불 목록 수 세기
//	public int getRefundCnt();
//	
//	// 환불정보 전체 보기 - 관리자
//	public List<ProductVO> refundList(int start, int end);
//	
//	// 구매목록 수 세기 - 관리자
//	public int getAllBuyCnt(String clientId);
//	
//	// 구매 목록 전체 조회 - 관리자
//	public List<ProductVO> AllBuyList(String clientId, int start, int end);
//}
