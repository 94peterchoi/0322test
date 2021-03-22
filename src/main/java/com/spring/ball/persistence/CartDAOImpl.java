//package com.spring.ball.persistence;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import mvc.cart.vo.CartVO;
//import mvc.product.vo.ProductVO;
//
//public class CartDAOImpl implements CartDAO {
//
//	private static CartDAOImpl instance = new CartDAOImpl();
//	
//	DataSource dataSource;
//	
//	private CartDAOImpl() {
//		Context context;
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/shop_pj");
//		}
//		catch(NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static CartDAOImpl getInstance() {
//		if(instance == null) {
//			instance = new CartDAOImpl();
//		}
//		return instance;
//	}
//	
//	// 장바구니 담기
//	@Override
//	public int insertCart(CartVO vo) {
//		int insertCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "INSERT INTO cart(cartnum, guestid, p_num, cartcount) "
//						+ "VALUES(cart_seq.NEXTVAL, ?, ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getGuestId());
//			pstmt.setInt(2, vo.getPdNum());
//			pstmt.setInt(3, vo.getCartcount());
//			
//			insertCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return insertCnt;
//	}
//	// 장바구니 수 세기
//	@Override
//	public int getCartCnt(String guestId) {
//		int cnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT COUNT(*) as cnt FROM cart WHERE guestid=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, guestId);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				cnt = rs.getInt("cnt");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//				if(rs != null) rs.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return cnt;
//	}
//	
//	// 장바구니 목록 보기
//	@Override
//	public List<ProductVO> cartList(int start, int end, String guestId) {
//		List<ProductVO> list = new ArrayList<ProductVO>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * "
//						+  "FROM(SELECT c.cartnum, "
//						+ 			  "l.p_num, "
//						+ 			  "l.p_title, "
//						+ 			  "l.p_price, "
//						+ 			  "c.cartcount, "
//						+ 			  "l.p_price * c.cartcount totPrice, "
//						+ 			  "ROWNUM rNum "
//						+ 		 "FROM cart c "
//						+ 		 "JOIN product l "
//						+ 		   "ON l.p_num = c.p_num "
//						+ 		"WHERE guestid = ?) "
//						+ "WHERE rNum >= ? AND rNum <= ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, guestId);
//			pstmt.setInt(2, start);
//			pstmt.setInt(3, end);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				list = new ArrayList<ProductVO>();
//				
//				do {
//					ProductVO vo = new ProductVO();
//					
//					System.out.println("cartnum" + rs.getInt("cartnum"));
//					System.out.println(rs.getInt("p_num"));
//					System.out.println(rs.getString("p_title"));
//					System.out.println(rs.getInt("p_price"));
//					System.out.println(rs.getInt("cartcount"));
//					System.out.println(rs.getInt("totPrice"));
//					
//					vo.setCartNum(rs.getInt("cartnum"));
//					vo.setPdNum(rs.getInt("p_num"));;
//					vo.setTitle(rs.getString("p_title"));
//					vo.setP_price(rs.getInt("p_price"));
//					vo.setP_count(rs.getInt("cartcount"));
//					vo.setTotPrice(rs.getInt("totPrice"));
//
//					list.add(vo);
//				}
//				while(rs.next());
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return list;
//	}
//
//	// 장바구니 수량 변경
//	@Override
//	public int updateQuantity(int cartNum, int p_count) {
//
//		int updateCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE cart SET cartcount=? WHERE cartnum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, p_count);
//			pstmt.setInt(2, cartNum);
//			updateCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return updateCnt;
//	}
//
//	// 장바구니 빼기
//	@Override
//	public int deleteCart(int cartNum) {
//		
//		int deleteCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "DELETE FROM cart WHERE cartnum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, cartNum);
//			deleteCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return deleteCnt;
//	}
//
//}
