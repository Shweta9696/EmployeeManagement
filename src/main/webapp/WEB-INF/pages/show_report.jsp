<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <c:choose>
    
    <c:when test="${!empty empsList }">
    
    <table align="center" border="1" bgcolor="yellow">
    <tr bgcolor="cyan">
    
    <th>Empno</th>
    <th>Ename</th>
    <th>Job</th>
    <th>Salary</th>
    <th>Deptno</th>  
     <td>Operations</td>
    </tr>
    
     <c:forEach var="emp" items="${empsList }">
     
     <tr>
    
 <td>${emp.empno}</td>
  <td>${emp.ename}</td>
   <td>${emp.job}</td>
    <td>${emp.sal}</td>
    <td>${emp.deptno}</td>
     <td><a href="edit?no=${emp.empno}"><img src="images/edit.png" width="30px" height="40px"></a>
     &nbsp;&nbsp;<a href="delete?no=${emp.empno}"><img src="images/deleted.png" width="30px" height="40px"></a>
     
     </td> 
     </tr>
     </c:forEach>
    </table>
       </c:when>
       <c:otherwise>
    <h1 style="color:red;text-align:center">NO Data Found</h1>
       </c:otherwise>
    </c:choose>
    <br><br>
    <br><br>
     <h1 style="color:green; text-align:center">${resultMsg }</h1>
     <br><br>
     <center><a href="register">Add Employee<img src="images/ad.png/" width="100px" height="80px"></a></center>
    <br><br>
      <center><a href="./">Home<img src="images/house.jpeg/" width="100px" height="80px"></a></center>
    
 
 
 
 
 
 
 
 
 
 
 
    