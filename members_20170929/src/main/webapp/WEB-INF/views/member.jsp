<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%-- Spring MVC Pattern 에서 JSP 페이지 작성시 뷰 역할만 하기 때문에 액션 관련 코드는 모두 제거한다 --%>
<%-- 현재 페이지(*.jsp)를 직접 실행하지 않는다 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원관리 v6.0</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

div.panel {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

</style>

<script>
	$(document).ready(function() {

		<%-- 서블릿에서 전달받은 자료는 EL 표현을 이용해서 출력한다 --%>
		if ("${code}" == "200") {
			$(".insert-success").css("display", "inline");
		} else if ("${code}" == "500") {
			$(".insert-fail").css("display", "inline");
		} 
		var key = "${key}";
		var value = "${value}";
		$("select#key > option[value='" + key + "']").attr("selected", "selected");
		$("input#value").val(value);
	});
</script>
</head>
<body>

	<div class="container">

		<%-- contextPath 지정시 EL 표현을 사용한다 --%>
		<%-- 모든 요청 주소는 *.jsp 대신 /member/list 형식을 사용한다 --%>			
		<h3 style="margin-bottom:20px;"><a href="${pageContext.request.contextPath}/member/list"><img src="${pageContext.request.contextPath}/resources/img/sist_logo.png" alt="sist_logo.png" ></a> 회원관리 <small>v6.0 Spring MVC + MyBatis</small></h3>

		<div class="panel panel-default">
			<div class="panel-heading">회원 정보 입력</div>
			<div class="panel-body">

				<%-- 모든 요청 주소는 *.jsp 대신 /member/add 형식을 사용한다 --%>			
				<form action="${pageContext.request.contextPath}/member/add" method="post"> 
				
					<div class="form-group">
						<label for="name_">Name:</label> <input type="text"
							class="form-control" id="name_" name="name_" value=""  placeholder="max:10" required>
					</div>
					<div class="form-group">
						<label for="phone">Phone:</label> <input type="text"
							class="form-control" id="phone" name="phone"  placeholder="max:20" required>
					</div>
					
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="email"  placeholder="max:30" required>
					</div>
					
					<button type="submit" class="btn btn-default">Submit</button>
					
					
					<!-- 회원 정보 입력 액션에 대한 결과 메시지 출력 -->
					<span class="alert alert-success insert-success" style="padding:8px; display:none;">
						<strong>Success!</strong> 회원 정보 입력이 성공했습니다.
					</span>
					<span class="alert alert-danger insert-fail" style="padding:8px; display:none;">
						<strong>Fail!</strong> 회원 정보 입력이 실패했습니다.
					</span>
					
					
				</form>			
			
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">회원 정보 출력</div>
			<div class="panel-body">

				<table class="table table-striped">
					<thead>
						<tr>
							<th>Mid</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>RegDate</th>
							<th>ClientIP</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
						<tr>
							<td>1</td>
							<td>Hong</td>
							<td>010-1234-1234</td>
							<td>hong@test.com</td>
							<td>2017-08-03</td>
							<td>211.63.89.68</td>
						</tr>
						<tr>
							<td>2</td>
							<td>Kim</td>
							<td>010-4567-7890</td>
							<td>kim@naver.com</td>
							<td>2017-08-03</td>
							<td>211.63.89.72</td>
						</tr>
						 -->
						 
						<%-- 서블릿에서 전달받은 자료 중에서 컬렉션형 자료는 JSTL+EL 형식으로 작업한다 --%>
						<%-- items 속성은 배열 또는 컬렉션 자료만을 취급한다 --%>
						<c:forEach var="m" items="${members}">
							<tr>
								<%-- Member객체의 getMid() 메소드 접근 --%>
								<td>${m.mid}</td>
								<%-- Member객체의 getName_() 메소드 접근 --%>
								<td>${m.name_}</td>
								<%-- Member객체의 getPhone() 메소드 접근 --%>
								<td>${m.phone}</td>
								<%-- Member객체의 getEmail() 메소드 접근 --%>
								<td>${m.email}</td>
								<%-- Member객체의 getRegDate() 메소드 접근 --%>
								<td>${m.regDate}</td>
								<%-- Member객체의 getClientIP() 메소드 접근 --%>
								<td>${m.clientIP}</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>

				<form class="form-inline" method="post">
					<button type="button" class="btn btn-default">
						<%-- 서블릿에서 전달받은 자료는 EL 표현을 이용해서 출력한다 --%>
						Count <span class="badge">${count}</span>
					</button>
					<div class="form-group">
						<select class="form-control" id="key" name="key">
							<option value="name">Name</option>
							<option value="phone">Phone</option>
							<option value="email">Email</option>
							<option value="regDate">RegDate</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text"
							class="form-control" id="value" name="value" required>
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>

			</div>
		</div>

	</div>

</body>
</html>