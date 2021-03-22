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
//import javax.sql.DataSource;
//
//import mvc.product.vo.ProductVO;
//
//public class ProductDAOImpl implements ProductDAO {
//
//	private static ProductDAOImpl instance = new ProductDAOImpl();
//	
//	public static ProductDAOImpl getInstance() {
//		if(instance == null) {
//			instance = new ProductDAOImpl();
//		}
//		return instance;
//	}
//	
//	DataSource dataSource;
//	
//	private ProductDAOImpl() {
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/shop_pj");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	// 제품 등록
//	@Override
//	public int insertProduct(ProductVO vo) {
//		int insertCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO product (p_num, p_title, p_count, p_price, p_image, p_date) "
//						+ "VALUES(product_seq.NEXTVAL, ?, ?, ?, ?, sysdate)";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setInt(2, vo.getP_count());
//			pstmt.setInt(3, vo.getP_price());
//			pstmt.setString(4, vo.getPdImage());
//			
//			insertCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//		        if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return insertCnt;
//	}
//	
//	// 제품번호 가져오기
//	@Override
//	public int getPdNum() {
//		
//		int maxPdNum = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT Max(p_num) maxPdNum FROM product";
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				maxPdNum = rs.getInt("maxPdNum");
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
//		
//		return maxPdNum;
//	}
//	
//	// 제품 목록 보기
//	@Override
//	public List<ProductVO> productList(int start, int end) {
//		
//		List<ProductVO> list = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * "
//					+ "FROM (SELECT p_num, p_title, p_count, p_price, p_image, p_date, rowNum rNum "
//			        +  	   "FROM ("
//			        +            "SELECT * FROM product "
//			        +             "ORDER BY p_num" // 1. 최신글부터 SELECT(num 30번~1번 순서)
//			        +           ")"  // 2. 최신글부터 SELECT한 결과에 rowNum을 추가한다.
//			        +    ")"
//					+ "WHERE rNum >= ? AND rNum <= ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				list = new ArrayList<ProductVO>();
//				
//				do {
//					ProductVO vo = new ProductVO();
//					
//					vo.setPdNum(rs.getInt("p_num"));
//					vo.setTitle(rs.getString("p_title"));
//					vo.setP_count(rs.getInt("p_count"));
//					vo.setP_price(rs.getInt("p_price"));
//					vo.setPdImage(rs.getString("p_image"));
//					vo.setP_date(rs.getTimestamp("p_date"));
//					
//					list.add(vo);
//				}
//				while(rs.next());
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return list;
//	}
//
//	// 제품 등록 수 구하기
//	@Override
//	public int getProductCnt() {
//		
//		int selectCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT COUNT(*) as cnt FROM product";	// 조회된 전체 행 갯수를 센다.
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				selectCnt = rs.getInt("cnt");	// 괄호 안은 SELECT COUNT(*) 결과의 별칭
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return selectCnt;
//	}
//	
//	// 수량 변경
//	@Override
//	public int quantityChange(int pdNum, int quantity) {
//		int updateCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE product SET p_count=? WHERE p_num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, quantity);
//			pstmt.setInt(2, pdNum);
//			updateCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return updateCnt;
//	}
//	
//	// 제품 정보 보기
//	@Override
//	public ProductVO productView(int pdNum) {
//		
//		ProductVO vo = new ProductVO();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM product WHERE p_num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pdNum);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				vo.setPdNum(rs.getInt("p_num"));
//				vo.setTitle(rs.getString("p_title"));
//				vo.setP_price(rs.getInt("p_price"));
//				vo.setP_date(rs.getTimestamp("p_date"));
//				vo.setP_count(rs.getInt("p_count"));
//				vo.setPdImage(rs.getString("p_image"));
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
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return vo;
//	}
//	
//	// 제품정보 수정
//	@Override
//	public int updateProduct(ProductVO vo) {
//		
//		int updateCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE product "
//						+	"SET p_title=?, "
//						+		"p_price=?, "
//						+		"p_count=?, "
//						+		"p_image=? "
//						+ "WHERE p_num=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setInt(2, vo.getP_price());
//			pstmt.setInt(3, vo.getP_count());
//			pstmt.setString(4, vo.getPdImage());
//			pstmt.setInt(5, vo.getPdNum());
//			updateCnt = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return updateCnt;
//	}
//	
//	// 제품 삭제
//	@Override
//	public int deleteProduct(int pdNum) {
//		
//		int deleteCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "DELETE FROM product WHERE p_num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pdNum);
//			deleteCnt = pstmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return deleteCnt;
//	}
//
//	// 환불 목록 수 세기
//	@Override
//	public int getRefundCnt() {
//		
//		int selectCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT COUNT(*) as cnt FROM refund WHERE NOT refundguestid='host'";	// 조회된 전체 행 갯수를 센다.
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				selectCnt = rs.getInt("cnt");	// 괄호 안은 SELECT COUNT(*) 결과의 별칭
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return selectCnt;
//	}
//
//	// 환불정보 전체 보기 - 관리자
//	@Override
//	public List<ProductVO> refundList(int start, int end) {
//		List<ProductVO> list = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM "
//					+ 		"(SELECT refundNum, "
//					+				"l.p_title, "
//					+				"l.p_price, "
//					+				"refundcount, "
//					+ 			    "r.refundguestid, "
//					+			    "r.refund_date,"
//					+				"refundstate, "
//					+				"ROWNUM rNum "
//					+		   "FROM refund r "
//					+		   "JOIN product l "
//					+			 "ON l.p_num = r.p_num "
//					+		  "WHERE NOT r.refundguestid='host' "
//					+		  "ORDER BY refundnum) "
//					+		  "WHERE rNum >= ? AND rNum <= ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				list = new ArrayList<ProductVO>();
//				
//				do {
//					ProductVO vo = new ProductVO();
//					
//					vo.setRefundNum(rs.getInt("refundNum"));
//					vo.setTitle(rs.getString("p_title"));
//					vo.setP_price(rs.getInt("p_price"));
//					vo.setP_count(rs.getInt("refundcount"));
//					vo.setClientId(rs.getString("refundguestid"));
//					vo.setRefundReqDate(rs.getDate("refund_date"));
//					vo.setState(rs.getInt("refundState"));
//					
//				
//					
//					list.add(vo);
//				}
//				while(rs.next());
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
//
//	// 구매목록 수 세기 - 관리자
//	@Override
//	public int getAllBuyCnt(String clientId) {
//		int cnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT COUNT(*) as cnt FROM buy WHERE NOT buyguestid=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, clientId);
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
//		
//		return cnt;
//	}
//
//	// 구매 목록 전체 조회 - 관리자
//	@Override
//	public List<ProductVO> AllBuyList(String clientId, int start, int end) {
//		
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
//					+	  "FROM(SELECT r.buyNum, "
//					+ 				  "l.p_num, "
//					+      		      "l.p_title, "
//					+			   	  "l.p_price, "
//					+ 				  "r.buycount, "
//					+ 				  "r.buyguestid, "
//					+ 				  "r.buystate, "
//                    +				  "r.buy_date, "
//					+ 				  "ROWNUM rNum "
//					+	  	 	 "FROM buy r "
//					+	  	 	 "JOIN product l "
//					+    		   "ON r.p_num = l.p_num "
//					+		  	"WHERE NOT buyguestid=?) "
//					+	  "WHERE rNum >= ? AND rNum <= ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, clientId);
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
//					vo.setBuyNum(rs.getInt("buyNum"));
//					vo.setPdNum(rs.getInt("p_num"));
//					vo.setTitle(rs.getString("p_title"));
//					vo.setP_price(rs.getInt("p_price"));
//					vo.setP_count(rs.getInt("buycount"));
//					vo.setClientId(rs.getString("buyguestid"));
//					vo.setState(rs.getInt("buyState")); 
//					vo.setAuDate(rs.getDate("buy_date"));
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
//}
