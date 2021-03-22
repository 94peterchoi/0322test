//package com.spring.ball.persistence;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import mvc.board.vo.BoardVO;
//
//public class BoardDAOImpl implements BoardDAO {
//
//	// 싱글톤 방식으로 객체 생성
//	private static BoardDAOImpl instance = new BoardDAOImpl();
//	
//	public static BoardDAOImpl getInstance() {
//		if(instance == null) {
//			instance = new BoardDAOImpl();
//		}
//		return instance;
//	}
//	
//	// 커넥션 객체 보관
//	DataSource dataSource;
//	
//	// 생성자
//	private BoardDAOImpl() {
//		// 커넥션 풀 생성
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/shop_pj");
//		}
//		catch(NamingException e) {
//			e.printStackTrace();
//		}
//	};
//
//	// 게시글 갯수 구하기
//	@Override
//	public int getBoardCnt() {
//		
//		int selectCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT COUNT(*) as cnt FROM board";	// 조회된 전체 행 갯수를 센다.
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
//	// 게시판 글 목록 조회
//	@Override
//	public ArrayList<BoardVO> getBoardList(int start, int end) {
//		
//		//1. ArrayList 선언
//		
//		ArrayList<BoardVO> dtos = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		
//			conn = dataSource.getConnection();
//			String sql = "SELECT * " 
//					 + "FROM (SELECT num, writer, pwd, subject, content, readCnt, "
//					 	+  		    "ref, ref_step, ref_level, reg_date, rowNum rNum "
//				        +  	   "FROM ("
//				        +            "SELECT * FROM board "
//				        +             "ORDER BY ref DESC, ref_step ASC" // 1. 최신글부터 SELECT(num 30번~1번 순서)
//				        +           ")"  // 2. 최신글부터 SELECT한 결과에 rowNum을 추가한다.
//				        +    ")"  
//				+ " WHERE  rNum >= ? AND rNum <= ?";  // 3. 넘겨받은 start값과 end값으로 rowNum을 조회 : 30건 기준(6페이지) ..(1페이지 => (num 30~26번 => rNum 1~5)
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
//			rs = pstmt.executeQuery();
//			
//			// 결과가 존재하면 
//			if(rs.next()) { 
//				// 2. 큰 바구니 생성
//				dtos = new ArrayList<BoardVO>();
//				
//				do {	
//					// 3. 작은바구니(BoardVO) 생성
//					BoardVO vo = new BoardVO();
//					
//					// 4. 게시글 1건을 읽어서 rs 결과를 작은바구니에 담는다.
//					vo.setNum(rs.getInt("num"));
//					vo.setWriter(rs.getString("writer"));
//					vo.setPwd(rs.getString("pwd"));
//					vo.setSubject(rs.getString("subject"));
//					vo.setContent(rs.getString("content"));
//					vo.setReadCnt(rs.getInt("readCnt"));
//					vo.setRef(rs.getInt("ref"));
//					vo.setRef_step(rs.getInt("ref_step"));
//					vo.setRef_level(rs.getInt("ref_level"));
//					vo.setReg_date(rs.getTimestamp("reg_date"));
//					
//					// 5. 큰 바구니(ArrayList)에 작은 바구니(BoardVO, 게시글 1 건씩)을 담는다.
//					dtos.add(vo);
//				}
//				while(rs.next());	// 또 다음 rs가 존재하면 do 문을 계속 반복한다.
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		// 6. 큰 바구니(ArrayList)를 반환한다.
//		return dtos;
//	}
//
//	// 조회수 증가
//	@Override
//	public void addReadCnt(int num) {
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "UPDATE board SET readCnt = readCnt + 1 WHERE num=?";	
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			
//			pstmt.executeUpdate();
//		}
//		catch(SQLException e) {
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
//	}
//
//	// 게시판 글 상세 페이지, 수정 상세 페이지
//	@Override
//	public BoardVO getBoardDetail(int num) {
//		
//		BoardVO vo = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM board WHERE num=?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//			
//			// 존재한다면
//			if(rs.next()) {
//				// 1. 작은 바구니 생성
//				vo = new BoardVO();
//				
//				// 2. 게시글 1건을 읽어서 작은 바구니에 컬럼별로 담는다.
//				vo.setNum(rs.getInt("num"));
//				vo.setWriter(rs.getString("writer"));
//				vo.setPwd(rs.getString("pwd"));
//				vo.setSubject(rs.getString("subject"));
//				vo.setContent(rs.getString("content"));
//				vo.setReadCnt(rs.getInt("readCnt"));
//				vo.setRef(rs.getInt("ref"));
//				vo.setRef_step(rs.getInt("ref_step"));
//				vo.setRef_level(rs.getInt("ref_level"));
//				vo.setReg_date(rs.getTimestamp("reg_date"));
//				
//				return vo;
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return vo;
//	}
//
//	// 게시판 글 비밀번호 인증 - 수정 상세 페이지
//	@Override
//	public int numPwdCheck(int num, String strPwd) {
//		int selectCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//			
//		try {
//			conn= dataSource.getConnection();
//			
//			String sql = "SELECT * FROM board WHERE num=? AND pwd=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			pstmt.setString(2, strPwd);
//			
//			rs = pstmt.executeQuery();
//			
//			// 존재한다면
//			if(rs.next()) {
//				selectCnt = 1;
//			}
//			else {
//				selectCnt = 0;
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
//	// 게시판 글 수정 처리 페이지
//	@Override
//	public int updateBoard(BoardVO vo) {
//		
//		int updateCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn= dataSource.getConnection();
//			
//			String sql = "UPDATE board SET writer=?, subject=?, content=? WHERE num=?";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, vo.getWriter());
//			pstmt.setString(2, vo.getSubject());
//			pstmt.setString(3, vo.getContent());
//			pstmt.setInt(4, vo.getNum());
//			
//			updateCnt = pstmt.executeUpdate();
//			
//		}
//		catch(SQLException e) {
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
//	// 게시판 글 쓰기 처리 페이지
//	@Override
//	public int insertBoard(BoardVO vo) {
//		
//		int insertCnt = 1;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "";
//
//		try {
//			conn = dataSource.getConnection();
//			
//			int num = vo.getNum();
//			int ref = vo.getRef();
//			int ref_step = vo.getRef_step();
//			int ref_level = vo.getRef_level();
//			
//			// 답변글이 아닌 경우(새 글인 경우)
//			if(num == 0) {
//				// 최신글부터 가져온다.
//				sql = "SELECT MAX(num) as maxNum FROM board";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				
//				// 첫글이 아닌경우 : num과 ref는 동일
//				if(rs.next()) {
//					ref = rs.getInt("maxNum") +1;
//				}
//				else {
//					// 첫글인 경우
//					ref = 1;
//				}
//				ref_step = 0;
//				ref_level = 0;
//			}
//			// 답변글인 경우
//			else {
//				// 삽입할 글보다 아래쪽 글들이 한 줄씩 밀려난다.
//				sql = "UPDATE board SET ref_step = ref_step+1 WHERE ref = ? AND ref_step > ?";
//
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, ref);
//				pstmt.setInt(2, ref_step);
//				
//				pstmt.executeUpdate();
//				
//				// 현재 내 답변글 => 카페에 올려진 게시판 사진과 비교할 것
//				ref_step++;		// 원래 글로부터 밑으로 밀려난 정도(행)
//				ref_level++;	// 들여쓰기 단계
//			}
//			
//			pstmt.close();
//			
//			sql = "INSERT INTO board (num, writer, pwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date) "
//					   + "VALUES(board_seq.NEXTVAL, ?, ?, ?, ?, 0, ?, ?, ?, ?)";
//						// 글번호(num)는 자동 생성에 맞기고, 조회수(readCnt) 0으로 유지.
//						//또, 답글을 쓰면 ref 정보도 바뀌게 되므로 여기서는 자동생성이 아니라, ?를 주어 브라우저 요청으로 불러온 변수를 입력하도록 설정한다.
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getWriter());
//			pstmt.setString(2, vo.getPwd());
//			pstmt.setString(3, vo.getSubject());
//			pstmt.setString(4, vo.getContent());
//			pstmt.setInt(5, ref);		// vo getter가 아니라, 여기서 설정한 값을 넣어야 한다.
//			pstmt.setInt(6, ref_step);	// vo getter가 아니라, 여기서 설정한 값을 넣어야 한다.
//			pstmt.setInt(7, ref_level);	// vo getter가 아니라, 여기서 설정한 값을 넣어야 한다.
//			pstmt.setTimestamp(8, vo.getReg_date());
//			
//			insertCnt = pstmt.executeUpdate();
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally{
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
//		return insertCnt;
//	}
//
//	// 검색한 게시글 수 구하기
//	@Override
//	public int getSearchBoardCnt(String inputKey) {
//		int searchCnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT COUNT(*) as cnt FROM qna WHERE qnaTitle LIKE ?";	// 키워드로 조회된 전체 행 갯수를 센다.
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, inputKey);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				searchCnt = rs.getInt("cnt");	// 괄호 안은 SELECT COUNT(*) 결과의 별칭
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
//		return searchCnt;
//	}
//	
//	// 검색한 게시글 목록 조회
//	@Override
//	public ArrayList<BoardVO> searchBoardList(int start, int end, String inputKey) {
//		
//		ArrayList<BoardVO> dtos = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM (SELECT QnaNum, guestId, qnaTitle, qnaContent, pwd, readCnt, ref, ref_step, ref_level, reg_date, ROWNUM rNum FROM qna WHERE qnaTitle LIKE ?) WHERE rNum >= ? AND rNum <= ? ORDER BY qnaTitle";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, inputKey);
//			pstmt.setInt(2, start);
//			pstmt.setInt(3, end);
//			rs = pstmt.executeQuery();
//			
//			// 결과가 존재하면 
//			if(rs.next()) { 
//				// 2. 큰 바구니 생성
//				dtos = new ArrayList<BoardVO>();
//				
//				do {	
//					// 3. 작은바구니(BoardVO) 생성
//					BoardVO vo = new BoardVO();
//					
//					// 4. 게시글 1건을 읽어서 rs 결과를 작은바구니에 담는다.
//					vo.setNum(rs.getInt("qnaNum"));
//					vo.setWriter(rs.getString("guestId"));
//					vo.setPwd(rs.getString("pwd"));
//					vo.setSubject(rs.getString("qnaTitle"));
//					vo.setContent(rs.getString("qnaContent"));
//					vo.setReadCnt(rs.getInt("readCnt"));
//					vo.setRef(rs.getInt("ref"));
//					vo.setRef_step(rs.getInt("ref_step"));
//					vo.setRef_level(rs.getInt("ref_level"));
//					vo.setReg_date(rs.getTimestamp("reg_date"));
//					
//					// 5. 큰 바구니(ArrayList)에 작은 바구니(BoardVO, 게시글 1 건씩)을 담는다.
//					dtos.add(vo);
//				}
//				while(rs.next());	// 또 다음 rs가 존재하면 do 문을 계속 반복한다.
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}
//			catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		// 6. 큰 바구니(ArrayList)를 반환한다.
//		return dtos;
//	}
//	
//	// 게시판 글 인증 - 삭제 처리 페이지
//	@Override
//	public int deleteBoardAction(int num) {
//		int deleteCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "DELETE FROM board WHERE num=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			
//			deleteCnt = pstmt.executeUpdate();
//			
//			return deleteCnt;
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
//		return deleteCnt;
//	}
//	
//	// 게시판 새 글 쓰기 처리 페이지
//	@Override
//	public int writeNewAction(BoardVO vo) {
//		
//		int insertCnt = 0;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			System.out.println(vo.getWriter());
//			System.out.println(vo.getPwd());
//			System.out.println(vo.getSubject());
//			System.out.println(vo.getContent());
//			
//			String sql = "INSERT INTO board (num, writer, pwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date) "
//						+ "VALUES(board_seq.NEXTVAL, ?, ?, ?, ?, 0, board_seq.CURRVAL, 0, 0, sysdate)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getWriter());
//			pstmt.setString(2, vo.getSubject());
//			pstmt.setString(3, vo.getContent());
//			pstmt.setString(4, vo.getPwd());
//			
//			insertCnt = pstmt.executeUpdate();
//			
//			return insertCnt;
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
//			
//		}
//		return insertCnt;
//	}
//	
//}
