<%@page import="in.co.rays.proj4.model.CollegeModel"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.CollegeListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>College List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">
		<%@include file="Header.jsp"%>
		<%
			List slist = (List) request.getAttribute("name");
		%>
		<center>
			<div align="center">
				<h1>College List</h1>
				<h1>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h1>
				<h1>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h1>
			</div>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<CollegeBean> it = list.iterator();

				if (list.size() != 0) {
			%>


			<table width="100%">
				<tr>
					<td align="center"><label> Name :</label> &emsp; <%=HTMLUtility.getList("colgname", String.valueOf(bean.getID()), slist)%>&emsp;
						<label>City :</label> <input type="text" name="city" placeholder="City name is required"
						value="<%=ServletUtility.getParameter("city", request)%>">
						<input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=CollegeListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr align="center">
					<th><input type="checkbox" id="select_all">Select All</th>
					<th>ID.</th>
					<th>Name.</th>
					<th>Address.</th>
					<th>State.</th>
					<th>City.</th>
					<th>PhoneNo.</th>
					<th>Edit</th>
				</tr>
				
				<%
					while (it.hasNext()) {

							CollegeBean cb = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=cb.getID()%>"></td>
					<td><%=index++%></td>
					<%--  <td><%=cb.getID()%></td> --%>
					<td><%=cb.getName()%></td>
					<td><%=cb.getAddress()%></td>
					<td><%=cb.getState()%></td>
					<td><%=cb.getCity()%></td>
					<td><%=cb.getPhoneno()%></td>
					<td><a href="CollegeCtl?id=<%=cb.getID()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEW%>"></td>


					<%
						CollegeModel model = new CollegeModel();
							if (list.size() < pageSize || model.nextPK() - 1 == bean.getID()) {
					%><td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=CollegeListCtl.OP_BACK%>"></td>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</center>
	<br>
	<br>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
			<%@include file="Footer.jsp"%>
	
</body>
</html>