<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="service.*"%>

<%
	// controller -> servlet Class
	//모델 호출
	request.setCharacterEncoding("utf-8");
	String category = "";
	category = request.getParameter("category");

	
	// 년도별 수입.지출
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	CashService cashService = new CashService();
	list = cashService.getCashListByCategory(category);
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>통계</title>
	</head>
	
	<body>
		<!-- 
			전체 년도별 수입/지출
			전체 월별 수입/지출 
			
			리스트 출력(최신순) -> 날짜, 카테고리, 금액
			페이징 필요
		-->
		<h1>년도별 <%=category%> 합계 목록</h1>
		<a href="<%=request.getContextPath()%>/index.jsp">INDEX</a>
		<table border="1">
			<tr>
				<th>날짜</th>
				<th>금액</th>
			</tr>
				<%
					for(HashMap<String, Object> m : list){
				%>
						<tr>
							<td><%=m.get("year")%></td>
							<td>
								<%
									if(category.equals("수입")) {
								%>
										<span style="color:blue;"> +
								<%
									} else {
								%>
										<span style="color:red;"> -
								<%
									}
								%>
										<%=m.get("price")%>
									</span>
							</td>
						</tr>
						<%
					}
				%>
		</table>
	</body>
</html>