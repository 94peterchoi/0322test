//package com.spring.ball.persistence;
//
//import java.util.List;
//
//import mvc.cart.vo.CartVO;
//import mvc.product.vo.ProductVO;
//
//public interface CartDAO {
//	
//	// 장바구니 담기
//	public int insertCart(CartVO vo);
//	
//	// 장바구니 수 세기
//	public int getCartCnt(String guestId);
//	
//	// 장바구니 목록 보기
//	public List<ProductVO> cartList(int start, int end, String guestId);
//	
//	// 장바구니 수량 변경
//	public int updateQuantity(int cartNum, int p_count);
//	
//	// 장바구니 빼기
//	public int deleteCart(int cartNum);
//	
//}
