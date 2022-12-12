<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="service.*"%>

<%
	request.setCharacterEncoding("utf-8");
String category = request.getParameter("category");
	System.out.println(category);
	
	int year = 0;
	if(request.getParameter("year") == null) {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
	} else {
		year = Integer.parseInt(request.getParameter("year"));
	}
	System.out.println(year);
	//모델 호출
	
	// 월별 수입.지출
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	CashService cashService = new CashService();
	list = cashService.getCashListByMonth(category, year);
	
	// 페이징year
	HashMap<String, Object> map = cashService.getMaxMinYear(); 
	int minYear = (Integer)map.get("minYear");
	int maxYear = (Integer)map.get("maxYear");
	System.out.println(minYear);
	System.out.println(maxYear);
	
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
		<h1><%=year%>년 월별 <%=category%>합계 목록</h1>
		<a href="<%=request.getContextPath()%>/index.jsp">INDEX</a>
		<table border="1">
			<tr>
				<th>월</th>
				<th>금액</th>
			</tr>
				<%
					for(HashMap<String, Object> m : list){
				%>
						<tr>
							<td><%=m.get("month")%></td>
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
		<!-- 페이징 -->
		<div>
			<%
				if(year > minYear) {
			%>
					<a href="<%=request.getContextPath()%>/cashListByMonth.jsp?category=<%=category%>&year=<%=year-1%>">이전</a>
			<%		
				}
			
				if(year < maxYear) {
			%>
					<a href="<%=request.getContextPath()%>/cashListByMonth.jsp?category=<%=category%>&year=<%=year+1%>">다음</a>	
			<%		
				}
			%>
		</div>
	</body>
</html>