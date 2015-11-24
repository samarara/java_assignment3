<%-- 
    Document   : MenuRefreshV
    Created on : Nov 3, 2015, 12:43:44 AM
    Author     : Lucas
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Service Home</title>
        <style>
            div.scrollContainer {
                width: 350px;
                height: 300px;
                overflow-y: scroll;
                overflow-x: hidden;
                display: block;
            }
            thead {
                font-weight: bold;
            }
            
        </style>
        <script language="javascript" type="text/javascript">
            function showHide(id) {
                document.getElementById(id).style.display = 'block';
                if (id === 'ShowTables') {
                    document.getElementById('UpdateTables').style.display = 'none';
                    document.getElementById('About').style.display = 'none';
                }
                if (id === 'UpdateTables') {
                    document.getElementById('ShowTables').style.display = 'none';
                    document.getElementById('About').style.display = 'none';
                }
                if (id === 'About') {
                    document.getElementById('UpdateTables').style.display = 'none';
                    document.getElementById('ShowTables').style.display = 'none';
                }
            }
        </script>
    </head>
    <body>
        <h1>Home Menu</h1>
        <table>
            <tr>
                <%-- Menu buttons to show options --%>
                <td><button onclick="showHide('ShowTables')">Show Tables</button></td>
                <td><button onclick="showHide('UpdateTables')">Update Tables</button></td>
                <td><button onclick="">Create Tables</button></td>
                <td><button onclick="showHide('About')">About</button></td>
            </tr>
        </table>
        <form action="MenuManagerC" method="POST">
            <table>
                <tr>
                    <td id="ShowTables" style="display:block">

                        <div class="scrollContainer">
                            <table>
                                Show which table?
                                <%-- options for ShowTable --%>
                                <c:forEach var="row" items="${result}">
                                    <tr><td><input type="radio" name="showThis" value="${row}"> <c:out value="${row}"/> </td></tr>
                                </c:forEach>
                            </table>
                            <input type="submit" value="Refresh" name="action">
                            <input type="submit" value="Clear" name="action">
                        </div>
                                <br>
                        <div class="scrollContainer">
                            <c:choose>
                                
                                <c:when test="${table != null}">
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <c:forEach items="${table[0]}" var="column">
                                                    <td><c:out value="${column}" /></td>
                                                </c:forEach>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${table[1]}" var="columns">
                                                <tr>
                                                    <c:forEach items="${columns}" var="column">
                                                        <td><c:out value="${column}" /></td>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <label>You must select a table!</label>
                                </c:otherwise>
                                
                            </c:choose>
                        </div>        
                    </td>
                    <td id="UpdateTables" style="display:none">
                        <div class="scrollContainer">
                            <table>
                                Update which table?
                                <%-- options for UpdateTable --%>
                                <c:forEach var="row" items="${result}">
                                    <tr><td><input type="radio" name="updateThis" value="${row}"> <c:out value="${row}"/> </td></tr>
                                </c:forEach>
                            </table>
                            <input type="submit" value="Update" name="action">
                            <input type="submit" value="Clear" name="action">
                        </div>
                    </td>
                    <td id="CreateTables" style="display:none">
                        <div>

                        </div>
                    </td>
                    <td id="About" style="display:none">
                        <div>
                            <p>
                                My Food Service web app v0.9. November 3, 2015
                                Java servlets, JSP, SQL, and Javascript keep this 
                                application functional.
                            </p>
                            <p>
                                This web application was designed by Lucas Hoang Lam Nguyen-Khuong
                                for an assignment in Enterprise Java at Sheridan College. An MVC 
                                architecture was the main design objective. If not
                                satisfactory, it will hopefully be kept and improved 
                                upon in the future. 
                            </p> 
                        </div>
                    </td>
                </tr>
            </table>
            
        </form>
    </body>
</html>
