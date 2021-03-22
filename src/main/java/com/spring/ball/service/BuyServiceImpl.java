//package com.spring.ball.service;
//
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.spring.ball.persistence.BuyDAO;
//import com.spring.ball.persistence.BuyDAOImpl;
//import com.spring.ball.vo.BuyVO;
//import com.spring.ball.vo.ProductVO;
//
//public class BuyServiceImpl implements BuyService {
//
//	@Autowired
//	BuyDAO dao;
//	
//	// 구매요청
//	@Override
//	public void requestBuy(HttpServletRequest req, HttpServletResponse res) {
//		
//		BuyVO vo = new BuyVO();
//		
//		vo.setGuestId(req.getParameter("guestId"));
//		vo.setPdNum(Integer.parseInt(req.getParameter("pdNum")));
//		vo.setBuycount(Integer.parseInt(req.getParameter("buycount")));
//		
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		int insertCnt = dao.insertBuy(vo);
//	
//		req.setAttribute("insertCnt", insertCnt);
//	}
//	
//	// 구매목록 보기
//	@Override
//	public void viewBuyList(HttpServletRequest req, HttpServletResponse res) {
//		
//		String clientId = (String)req.getSession().getAttribute("clientId");
//		System.out.println("구매목록보기 요청자 : " + clientId);
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
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		cnt = dao.getBuyCnt(clientId);	// 구매목록 수 세기
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
//		// 5-2단계. 구매목록 조회
//			List<ProductVO> list = dao.selectBuyList(start, end, clientId);
//			
//			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서) 
//			req.setAttribute("list", list);
//		}
//		
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
//	}
//	
//	// 구매 확정 - 관리자, 고객
//	@Override
//	public void buyConfirm(HttpServletRequest req, HttpServletResponse res) {
//		
//		int pageNum = 1;
//		int updateCnt = 0;
//		int reflectCnt = 0; 
//		
//		int pdNum = Integer.parseInt(req.getParameter("pdNum"));
//		int buyNum = Integer.parseInt(req.getParameter("buyNum"));
//		pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		int p_count = Integer.parseInt(req.getParameter("p_count"));	// 구매요청 수량
//		
//		//String auHostId = req.getParameter("auHostId");
//		
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		
//		// 재고 수량 조회
//		int pdQuantity = dao.getStockQuantity(pdNum);
//		int estQuantity = pdQuantity - p_count;
//		
//		System.out.println("조회된 재고 수량 : " + pdQuantity);
//		System.out.println("구매확정 시 예상 재고 수량 : " + estQuantity);
//		
//		// 결제 후 재고가 남는 경우 - 재고 수량 변경 및 구매확정
//		if(estQuantity >= 0) {
//			System.out.println("구매확정 시 필요한 재고 있음"); 
//			// 재고 수량 변경
//			reflectCnt = dao.confirmReflect(pdNum, estQuantity);
//			
//			// 상태코드 변경(구매확정)
//			updateCnt = dao.updateBuyState(buyNum);
//		}
//		else {
//			System.out.println("구매확정 시 필요한 재고 없음");
//		}
//		
//		req.setAttribute("reflectCnt", reflectCnt); // 재고 수량 반영(감소처리) 결과
//		req.setAttribute("updateCnt", updateCnt);	// 구매확정 처리 결과
//		req.setAttribute("pageNum", pageNum);
//		
//	}
//
//	
//	// 환불요청
//	// 이 프로젝트에서는 환불요청 시 요청수량만큼 기존 구매요청된 수량이 줄어드는 것으로 한다.
//	@Override
//	public void refundReq(HttpServletRequest req, HttpServletResponse res) {
//		
//		System.out.println("pdNum : " + req.getParameter("pdNum"));
//		
//		int pdNum = Integer.parseInt(req.getParameter("pdNum"));
//		int refundcount = Integer.parseInt(req.getParameter("refundcount"));
//		int p_count = Integer.parseInt(req.getParameter("p_count"));
//		int buyNum = Integer.parseInt(req.getParameter("buyNum"));
//		String clientId = req.getParameter("clientId");
//		
//		System.out.println("pdNum : " + pdNum);
//		System.out.println("refundcount : " + refundcount);
//		System.out.println("p_count : " + p_count);
//		System.out.println("buyNum : " + buyNum);
//		System.out.println("clientId : " + clientId);
//		
//		int subQuantity = p_count - refundcount; // 구매요청수량 - 환불요청수량 
//		System.out.println("변경될 구매요청수량 : " + subQuantity);
//		
//		// 구매요청수량 변경
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		int updateCnt = dao.refundUpdate(buyNum, subQuantity);
//		
//		// 환불목록 등록
//		int insertCnt = dao.insertRefund(pdNum, clientId, refundcount);
//		
//		System.out.println("구매요청수량 변경결과 : " + updateCnt);
//		System.out.println("환불목록 등록 결과 : " + insertCnt);
//		
//		// 속성 등록
//		req.setAttribute("updateCnt", updateCnt);
//		req.setAttribute("insertCnt", insertCnt);
//		
//	}
//
//	// 환불목록 보기
//	@Override
//	public void viewRefundList(HttpServletRequest req, HttpServletResponse res) {
//
//		String clientId = (String)req.getSession().getAttribute("clientId");
//		System.out.println("환불목록보기 요청자 : " + clientId);
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
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		cnt = dao.getRefundCnt(clientId);	// 환불목록 수 세기
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
//		// 5-2단계. 환불 목록 조회
//			List<ProductVO> list = dao.selectRefundList(start, end, clientId);
//			
//			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위해서) 
//			req.setAttribute("list", list);
//		}
//		
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
//	// 환불 확정
//	@Override
//	public void confirmRefund(HttpServletRequest req, HttpServletResponse res) {
//		
//		int pageNum = 1;
//		int refundNum = Integer.parseInt(req.getParameter("refundNum"));
//		pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		
//		int updateCnt = dao.confirmRefund(refundNum);
//		System.out.println("환불 확정 결과 : " + updateCnt);
//		req.setAttribute("updateCnt", updateCnt);
//		req.setAttribute("pageNum", pageNum);
//	}
//	
//	// 환불 취소
//	@Override
//	public void cancelRefund(HttpServletRequest req, HttpServletResponse res) {
//		
//		int updateCnt = 0;
//		int deleteCnt = 0;
//		int buyNum = Integer.parseInt(req.getParameter("buyNum"));			// 구매번호
//		int refundNum = Integer.parseInt(req.getParameter("refundNum"));	// 환불번호
//		int p_count = Integer.parseInt(req.getParameter("p_count"));		// 환불수량
//		// 현재 구매목록에 남아있는 구매요청 수량
//		
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		
//		// 현재 구매목록에 남아있는 구매요청 수량 불러오기
//		int buycount = dao.getBuyQuantity(buyNum);
//		System.out.println("현재 환불요청잔량 : " + buycount);
//		int changeBuyQuantity = buycount + p_count; // 갱신될 새 구매수량 : 구매요청잔량 + 환불취소수량
//		System.out.println("갱신될 새 구매수량 : " + changeBuyQuantity);
//		
//		// 환불 목록에서 삭제
//		deleteCnt = dao.deleteRefund(refundNum);
//		
//		// 구매요청수량에 환불취소 수량 더하기
//		if(deleteCnt == 1) {
//			updateCnt = dao.refundToBuy(buyNum, changeBuyQuantity);	// 기존에 수량이 있는 경우 문제 있음. 먼저 구매목록을 조회하고 기존 구매요청수량을 갖고와서 처리해야 함.
//		}
//		
//		req.setAttribute("deleteCnt", deleteCnt);
//		req.setAttribute("updateCnt", updateCnt);
//		
//	}
//
//	// 결산
//	@Override
//	public void closing(HttpServletRequest req, HttpServletResponse res) {
//
//		double maxValue = 100000000; // 목표 월매출(차트 최대값)
//		double annMaxValue = maxValue * 12;	// 목표 연매출
//		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM");
//		
//		String yyyymm = sdf.format(now);
//		System.out.println("날짜 데이터 : " + yyyymm);
//		
//		String[] arr = yyyymm.split(" ");
//		String year = arr[0];
//		String month = arr[1];
//		
//		System.out.println("올해 년도 : " + year);
//		System.out.println("이번달 : " + month);
//		
//		DecimalFormat df = new DecimalFormat("#,##0");
//		
//		BuyDAO dao = BuyDAOImpl.getInstance();
//		List<ClosingVO> salesList = dao.getClosingSalesData(year);
//		List<ClosingVO> standbyList = dao.getClosingStandbyData(year);
//		int annualSales = dao.annualSales(year);
//		double monthlySales = dao.monthlySales(year, month);
//		int monthlyStandby = dao.monthlyStandby(year, month);
//		
//		// 미해결 과제 : 그래프 길이를 나타내는 아래 percent 변수를 달과 년도마다 각각 다른 수치로 나오도록 해야 한다.
//		// 이를 위해 DAO단에서 percent를 계산해 ClosingVO 변수에 입력하고 List 컬렉션에 같이 담아야 한다. 연매출을 나타내는 percent도 다른 변수를 사용해야 한다.
//		// 설 쇠고 마무리할 계획
//		double result = (monthlySales / maxValue) * 100;
//		String percent = String.format("%.2f", result);
//		System.out.println("목표 달성율 : " + percent);
//		
//		int ms = (int)monthlySales;
//		String dfms = df.format(ms);
//		String dfMonthlyStandby = df.format(monthlyStandby);
//		String dfAnnualSales = df.format(annualSales);
//		String dfMaxValue = df.format(maxValue);
//		String dfAnnMaxValue = df.format(annMaxValue);
//		
//		req.setAttribute("year", year);
//		req.setAttribute("salesList", salesList);
//		req.setAttribute("standbyList", standbyList);
//		req.setAttribute("annualSales", annualSales);
//		req.setAttribute("dfAnnualSales", dfAnnualSales);
//		req.setAttribute("dfms", dfms);
//		req.setAttribute("dfMonthlyStandby", dfMonthlyStandby);
//		req.setAttribute("percent", percent);
//		req.setAttribute("dfMaxValue", dfMaxValue);
//		req.setAttribute("dfAnnMaxValue", dfAnnMaxValue);
//	}
//	
//}
