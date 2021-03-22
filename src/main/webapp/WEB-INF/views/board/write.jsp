<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<h2><center> 답글 쓰기</center></h2>

<!-- 비밀번호 인증 성공 -->
<c:if test="${selectCnt != 0}">
	<form action="writeAction.bo" method="post" name="mdifyform">
	
		<!-- input type="hidden"은 form 태그안에 지정해야 한다. -->
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
		
		<table align="center">
			<tr>
				<th colspan="2"> 글을 작성하세요.!! </th>
			</tr>
			
			<tr>
				<th style="width:150px"> 작성자 </th>
				<td>
					 <input class="input" type="text" name="writer" maxlength="20"
					 	 placeholder="작성자를 입력하세요" autofocus required>
				 </td>
			</tr>
			
			<tr>
				<th style="width:150px"> 비밀번호 </th>
				<td>
					 <input class="input" type="password" name="pwd" maxlength="20"
					 	 placeholder="비밀번호를 입력하세요" required>
				 </td>
			</tr>
			
			<tr>
				<th style="width:150px"> 글제목 </th>
				<td>
					 <input class="input" type="text" name="subject" maxlength="50"
					 	placeholder="글제목을 입력하세요.!!" required style="width:270px">
				 </td>
			</tr>
			
			<tr>
				<th> 글내용 </th>
				<td>
				 	<textarea class="input" rows="10" cols="50" name="content"
				 		placeholder="글내용을 입력하세요.!!" word-break:break-all style="width:270px"> </textarea>
				 </td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input class="button" type="submit" value="작성">
					<input class="button" type="reset" value="초기화">
					<input class="button" type="button" value="목록보기"
						onclick="window.location='boardList.bo?pageNum=${pageNum}'">
				</th>	
			</tr>
			
		</table>
	</form>
</c:if>

</body>
</html>