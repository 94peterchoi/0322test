<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.jumbotron {
	 	background-image: url('/labMall_mvc/pj_images/title_04.jpg');
	}
</style>
</head>
<body>
<br>
<br>
<br>

<div class="jumbotron jumbotron-fluid">
	<div style="color: white;" class = "container">
		<h1>결산</h1>
		<p>월별 결산 내역입니다.</p> 
	</div>
</div>

<div class = "container">
	<div class = "row">
		<div class = "col-sm-9" align = "center">
			<table class = "table table-hover">
				<tr>
					<td width = "10%">1월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar" style="width:70%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">2월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-success" style="width:20%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">3월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-info" style="width:60%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">4월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-warning" style="width:40%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">5월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-danger" style="width:50%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">6월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar" style="width:30%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">7월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-secondary" style="width:70%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">8월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-warning" style="width:60%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">9월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-dark" style="width:40%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">10월</td>
					<td width = "90%">
						<div class="progress border">
							<div class="progress-bar bg-danger" style="width:60%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">11월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-success" style="width:20%"></div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td width = "10%">12월</td>
					<td width = "90%">
						<div class="progress">
							<div class="progress-bar bg-info" style="width:80%"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>

		<div class = "col-sm-3">
			<!-- 결제하기 목록 -->
			<table class = "table table-hover">
				<tr>
					<th colspan = "2" class = "table-info">
						내역
					</th>
				</tr>
				<tr>
					<td>이달 매출</td>
					<td align = "right">12,234,300원</td>
				</tr>
				<tr>
					<td>이달 손실</td>
					<td align = "right">1,560,000원</td>
				</tr>
			</table>
			<hr>
			<table class = "table table-hover">
				<tr>
					<td class = "table-info">이달 이익</td>
					<td align = "right">10,674,300원</td>
				</tr>
			</table>
			<hr>
			<table class = "table table-hover">
				<tr>
					<td>연간 매출</td>
					<td align = "right">978,124,700원</td>
				</tr>
				<tr>
					<td>연간 손실</td>
					<td align = "right">7,640,000원</td>
				</tr>
			</table>
			<hr>
			<table class = "table table-hover">
				<tr>
					<td class = "table-info">연간 이익</td>
					<td align = "right">970,484,700원</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>