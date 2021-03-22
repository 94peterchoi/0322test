//package com.spring.ball.service;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.spring.ball.persistence.CartDAO;
//import com.spring.ball.persistence.CartDAOImpl;
//import com.spring.ball.vo.CartVO;
//import com.spring.ball.vo.ProductVO;
//
//public class CartServiceImpl implements CartService {
//
//	@Autowired
//	CartDAO dao;
//	
//	// 장바구니 담기
//	@Override
//	public void shoppingCart(HttpServletRequest req, HttpServletResponse res) {
//		
//		CartVO vo = new CartVO();
//
//		vo.setPdNum(Integer.parseInt(req.getParameter("pdNum")));
//		vo.setCartcount(Integer.parseInt(req.getParameter("cartcount")));
//		vo.setGuestId(req.getParameter("guestId"));
//		
//		System.out.println("pdNum : " + vo.getPdNum());
//		System.out.println("cartcount : " + vo.getCartcount());
//		System.out.println("guestId : " + vo.getGuestId());
//		
//		CartDAO dao = CartDAOImpl.getInstance();
//		
//		int insertCnt = dao.insertCart(vo);
//		
//		req.setAttribute("insertCnt", insertCnt);
//		
//	}
//
//	// 장바구니 목록 보기
//	@Override
//	public void viewCartList(HttpServletRequest req, HttpServletResponse res) {
//		
//		String guestId = (String)req.getSession().getAttribute("clientId");
//		System.out.println("장바구니 목록보기 요청자 : " + guestId);
//		
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
//		CartDAO dao = CartDAOImpl.getInstance();
//		cnt = dao.getCartCnt(guestId);	// 장바구니 수 세기
//		
//		pageNum = req.getParameter("pageNum");
//		
//		if(pageNum == null) {
//			pageNum = "1";	// 첫 페이지를 1페이지로 지정
//		}
//		
//		currentPage = Integer.parseInt(pageNum);
//		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
//		start = (currentPage - 1) * pageSize + 1;
//		end = start + pageSize - 1;
//		number = cnt - (currentPage - 1) * pageSize;
//		startPage = (currentPage / pageBlock) * pageBlock + 1;
//		if(currentPage % pageBlock == 0) startPage -= pageBlock;
//		endPage = startPage + pageBlock -1;
//		if(endPage > pageCount) endPage = pageCount;
//		
//		if(cnt > 0) {
//		// 5-2단계. 게시판 글 목록 조회
//			List<ProductVO> list = dao.cartList(start, end, guestId);
//			
//			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서) 
//			req.setAttribute("list", list);
//		}
//		System.out.println("cnt : " + cnt);
//		req.setAttribute("cnt", cnt);
//		req.setAttribute("number", number);
//		req.setAttribute("pageNum", pageNum);
//		
//		if(cnt > 0) {	// 앞 if문과 조건이 같아도 문제가 없다. 앞 if문 처리 후 실행.
//			req.setAttribute("startPage", startPage);		
//			req.setAttribute("endPage", endPage);			
//			req.setAttribute("pageBlock", pageBlock);		
//			req.setAttribute("pageCount", pageCount);		
//			req.setAttribute("currentPage", currentPage);
//		}
//		
//	}
//	
//	// 장바구니 수량 변경
//	@Override
//	public void changeCart(HttpServletRequest req, HttpServletResponse res) {
//		
//		String guestId = req.getParameter("guestId");
//		int cartNum = Integer.parseInt(req.getParameter("cartNum"));
//		int p_count = Integer.parseInt(req.getParameter("p_count"));
//		
//		CartDAO dao = CartDAOImpl.getInstance();
//		int updateCnt = dao.updateQuantity(cartNum, p_count);
//		
//		System.out.println("updateCnt : " + updateCnt);
//		req.setAttribute("updateCnt", updateCnt);
//		
//	}
//
//	// 장바구니 빼기
//	@Override
//	public void deleteCart(HttpServletRequest req, HttpServletResponse res) {
//		
//		int pageNum = 1;
//		int cartNum = Integer.parseInt(req.getParameter("cartNum"));
//		pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		
//		CartDAO dao = CartDAOImpl.getInstance();
//		
//		int deleteCnt = dao.deleteCart(cartNum);
//		
//		req.setAttribute("deleteCnt", deleteCnt);
//		req.setAttribute("pageNum", pageNum);
//		
//	}
//
//}
