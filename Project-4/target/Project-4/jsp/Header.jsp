<!DOCTYPE html>
<%-- <%@page import="in.co.rays.ors.controller.LoginCtl"%> --%>
<%@ page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<HEAD>
<style>
form {
	height: 85% !important;
}
</style>
</HEAD>




<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'mm/dd/yy',
			//defaultDate : "06/06/2001",
			changeMonth : true,
			changeYear : true,
			//yearRange: "c-20:c+0",
			//yearRange : "1980:2025",
			maxDate : '12/31/2003',
			minDate : '01/01/1981'
		//yearRange : "-30: -18"
		});
	});
</script>
<body>
	<%
		UserBean ub = (UserBean) session.getAttribute("user");
		boolean userLoggedIn = ub != null;
		String welcomeMsg = "Hi, ";
		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += ub.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
	%>

	<table>
		<tr>
			<th></th>
			<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>">Welcome </a>
				| <%
				if (userLoggedIn) {
			%> <a
				href=" <%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout
			</a> <%
 	} else {
 %> <a href="<%=ORSView.LOGIN_CTL%>">Login </a> <%
 	}
 %></td>
			<td rowspan="2">
				<h1 align="right">
					<img src="<%=ORSView.APP_CONTEXT%>/img/Logo.jpg" width="350"
						height="90">
				</h1>
			</td>
		</tr>

		<tr>
			<th></th>
			<td>
				<h3><%=welcomeMsg%></h3>
			</td>
		</tr>

		<%
			if (userLoggedIn) {
		%>

		<tr>
			<th></th>
			<td colspan="2" "><font
				style="font-size: 18px">





					 
						<a href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</a>
					  &emsp;
					 
						<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
					   &emsp;
					 
						<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
					  &emsp;
					 
						<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
							MeritList</a>
					   <%
 	if (ub.getRoleID() == RoleBean.ADMIN) {
 %>
&emsp;
					 
						<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
					  
					 &emsp;
						<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
					   &emsp;
					 
						<a href="<%=ORSView.USER_CTL%>">Add User </a>
					  
					 &emsp;
						<a href="<%=ORSView.USER_LIST_CTL%>">User List </a>
					   &emsp;
					 
						<a href="<%=ORSView.COLLEGE_CTL%>">Add College </a>
					  
					 &emsp;
						<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List </a>
					   &emsp;
					 
						<a href="<%=ORSView.ROLE_CTL%>">Add Role </a>
					  &emsp;
					 
						<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List </a>
					   &emsp;
					 
						<a href="<%=ORSView.STUDENT_CTL%>">Add Student </a>
					  
					 &emsp;
						<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List </a>
					   &emsp;
					<br>
					<br>
					
					 
						<a href="<%=ORSView.COURSE_CTL%>">Add Course </a>
					  &emsp;
					 
						<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List </a>
					  
					&emsp;
					 
						<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject </a>
					  &emsp;
					 
						<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List </a>
					  
					&emsp;
					 
						<a href="<%=ORSView.FACULTY_CTL%>">Add Faculty </a>
					  
					 &emsp;
						<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List </a>
					  
					&emsp;
					 
						<a href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable </a>
					  &emsp;
					 
						<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List </a>
					  
					&emsp;
					 
						<a target="blank" href="<%=ORSView.JAVA_DOC_VIEW%>">Java Doc </a>
					   <%
 	}
 %> <%
 	if (ub.getRoleID() == RoleBean.STUDENT) {
 %>
&emsp;
					 
						<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List </a>
					  
					 &emsp;
						<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List </a>
					  &emsp;
					 
						<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List </a>
					  
					 &emsp;
						<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List </a>
					  &emsp;
					 
						<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List </a>
					  
					 &emsp;
						<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List </a>
					   <%
 	}
 %> <%
 	if (ub.getRoleID() == RoleBean.KIOSK) {
 %>
					 &emsp;
						<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List </a>
					  
					 &emsp;
						<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List </a>
					  &emsp;
					 
						<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List </a>
					   <%
 	}
 %> <%
 	if (ub.getRoleID() == RoleBean.FACULTY) {
 			System.out.println("======>><><>" + ub.getRoleID());
 %>

					 &emsp;
						<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet </a>
					  
					 &emsp;
						<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List </a>
					  &emsp;
					 
						<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List </a>
					  
					 &emsp;
						<a href="<%=ORSView.STUDENT_CTL%>">Add Student </a>
					  
					 &emsp;
						<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List </a>
					  
					 &emsp;
						<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List </a>
					  
					 &emsp;
						<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject </a>
					  &emsp;
					 
						<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List </a>
					  &emsp;
					 
						<a href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable </a>
					  
					 &emsp;
						<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List </a>
					   <%
 	}
 %> <%
 	if (ub.getRoleID() == RoleBean.COLLEGESCHOOL) {
 			System.out.println("======>><><>" + ub.getRoleID());
 %>

					 &emsp;
						<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet </a>
					  
					 &emsp;
						<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List </a>
					  &emsp;
					 
						<a href="<%=ORSView.STUDENT_CTL%>">Add Student </a>
					  &emsp;
					 
						<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List </a>
					  &emsp;
					 
						<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List </a>
					  &emsp;
					 
						<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List </a>
					  
					 &emsp;
						<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List </a>
					   <%
 	}
 %>

			</font></td>
		</tr>
		<%
			}
		%>
	</table>
	<hr>
</body>
</html>