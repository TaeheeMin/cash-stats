<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>홈</title>
	</head>
	
	<body>
	<ol>
		<li>
			<a href="<%=request.getContextPath()%>/cashListByCategory.jsp?category=수입">
				년도별 수입 목록
			</a>
		</li>
		
		<li>
			<a href="<%=request.getContextPath()%>/cashListByCategory.jsp?category=지출">
				년도별 지출 목록
			</a>
		</li>
		
		<li>
			<a href="<%=request.getContextPath()%>/cashListByMonth.jsp?category=수입">
				월별 수입 목록
			</a>
		</li>
		
		<li>
			<a href="<%=request.getContextPath()%>/cashListByMonth.jsp?category=지출">
				월별 지출 목록
			</a>
		</li>
	</ol>
	
	</body>
</html>