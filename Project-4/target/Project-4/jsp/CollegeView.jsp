<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>College Registration Page</title>
</head>
<body>
    <form action="CollegeCtl" method="POST">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
            scope="request"></jsp:useBean>

        <center>
        <h1>
        <% if(bean!=null && bean.getID()>0){ 
        %><tr><th><font>Update College</font></th></tr>
        <% }else 
        {%> <tr><th><font>Add College</font></th></tr>
        <%} %>
         
        </h1>
           

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getID()%>"> <input
                type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy"
                value="<%=bean.getModifiedBy()%>"> <input type="hidden"
                name="createdDatetime"
                value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime"
                value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                     <th align ="left"> Name <span style="color:red">*</span></th>
                    <td><input type="text" name="name" placeholder="Enter Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td>
                         <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                     <th align ="left">Address<span style="color:red">*</span></th>
                    <td><input type="text" name="address" placeholder="Enter Address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
                         <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
                </tr>
                <tr>
                     <th align ="left"> State <span style="color:red">*</span></th>
                    <td><input type="text" name="state" placeholder="Enter State"
                        value="<%=DataUtility.getStringData(bean.getState())%>"></td>
                         <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
                <tr>
                     <th align ="left"> City <span style="color:red">*</span></th>
                    <td><input type="text" name="city" placeholder="Enter City"
                        value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
                         <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
                </tr>
                <tr>
                    <th align ="left"> Phone No <span style="color:red">*</span></th>
                    <td><input type="text" name="mobileno" placeholder="Enter PhoneNo" maxlength="10"
                        value="<%=DataUtility.getStringData(bean.getPhoneno())%>"></td>
                         <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
                </tr>


   <tr><th></th>
                <%if(bean.getID()>0) {%>
                    <td colspan="2">
                     &nbsp;  &emsp;
                    <input type="submit" name="operation" value="<%=CollegeCtl.OP_UPDATE%>">                    
                      &nbsp;  &nbsp;
                     <input type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>"></td>
                <%}else{ %>
                	 <td colspan="2">
                	  &nbsp;  &emsp;
                    <input type="submit" name="operation" value="<%=CollegeCtl.OP_SAVE%>">                    
                      &nbsp;  &nbsp;
                     <input type="submit" name="operation" value="<%=CollegeCtl.OP_RESET%>"></td>
                <%} %>
                </tr>
            </table>
    </form>    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
