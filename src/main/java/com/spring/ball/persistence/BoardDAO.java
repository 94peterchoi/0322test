//package com.spring.ball.persistence;
//
//import java.util.ArrayList;
//
//import mvc.board.vo.BoardVO;
//
//public interface BoardDAO {
//	
//	// 게시글 갯수 구하기
//	public int getBoardCnt();
//	
//	// 게시판 글 목록 조회
//	public ArrayList<BoardVO> getBoardList(int start, int end);
//	
//	// 조회수 증가
//	public void addReadCnt(int num);
//	
//	// 게시판 글 상세 페이지, 수정 상세 페이지
//	public BoardVO getBoardDetail(int num);
//	
//	// 게시판 글 비밀번호 인증 - 수정 상세 페이지
//	public int numPwdCheck(int num, String strPwd);
//	
//	// 게시판 글 수정 처리 페이지
//	public int updateBoard(BoardVO vo);
//	
//	// 게시판 답글 쓰기 처리 페이지
//	public int insertBoard(BoardVO vo);
//	
//	// 게시판 새 글 쓰기 처리 페이지
//	public int writeNewAction(BoardVO vo);
//	
//	// 게시판 글 인증 - 삭제 처리 페이지
//	public int deleteBoardAction(int num);
//	
//	// 검색한 게시글 수 구하기
//	public int getSearchBoardCnt(String inputKey);
//	
//	// 검색한 게시글 목록 조회
//	public ArrayList<BoardVO> searchBoardList(int start, int end, String inputKey);
//	
//}
