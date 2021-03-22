//package com.spring.ball.service;
//
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//
//import com.spring.ball.persistence.ProductDAO;
//import com.spring.ball.persistence.ProductDAOImpl;
//import com.spring.ball.vo.ProductVO;
//
//public class ProductServiceImpl implements ProductService {
//
//	@Autowired
//	ProductDAO dao;
//	
//	// 제품 등록
//	@Override
//	public void productRegistAction(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		
//		ProductVO vo = new ProductVO();
//		int sizeLimit = 1024 * 1024 * 10;  // 업로드 용량
//		String realDir = "C:\\apache-tomcat-8.5.61-windows-x64\\apache-tomcat-8.5.61\\wtpwebapps\\labMall_mvc\\pj_images\\";  // 파일업로드 디렉토리
//      
//		// new MultipartRequest(request, 파일업로드 디렉토리, 업로드 용량, 파일인코딩, 중복파일정책)
//      	MultipartRequest multi = new MultipartRequest(req, realDir, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
//      	
//		String fileName = "";
//		
//		try {
//			Enumeration files = multi.getFileNames();
//			
//			while(files.hasMoreElements()) {
//				String file1 = (String)files.nextElement();
//				fileName = multi.getFilesystemName(file1);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		// 첨부파일이 없는 경우
//		if(fileName == null || fileName.trim().equals("")) {
//			fileName = "-";
//		}
//		System.out.println("fileName ==> " + fileName);
//		
//		// 파일 업로드
//		vo.setPdImage(fileName);		
//		vo.setTitle(multi.getParameter("title"));
//		vo.setP_price(Integer.parseInt(multi.getParameter("p_price")));
//		vo.setP_count(Integer.parseInt(multi.getParameter("p_count")));
//		
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		// 제품 테이블 등록
//		int insertCnt = dao.insertProduct(vo);
//
//		// 제품 번호 가져오기
//		int maxPdnum = dao.getPdNum();
//		System.out.println("호출된 제품번호 최대 숫자 : " + maxPdnum);
//		
//		req.setAttribute("insertCnt", insertCnt);
//	}
//	
//	// 제품 목록 보기
//	@Override
//	public void productList(HttpServletRequest req, HttpServletResponse res) {
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
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		cnt = dao.getProductCnt();
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
//		// 5-2단계. 제품 목록 조회
//			List<ProductVO> list = dao.productList(start, end);
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
//	}
//
//	// 수량 변경
//	@Override
//	public void quantityChange(HttpServletRequest req, HttpServletResponse res) {
//		
//		String pageNum = "";
//		pageNum = req.getParameter("pageNum"); 
//		int pdNum = Integer.parseInt(req.getParameter("pdNum"));
//		int p_count = Integer.parseInt(req.getParameter("p_count"));
//		
//		if(pageNum.equals("")) {
//			pageNum = "1";
//		}
//		
//		System.out.println("pageNum : " + pageNum);
//		
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		
//		int updateCnt = dao.quantityChange(pdNum, p_count);
//		
//		req.setAttribute("updateCnt", updateCnt);
//		req.setAttribute("pageNum", pageNum);
//		
//	}
//	
//	// 제품 정보 보기
//	@Override
//	public void productView(HttpServletRequest req, HttpServletResponse res) {
//		
//		String pageNum = req.getParameter("pageNum");
//		int pdNum = Integer.parseInt(req.getParameter("pdNum"));
//		
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		
//		ProductVO vo = dao.productView(pdNum);
//		
//		req.setAttribute("vo", vo);
//		req.setAttribute("pageNum", pageNum);
//	}
//
//	// 제품 정보 수정
//	@Override
//	public void productModifyAction(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		
//		ProductVO vo = new ProductVO();
//		int sizeLimit = 1024 * 1024 * 10;  // 업로드 용량
//		String realDir = "C:\\apache-tomcat-8.5.61-windows-x64\\apache-tomcat-8.5.61\\wtpwebapps\\labMall_mvc\\pj_images\\";  // 파일업로드 디렉토리
//      
//		// new MultipartRequest(request, 파일업로드 디렉토리, 업로드 용량, 파일인코딩, 중복파일정책)
//      	MultipartRequest multi = new MultipartRequest(req, realDir, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
//      	
//		String fileName = "";
//		
//		try {
//			Enumeration files = multi.getFileNames();
//			
//			while(files.hasMoreElements()) {
//				String file1 = (String)files.nextElement();
//				fileName = multi.getFilesystemName(file1);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		// 첨부파일이 없는 경우
//		if(fileName == null || fileName.trim().equals("")) {
//			fileName = "-";
//		}
//		System.out.println("fileName ==> " + fileName);
//		
//		// 파일 업로드
//		vo.setPdImage(fileName);
//		
//		vo.setPdNum(Integer.parseInt(multi.getParameter("pdNum")));
//		vo.setTitle(multi.getParameter("title"));
//		vo.setP_price(Integer.parseInt(multi.getParameter("p_price")));
//		vo.setP_count(Integer.parseInt(multi.getParameter("p_count")));
//		
//		// 페이지 정보 가져오기
//		String pageNum = multi.getParameter("pageNum");
//		
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		int updateCnt = dao.updateProduct(vo);
//		
//		req.setAttribute("updateCnt", updateCnt);
//		System.out.println("pageNum : " + pageNum);
//		req.setAttribute("pageNum", pageNum);
//	}
//	
//	// 제품 삭제
//	@Override
//	public void productDeleteAction(HttpServletRequest req, HttpServletResponse res) {
//		
//		int pdNum = Integer.parseInt(req.getParameter("pdNum"));
//		String pageNum = req.getParameter("pageNum");
//		
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		int deleteCnt = dao.deleteProduct(pdNum);
//		
//		req.setAttribute("deleteCnt", deleteCnt);
//		req.setAttribute("pageNum", pageNum);
//		
//	}
//
//	// 환불정보 전체 보기 - 관리자
//	public void viewAllrefundList(HttpServletRequest req, HttpServletResponse res) {
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
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		cnt = dao.getRefundCnt();	// 환불목록 수 세기
//		
//		System.out.println("조회된 환불목록 수 : " + cnt);
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
//			List<ProductVO> list = dao.refundList(start, end);
//			
//			req.setAttribute("list", list);
//		}
//		System.out.println("cnt : " + cnt);
//		req.setAttribute("cnt", cnt);
//		req.setAttribute("number", number);
//		req.setAttribute("pageNum", pageNum);
//		
//		if(cnt > 0) {
//			req.setAttribute("startPage", startPage);		
//			req.setAttribute("endPage", endPage);			
//			req.setAttribute("pageBlock", pageBlock);		
//			req.setAttribute("pageCount", pageCount);		
//			req.setAttribute("currentPage", currentPage);
//		}
//	}
//	
//	// 구매요청정보 전체 보기 - 관리자
//	@Override
//	public void viewAllbuyList(HttpServletRequest req, HttpServletResponse res) {
//		
//		String clientId = (String)req.getSession().getAttribute("clientId"); 
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
//		ProductDAO dao = ProductDAOImpl.getInstance();
//		cnt = dao.getAllBuyCnt(clientId);	// 구매목록 수 세기
//		
//		System.out.println("조회된 구매목록 수 : " + cnt);
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
//		// 5-2단계. 구매 목록 전체 조회
//			List<ProductVO> list = dao.AllBuyList(clientId, start, end);
//			
//			req.setAttribute("list", list);
//		}
//		System.out.println("cnt : " + cnt);
//		req.setAttribute("cnt", cnt);
//		req.setAttribute("number", number);
//		req.setAttribute("pageNum", pageNum);
//		
//		if(cnt > 0) {
//			req.setAttribute("startPage", startPage);		
//			req.setAttribute("endPage", endPage);			
//			req.setAttribute("pageBlock", pageBlock);		
//			req.setAttribute("pageCount", pageCount);		
//			req.setAttribute("currentPage", currentPage);
//		}
//	}
//}
