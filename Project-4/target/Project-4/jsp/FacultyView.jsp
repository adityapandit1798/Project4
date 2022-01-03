<%@page import="in.co.rays.proj4.bean.SubjectBean"%>
<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Faculty Registration Page</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <script>
	$(function() {
		$("#udateee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
			dateFormat : 'dd-mm-yy'
		});
	});
</script> -->
</head>

<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.FacultyBean" scope="request"></jsp:useBean>
	<form action="<%=ORSView.FACULTY_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<%
			List colist = (List<CollegeBean>) request.getAttribute("CollegeList");
			List<CourseBean> clist = (List<CourseBean>) request.getAttribute("CourseList");
			List<SubjectBean> slist = (List<SubjectBean>) request.getAttribute("SubjectList");
		%>

		<center>
			<h1>
			<% if(bean != null && bean.getID() >0) {%>
				<tr><th> Update Faculty </th></tr>
			<%}else{ %>
			<tr><th> Add Faculty </th></tr>
			<%} %>
			</h1>
			<div>
				<h2><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h2>
			</div>
			
			<input type="hidden" name="id" value=<%=bean.getID()%>> <input
				type="hidden" name="createdby" value=<%=bean.getCreatedBy()%>>
			<input type="hidden" name="modifiedby"
				value=<%=bean.getModifiedBy()%>> <input type="hidden"
				name="createdDatetime"
				value=<%=DataUtility.getStringData(bean.getCreatedDatetime())%>>
			<input type="hidden" name="modifiedDatetime"
				value=<%=DataUtility.getStringData(bean.getModifiedDatetime())%>>


			<table>

				 <tr>
					<th align="left">CollegeName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("collegeid", String.valueOf(bean.getCollege_id()), colist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("collegeid", request)%></font>
					</td>
				</tr> 
				<tr><th style="padding: 3px"></th></tr>
<tr>
					<th align="left">CourseName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseid", String.valueOf(bean.getCourse_id()), clist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("courseid", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">SubjectName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectid", String.valueOf(bean.getSubject_id()), slist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("subjectid", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">FirstName <span style="color: red">*</span>
					</th>
					<td><input type="text" name="firstname"
						placeholder=" Enter First Name" size="25"
						value="<%=DataUtility.getStringData(bean.getFirst_Name())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">LastName <span style="color: red">*</span></th>
					<td><input type="text" name="lastname"
						placeholder=" Enter last Name" size="25"
						value="<%=DataUtility.getStringData(bean.getLast_Name())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
								<tr>
					<th align="left">LoginId <span style="color: red">*</span></th>
					<td><input type="text" name="loginid"
						placeholder=" Enter Login Id" size="25"
						value="<%=DataUtility.getStringData(bean.getEmail_id())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("loginid", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				
				<tr>
					<th align="left">Gender <span style="color: red">*</span></th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("M", "Male");
							map.put("F", "Female");

							String hlist = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">Date of Joining <span style="color: red">*</span></th>
					<td><input type="text" name="doj" id="datepicker"
						placeholder="Enter Date Of Joining " size="25"
						value="<%=DataUtility.getDateString(bean.getDOJ())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("doj", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">Qualification <span style="color: red">*</span></th>
					<td><input type="text" name="qualification" size="25"
						placeholder=" Enter Qualification"
						value="<%=DataUtility.getStringData(bean.getQualification())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("qualification", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">MobileNo <span style="color: red">*</span></th>
					<td><input type="text" name="mobileno" size="25" maxlength="10"
						placeholder=" Enter Mobile No"
						value="<%=DataUtility.getStringData(bean.getMobile_No())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("mobileno", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				
				<tr>
					<th></th>
					<% if(bean.getID() >  0){%>
					
					<td>
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_UPDATE%>"> 
					 <input type="submit" name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
					</td>
					<%}else{ %>
					<td> 
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_SAVE%>"> 
					 <input type="submit" name="operation" value="<%=FacultyCtl.OP_RESET%>">
					</td>					
					<% } %>
				</tr>
			</table>
		</center>
	</form>
	<%@include file="Footer.jsp"%>
</body>
</html>