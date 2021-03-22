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
//import mvc.buy.vo.BuyVO;
//import mvc.buy.vo.ClosingVO;
//import mvc.product.vo.ProductVO;
//
//public class BuyDAOImpl implements BuyDAO {
//	
//private static BuyDAOImpl instance = new BuyDAOImpl();
//	
//	DataSource dataSource;
//	
//	private BuyDAOImpl() {
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
//	public static BuyDAOImpl getInstance() {
//		if(instance == null) {
//			instance = new BuyDAOImpl();
//		}
//		return instance;
//	}
//	
//	// 구매요청
//	@Override
//	public int insertBuy(BuyVO vo) {
//		
//		int insertCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "INSERT INTO buy(buyNum, buyguestid, p_num, buycount, buy_date) "
//						+ "VALUES(buy_seq.NEXTVAL, ?, ?, ?, sysdate)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getGuestId());
//			pstmt.setInt(2, vo.getPdNum());
//			pstmt.setInt(3, vo.getBuycount());		
//			
//			insertCnt = pstmt.executeUpdate();
//			
//		} 
//		catch (SQLException e) {
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
//		System.out.println("insertCnt : " + insertCnt);
//		return insertCnt;
//	}
//
//	// 구매목록 수 세기
//	@Override
//	public int getBuyCnt(String clientId) {
//		int cnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT COUNT(*) as cnt FROM buy WHERE buyguestid=?";
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
//	// 구매목록 보기
//	@Override
//	public List<ProductVO> selectBuyList(int start, int end, String clientId) {
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
//					+ 				  "r.buy_date, "
//					+ 				  "r.buystate, "
//					+ 				  "ROWNUM rNum "
//					+	  	 	 "FROM buy r "
//					+	  	 	 "JOIN product l "
//					+    		   "ON r.p_num = l.p_num "
//					+		  	"WHERE buyguestid=?) "
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
//					vo.setAuDate(rs.getDate("buy_date"));
//					vo.setState(rs.getInt("buystate"));
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
//	// 환불요청 - 기존 구매요청된 수량 감소 처리
//	@Override
//	public int refundUpdate(int buyNum, int subQuantity) {
//
//		int updateCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE buy SET buycount=? WHERE buyNum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, subQuantity);
//			pstmt.setInt(2, buyNum);
//			
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
//		
//		return updateCnt;
//	}
//
//	// 환불요청 - 환불 목록 등록
//	@Override
//	public int insertRefund(int pdNum, String clientId, int refQuantity) {
//		int insertCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "INSERT INTO refund(refundnum, p_num, refundguestid, refund_date, refundcount) "
//						+ "VALUES(refund_seq.NEXTVAL, ?, ?, sysdate, ?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pdNum);
//			pstmt.setString(2, clientId);
//			pstmt.setInt(3, refQuantity);
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
//
//	// 환불목록 수 세기
//	@Override
//	public int getRefundCnt(String clientId) {
//		int cnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT COUNT(*) as cnt FROM refund WHERE refundguestid=?";
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
//	// 환불 목록 조회
//	@Override
//	public List<ProductVO> selectRefundList(int start, int end, String clientId) {
//		
//		List<ProductVO> list = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * "
//						+ "FROM( SELECT r.refundnum, "
//						+ 			   "l.p_num, "
//						+ 			   "l.p_title, "
//						+ 			   "l.p_price, "
//						+ 			   "r.refundcount, "
//						+ 			   "r.refundguestid, "
//						+ 			   "r.refund_date, "
//						+			   "r.refundstate, "
//						+			   "y.buyNum, "
//						+ 			   "ROWNUM rNum " 
//						+ 		  "FROM refund r "
//						+ 		  "JOIN product l "
//						+ 	   	   " ON r.p_num = l.p_num "
//						+		  "JOIN buy y "
//			            + 		    "ON y.p_num = r.p_num "
//						+		   "AND y.buyguestid = r.refundguestid "
//						+ 		 "WHERE refundguestid=?) "
//						+ "WHERE rNum >= ? AND rNum <= ?";
// 		
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
//					vo.setRefundNum(rs.getInt("refundnum"));
//					vo.setPdNum(rs.getInt("p_num"));
//					vo.setTitle(rs.getString("p_title"));
//					vo.setP_price(rs.getInt("p_price"));
//					vo.setP_count(rs.getInt("refundcount"));
//					vo.setClientId(rs.getString("refundguestid"));
//					vo.setRefundReqDate(rs.getDate("refund_date"));
//					vo.setState(rs.getInt("refundstate"));
//					vo.setBuyNum(rs.getInt("buynum"));
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
//		return list;
//	}
//	
//	// 환불 확정
//	@Override
//	public int confirmRefund(int refundNum) {
//		
//		int updateCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE refund SET refundstate=1, refund_date=sysdate WHERE refundnum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, refundNum);
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
//	// 환불취소 - 현재 구매목록에 남아있는 구매요청 수량 불러오기
//	@Override
//	public int getBuyQuantity(int buyNum) {
//		
//		int buyQuantity = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT buycount FROM buy WHERE buynum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, buyNum);
//			
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				buyQuantity = rs.getInt("buycount");
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
//		return buyQuantity;
//	}
//	
//	// 환불취소 - 환불목록에서 삭제
//	@Override
//	public int deleteRefund(int refundNum) {
//		int deleteCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "DELETE FROM refund WHERE refundnum=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, refundNum);
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
//	// 환불취소 - 구매요청수량에 환불취소 수량 더하기
//	@Override
//	public int refundToBuy(int buyNum, int changeBuyQuantity) {
//		
//		int updateCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE buy SET buycount=? WHERE buynum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, changeBuyQuantity);
//			pstmt.setInt(2, buyNum);
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
//	// 재고 수량 조회
//	@Override
//	public int getStockQuantity(int pdNum) {
//		
//		int pdQuantity = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT p_count pdQuantity FROM product WHERE p_num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pdNum);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				pdQuantity = rs.getInt("pdQuantity");
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
//		return pdQuantity;
//	}
//	
//	// 재고 수량 변경
//	@Override
//	public int confirmReflect(int pdNum, int estQuantity) {
//		int reflectCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE product SET p_count=? WHERE p_num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, estQuantity);
//			pstmt.setInt(2, pdNum);
//			
//			reflectCnt = pstmt.executeUpdate();
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
//		return reflectCnt;
//	}
//	
//	// 상태 변경 - 구매 확정
//	@Override
//	public int updateBuyState(int buyNum) {
//		
//		int updateCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "UPDATE buy SET buystate=1, buy_date=sysdate WHERE buynum=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, buyNum);
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
//		
//		return updateCnt;
//	}
//
//	// 결산 - 연간 월별 구매확정 기록
//	@Override
//	public List<ClosingVO> getClosingSalesData(String year) {
//		
//		List<ClosingVO> salesList = new ArrayList<ClosingVO>();
//		String yyyy = "yyyy";
//		String mm = "mm";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * " 
//					+	 "FROM ( "
//					+	    "SELECT year, "
//					+	           "month, "
//					+	           "SUM(total) annMonSales "
//					+	      "FROM(SELECT (l.price * b.buyquantity) total, "
//					+	                  "TO_CHAR(b.buyreqDate, ?) year, "
//					+	                  "TO_CHAR(b.buyreqDate, ?) month "
//					+	             "FROM buyreq b "
//					+	             "JOIN labtop l "
//					+	               "ON b.productNum = l.productNum "
//					+	            "WHERE b.buyState=1) "
//					+	      "GROUP BY year, month) "
//					+	 "WHERE year=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, yyyy);
//			pstmt.setString(2, mm);
//			pstmt.setString(3, year);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				
//				do {
//					ClosingVO vo = new ClosingVO();
//					
//					vo.setYear(rs.getString("year"));
//					vo.setMonth(rs.getString("month"));
//					vo.setAnnMonSales(rs.getInt("annMonSales"));
//					
//					salesList.add(vo);
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
//		return salesList;
//	}
//
//	// 결산 - 연간 월별 결제대기 기록
//	@Override
//	public List<ClosingVO> getClosingStandbyData(String year) {
//		
//		List<ClosingVO> standbyList = new ArrayList<ClosingVO>();
//		String yyyy = "yyyy";
//		String mm = "mm";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT * " 
//					+	 "FROM ( "
//					+	    "SELECT year, "
//					+	           "month, "
//					+	           "SUM(total) annMonStandbySales "
//					+	      "FROM(SELECT (l.price * b.buyquantity) total, "
//					+	                  "TO_CHAR(b.buyreqDate, ?) year, "
//					+	                  "TO_CHAR(b.buyreqDate, ?) month "
//					+	             "FROM buyreq b "
//					+	             "JOIN labtop l "
//					+	               "ON b.productNum = l.productNum "
//					+	            "WHERE b.buyState=0) "
//					+	      "GROUP BY year, month) "
//					+	 "WHERE year=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, yyyy);
//			pstmt.setString(2, mm);
//			pstmt.setString(3, year);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				
//				do {
//					ClosingVO vo = new ClosingVO();
//					
//					vo.setYear(rs.getString("year"));
//					vo.setMonth(rs.getString("month"));
//					vo.setAnnMonStandbySales(rs.getInt("annMonStandbySales"));
//					
//					standbyList.add(vo);
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
//		return standbyList;
//	}
//
//	// 결산 - 연간 매출
//	@Override
//	public int annualSales(String year) {
//		
//		int annualSales = 0;
//		String yyyy = "yyyy";
//		String mm = "mm";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT SUM(total) annualSales "
//						+  "FROM (SELECT (l.price * b.buyquantity) total, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) year, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) month "
//						+ 			   "FROM buyreq b "
//						+		   	   "JOIN labtop l "
//						+			  	 "ON b.productNum = l.productNum "
//						+ 			  "WHERE b.buyState=1) "
//						+ "WHERE year=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, yyyy);
//			pstmt.setString(2, mm);
//			pstmt.setString(3, year);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				annualSales = rs.getInt("annualSales");
//			}
//			
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
//		return annualSales;
//	}
//
//	// 결산 - 이달 매출
//	@Override
//	public double monthlySales(String year, String month) {
//		
//		double monthlySales = 0;
//		String yyyy = "yyyy";
//		String mm = "mm";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT SUM(total) monthlySales "
//						+  "FROM (SELECT (l.price * b.buyquantity) total, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) year, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) month "
//						+ 			   "FROM buyreq b "
//						+		   	   "JOIN labtop l "
//						+			  	 "ON b.productNum = l.productNum "
//						+ 			  "WHERE b.buyState=1) "
//						+ "WHERE year=? AND month=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, yyyy);
//			pstmt.setString(2, mm);
//			pstmt.setString(3, year);
//			pstmt.setString(4, month);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				monthlySales = rs.getInt("monthlySales");
//			}
//			
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
//		return monthlySales;
//	}
//	
//	// 결산 - 이달 결제대기
//	@Override
//	public int monthlyStandby(String year, String month) {
//		
//		int monthlyStandby = 0;
//		String yyyy = "yyyy";
//		String mm = "mm";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "SELECT SUM(total) monthlyStandby "
//						+  "FROM (SELECT (l.price * b.buyquantity) total, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) year, "
//						+				 	"TO_CHAR(b.buyreqDate, ?) month "
//						+ 			   "FROM buyreq b "
//						+		   	   "JOIN labtop l "
//						+			  	 "ON b.productNum = l.productNum "
//						+ 			  "WHERE b.buyState=0) "
//						+ "WHERE year=? AND month=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, yyyy);
//			pstmt.setString(2, mm);
//			pstmt.setString(3, year);
//			pstmt.setString(4, month);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				monthlyStandby = rs.getInt("monthlyStandby");
//			}
//			
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
//		return monthlyStandby;
//	}
//	
//}
