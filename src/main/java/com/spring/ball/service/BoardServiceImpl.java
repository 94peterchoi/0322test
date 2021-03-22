//package com.spring.ball.service;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.spring.ball.persistence.BoardDAO;
//import com.spring.ball.persistence.BoardDAOImpl;
//import com.spring.ball.vo.BoardVO;
//
//@Service
//public class BoardServiceImpl implements BoardService {
//	
//	@Autowired
//	BoardDAO dao;
//	
//	// 게시판 글 목록
//	@Override
//	public void boardList(HttpServletRequest req, HttpServletResponse res) {
//		
//		// 3단계. 화면으로 입력받은 값을 받아온다.
//		// 페이징
//		int pageSize = 10;		// 한 페이지당 출력할 글 갯수
//		int pageBlock = 3;		// 한 블럭당 페이지 갯수
//		
//		int cnt = 0;			// 글 갯수
//		int start = 0;			// 현재 페이지 시작 글 번호
//		int end = 0;			// 현재 페이지 마지막 글 번호
//		int number = 0;			// 출력용 글 번호
//		String pageNum = "";	// 페이지 번호
//		int currentPage = 0;	// 현재 페이지
//		
//		int pageCount = 0;		// 페이지 갯수
//		int startPage = 0;		// 화면에 뜬 시작페이지
//		int endPage = 0;		// 화면에 뜬 마지막 페이지
//		
//		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAOImpl dao = BoardDAOImpl.getInstance();
//		
//		// 5-1단계. 게시글 조회
//		cnt = dao.getBoardCnt();	// 조회된 DB 전체 행 갯수(selectCnt)를 반환하고 cnt에 대입
//		System.out.println("cnt => " + cnt);
//		
//		pageNum = req.getParameter("pageNum");	// 브라우저 요청으로 넣은 pageNum 값을 가져온다.
//		
//		if(pageNum == null) {
//			pageNum = "1";	// 첫 페이지를 1페이지로 지정
//		}
//		System.out.println("pageNum : " + pageNum);
//		
//		// 글 30건 기준  => (pageNum != null)인 경우
//		currentPage = Integer.parseInt(pageNum);	// 현재 페이지 : 1
//		System.out.println("currentPage : " +  currentPage);
//		
//		// 페이지 갯수 6 = (30 / 5) + (0 || 1)
//		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // 페이지갯수 + 나머지 있으면 1페이지 추가
//		System.out.println("pageCount : " +  pageCount);
//		
//		// 현재페이지 시작 글번호(페이지별) => 최신글을 먼저 봐야 하므로 내림차순 정렬이 돼야 한다. 또, 페이지별로 글 번호가 1~5 범위로 반복돼야 한다.
//		start = (currentPage - 1) * pageSize + 1;
//		
//		// 현재 페이지 마지막 글 번호(페이지별) => 가장 끝 페이지의 마지막 글 번호는 start 변수만으론 처리할 수 없다.
//		end = start + pageSize - 1;
//		
//		System.out.println("start : " + start);
//		System.out.println("end : " + end);
//		
//		// 출력용 글 번호 / 최종 페이지 => 30번 / 1페이지
//		number = cnt - (currentPage - 1) * pageSize;
//		System.out.println("number : " + number);
//		System.out.println("pageSize : " + pageSize);
//		
//		// 시작 페이지
//		startPage = (currentPage / pageBlock) * pageBlock + 1;
//		if(currentPage % pageBlock == 0) startPage -= pageBlock;
//		
//		System.out.println("startPage : " + startPage);
//					
//		// 마지막 페이지
//		endPage = startPage + pageBlock -1;
//		if(endPage > pageCount) endPage = pageCount;
//		
//		System.out.println("endPage : " + endPage);
//		
//		System.out.println("====================");
//		
//		if(cnt > 0) {
//		// 5-2단계. 게시판 글 목록 조회
//			ArrayList<BoardVO> dtos = dao.getBoardList(start, end);
//			
//			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서) 
//			req.setAttribute("dtos", dtos);
//		}
//		
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서)
//		
//		req.setAttribute("cnt", cnt);				// 글 갯수
//		req.setAttribute("number", number);			// 출력용 글 번호
//		req.setAttribute("pageNum", pageNum);	// 페이지 번호
//		
//		if(cnt > 0) {	// 앞 if문과 조건이 같아도 문제가 없다. 앞 if문 처리 후 실행.
//			req.setAttribute("startPage", startPage);		// 시작페이지
//			req.setAttribute("endPage", endPage);			// 마지막페이지
//			req.setAttribute("pageBlock", pageBlock);		// 한 블럭당 페이지 갯수
//			req.setAttribute("pageCount", pageCount);		// 페이지 갯수
//			req.setAttribute("currentPage", currentPage);	// 현재 페이지
//		}
//		
//	}
//	
//	// 게시판 글 상세 페이지
//	@Override
//	public void content(HttpServletRequest req, HttpServletResponse res) {
//		
//		// 3단게. 화면으로 입력받은 값을 받아온다.
//		// <a href = "content.bo?num=${dto.num}&pageNum=${pageNum}&number=${number}">${dto.subject}</a>
//		int num = Integer.parseInt(req.getParameter("num"));
//		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		int number = Integer.parseInt(req.getParameter("number"));
//		
//		// 4단게. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAO dao = BoardDAOImpl.getInstance(); 
//		
//		// 5-1단계. 조회수 증가
//		dao.addReadCnt(num);
//		
//		// 5-2단계. 상세페이지 조회
//		BoardVO vo = dao.getBoardDetail(num);
//		
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
//		req.setAttribute("dto", vo);
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("number", number);
//	}
//	
//	// 게시판 글 수정 상세 페이지
//	@Override
//	public void modifyView(HttpServletRequest req, HttpServletResponse res) {
//		// 3단게. 화면으로 입력받은 값(get방식)을 받아온다.
//		int num = Integer.parseInt(req.getParameter("num"));
//		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		String strPwd = req.getParameter("pwd");
//		
//		// 4단게. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAO dao = BoardDAOImpl.getInstance(); 
//		
//		// 5-1단계. 비밀번호 인증
//		int selectCnt = dao.numPwdCheck(num, strPwd);
//		
//		
//		// 5-2단계. 상세페이지 조회
//		if(selectCnt == 1) {
//			BoardVO vo = dao.getBoardDetail(num);
//			req.setAttribute("dto", vo);	// 바구니 참조변수
//		}
//		
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
//		req.setAttribute("num", num);
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("selectCnt", selectCnt);
//		
//	}
//	
//	// 게시판 글 수정 처리페이지
//	@Override
//	public void modifyAction(HttpServletRequest req, HttpServletResponse res) {
//		// 3단계. 화면으로부터 입력받은 값 가져오기
//		int num = Integer.parseInt(req.getParameter("num"));
//		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		
//		// 바구니 생성
//		BoardVO vo = new BoardVO();		
//		
//		// 화면에서 입력받은 값(작성자, 제목, 내용), hidden으로 넘긴 num 값도 BoardVO 바구니에 넘겨야 한다.
//		vo.setNum(num);
//		vo.setWriter(req.getParameter("writer"));
//		vo.setSubject(req.getParameter("subject"));
//		vo.setContent(req.getParameter("content"));
//		
//		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAO dao = BoardDAOImpl.getInstance(); 
//		
//		// 5단계. 글 수정 처리
//		int updateCnt = dao.updateBoard(vo);
//		
//		// 6. request나 session에 처리결과를 저장 
//		req.setAttribute("updateCnt", updateCnt);
//		// req.setAttribute("num", num); 수정 후 결과를 보여주는 과정에선 이 변수는 딱히 쓸 일이 없다.
//		req.setAttribute("pageNum", pageNum);
//	}
//	
//	// 게시글 쓰기
//	@Override
//	public void write(HttpServletRequest req, HttpServletResponse res) {
//		// 3단계. 화면에서 입력받은 값(hidden값, input 값)을 받아온다.
//		// 신규 제목글(답변글이 아닌 경우) =>write.bo?pageNum=${pageNum}
//		int num = 0;
//		int pageNum = 0;
//		int ref = 0;
//		int ref_step = 0;
//		int ref_level = 0;
//		
//		// 답변글 작성시
//		// 상세페이지의 답글 쓰기 버튼 클릭
//		// => 'write.bo?num=${dto.num}&pageNum=${pageNum}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'
//		if(req.getParameter("num") != null) {
//			num = Integer.parseInt(req.getParameter("num"));
//			
//			ref = Integer.parseInt(req.getParameter("ref"));
//			ref_step = Integer.parseInt(req.getParameter("ref_step"));
//			ref_level = Integer.parseInt(req.getParameter("ref_level"));
//		}
//		
//		pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
//		req.setAttribute("num", num);
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("ref", ref);
//		req.setAttribute("ref_step", ref_step);
//		req.setAttribute("ref_level", ref_level);
//	}
//	
//	// 게시판 글 쓰기 처리 페이지
//	@Override
//	public void writeAction(HttpServletRequest req, HttpServletResponse res) {
//		
//		// BoardVO 바구니 생성
//		BoardVO vo = new BoardVO();
//		
//		// 3-1단계. 화면으로부터 입력받은 값(hidden값)을 받아와서 바구니에 담는다.
//		vo.setNum(Integer.parseInt(req.getParameter("num")));
//		vo.setRef(Integer.parseInt(req.getParameter("ref")));
//		vo.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
//		vo.setRef_level(Integer.parseInt(req.getParameter("ref_level")));
//		
//		String pageNum = req.getParameter("pageNum");
//		
//		// 3-2단계. 화면으로부터 입력받은 값(input값 - 작성자, 비밀번호, 글제목, 글내용)을 받아온다.
//		vo.setWriter(req.getParameter("writer"));
//		vo.setPwd(req.getParameter("pwd"));
//		vo.setSubject(req.getParameter("subject"));
//		vo.setContent(req.getParameter("content"));
//		
//		// 3-3단계. 작성일, IP
//		// 아래 문장이 없다면, default에 sysdate로 설정되어 있을 경우 sysdate로 insert된다.
//		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
//		
//		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAO dao = BoardDAOImpl.getInstance();
//		
//		// 5단계. 글쓰기 처리
//		int insertCnt = dao.insertBoard(vo);
//		System.out.println("insertCnt : " + insertCnt);
//		
//		// 6. request나 session에 처리결과를 저장
//		req.setAttribute("insertCnt", insertCnt);
//		req.setAttribute("pageNum", pageNum);
//		
//	}
//	
//	// 게시글 검색
//	@Override
//	public void searchBoard(HttpServletRequest req, HttpServletResponse res) {
//		// 3단계. 화면으로 입력받은 값을 받아온다.
//		String keyword = req.getParameter("keyword");
//		String inputKey = "%" + keyword + "%";
//		
//		// 페이징
//		int pageSize = 10;		// 한 페이지당 출력할 글 갯수
//		int pageBlock = 3;		// 한 블럭당 페이지 갯수
//		
//		int searchCnt = 0;			// 글 갯수
//		int start = 0;			// 현재 페이지 시작 글 번호
//		int end = 0;			// 현재 페이지 마지막 글 번호
//		int number = 0;			// 출력용 글 번호
//		String pageNum = "";	// 페이지 번호
//		int currentPage = 0;	// 현재 페이지
//		
//		int pageCount = 0;		// 페이지 갯수
//		int startPage = 0;		// 화면에 뜬 시작페이지
//		int endPage = 0;		// 화면에 뜬 마지막 페이지
//		
//		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAOImpl dao = BoardDAOImpl.getInstance();
//		
//		// 5-1단계. 게시글 조회
//		searchCnt = dao.getSearchBoardCnt(inputKey);	// 조회된 DB 전체 행 갯수(selectCnt)를 반환하고 searchCnt에 대입
//		System.out.println("searchCnt => " + searchCnt);
//		
//		pageNum = req.getParameter("pageNum");	// 브라우저 요청으로 넣은 pageNum 값을 가져온다.
//		
//		if(pageNum == null) {
//			pageNum = "1";	// 첫 페이지를 1페이지로 지정
//		}
//		System.out.println("pageNum : " + pageNum);
//		
//		// 글 30건 기준  => (pageNum != null)인 경우
//		currentPage = Integer.parseInt(pageNum);	// 현재 페이지 : 1
//		System.out.println("currentPage : " +  currentPage);
//		
//		// 페이지 갯수 6 = (30 / 5) + (0 || 1)
//		pageCount = (searchCnt / pageSize) + (searchCnt % pageSize > 0 ? 1 : 0); // 페이지갯수 + 나머지 있으면 1페이지 추가
//		System.out.println("pageCount : " +  pageCount);
//		
//		// 현재페이지 시작 글번호(페이지별) => 최신글을 먼저 봐야 하므로 내림차순 정렬이 돼야 한다. 또, 페이지별로 글 번호가 1~5 범위로 반복돼야 한다.
//		start = (currentPage - 1) * pageSize + 1;
//		
//		// 현재 페이지 마지막 글 번호(페이지별) => 가장 끝 페이지의 마지막 글 번호는 start 변수만으론 처리할 수 없다.
//		end = start + pageSize - 1;
//		
//		System.out.println("start : " + start);
//		System.out.println("end : " + end);
//		
//		// 출력용 글 번호 / 최종 페이지 => 30번 / 1페이지
//		number = searchCnt - (currentPage - 1) * pageSize;
//		System.out.println("number : " + number);
//		System.out.println("pageSize : " + pageSize);
//		
//		// 시작 페이지
//		startPage = (currentPage / pageBlock) * pageBlock + 1;
//		if(currentPage % pageBlock == 0) startPage -= pageBlock;
//		
//		System.out.println("startPage : " + startPage);
//					
//		// 마지막 페이지
//		endPage = startPage + pageBlock -1;
//		if(endPage > pageCount) endPage = pageCount;
//		
//		System.out.println("endPage : " + endPage);
//		
//		System.out.println("====================");
//		
//		if(searchCnt > 0) {
//		// 5-2단계. 게시판 글 목록 조회
//			ArrayList<BoardVO> dtos = dao.searchBoardList(start, end, inputKey);
//			
//			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서) 
//			req.setAttribute("dtos", dtos);
//		}
//		
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서)
//		
//		req.setAttribute("searchCnt", searchCnt);				// 글 갯수
//		req.setAttribute("number", number);			// 출력용 글 번호
//		req.setAttribute("pageNum", pageNum);	// 페이지 번호
//		
//		if(searchCnt > 0) {	// 앞 if문과 조건이 같아도 문제가 없다. 앞 if문 처리 후 실행.
//			req.setAttribute("keyword", keyword);
//			req.setAttribute("startPage", startPage);		// 시작페이지
//			req.setAttribute("endPage", endPage);			// 마지막페이지
//			req.setAttribute("pageBlock", pageBlock);		// 한 블럭당 페이지 갯수
//			req.setAttribute("pageCount", pageCount);		// 페이지 갯수
//			req.setAttribute("currentPage", currentPage);	// 현재 페이지
//		}
//	}
//	
//	// 게시판 글 인증 - 삭제 처리 페이지
//	@Override
//	public void deleteBoardAction(HttpServletRequest req, HttpServletResponse res) {
//		// 3단게. 화면으로 입력받은 값(get방식)을 받아온다.
//		int deleteCnt = 0;
//		int num = Integer.parseInt(req.getParameter("num"));
//		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		String strPwd = req.getParameter("pwd");
//		
//		// 4단게. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
//		BoardDAO dao = BoardDAOImpl.getInstance(); 
//		
//		// 5-1단계. 비밀번호 인증
//		int selectCnt = dao.numPwdCheck(num, strPwd);
//		
//		// 5-2단계. 삭제
//		if(selectCnt == 1) {
//			deleteCnt = dao.deleteBoardAction(num);
//		}
//		
//		System.out.println("selectCnt : " + selectCnt);
//		System.out.println("deleteCnt : " + deleteCnt);
//		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("selectCnt", selectCnt);
//		req.setAttribute("deleteCnt", deleteCnt);
//		
//	}
//
//	// 게시판 새 글 쓰기 처리 페이지
//	@Override
//	public void writeNewAction(HttpServletRequest req, HttpServletResponse res) {
//		
//		BoardVO vo = new BoardVO();
//		
//		vo.setWriter(req.getParameter("writer"));
//		vo.setPwd(req.getParameter("pwd"));
//		vo.setSubject(req.getParameter("subject"));
//		vo.setContent(req.getParameter("content"));
//		
//		BoardDAOImpl dao = BoardDAOImpl.getInstance();
//		
//		int insertCnt = dao.writeNewAction(vo);
//		
//		req.setAttribute("insertCnt", insertCnt);
//		
//	}
//}
