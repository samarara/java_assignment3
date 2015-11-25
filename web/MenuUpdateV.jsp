<%-- 
    Document   : MenuUpdateV
    Created on : Nov 3, 2015, 12:43:55 AM
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
        
        <table>
            <tr>
                <td id="ShowTables" style="display:none">
                    <form action="MenuManagerC" method="POST">
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
                    </form>
                </td>
                <td id="UpdateTables" style="display:block">
                    <form action="MenuManagerC" method="POST">
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
                    </form>
                    <%--  --%>
                    <br>
                    <form>
                    <div class="scrollContainer">
                        <c:choose>
                            <c:when test="${tableName == 'frequents'}">
                                <p style="font-weight:bold">Frequents</p>
                                <table>
                                    <tr>
                                        <td>Student ID:</td>
                                        <td><input type="text" name="snum" maxlength="4"></td>
                                    </tr>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" name="name" maxlength="15"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <table>
                                                <thead><tr><td>Foodservice, Branch #</td></tr></thead>
                                                <tr>
                                                    <td>
                                                        <c:forEach var="row" items="${branch}">
                                                            <input type="radio" name="updateThis" value="${row[0]} ${row[1]}"> <c:out value="${row[0]}, ${row[1]}"/>
                                                            <br>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <input type="submit" value="Add" action="Add">
                                <input type="submit" value="Delete" action="Delete">
                                <input type="reset" value="Reset">
                            </c:when>
                            <c:when test="${tableName == 'student'}">
                                <p style="font-weight:bold">Student</p>
                                <table>
                                    <tr>
                                        <td>SNum:</td>
                                        <td><input type="text" name="snum" maxlength="4" required></td>
                                    </tr>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" name="name" maxlength="15"></td>
                                    </tr>
                                    <tr>
                                        <td>Major:</td>
                                        <td><input type="text" name="major" maxlength="10"></td>
                                    </tr>
                                    <tr>
                                        <td>Hobby</td>
                                        <td><input type="text" name="hobby" maxlength="25"></td>
                                    </tr>
                                </table>
                                <input type="submit" value="Insert" action="Add">
                                <input type="submit" value="Update" action="Update">
                                <input type="submit" value="Delete" action="Delete">
                                <input type="reset" value="Reset">
                            </c:when>
                            <c:when test="${tableName == 'foodservice'}">
                                <p style="font-weight:bold">Foodservice</p>
                                <table>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" name="name" maxlength="15" required></td>
                                    </tr>
                                    <tr>
                                        <td>Category:</td>
                                        <td><input type="text" name="category" maxlength="10"></td>
                                    </tr>
                                    <tr>
                                        <td>Rate:</td>
                                        <td><input type="text" name="rate" maxlength="5"></td>
                                    </tr>
                                </table>
                                <input type="submit" value="Insert" action="Add">
                                <input type="submit" value="Update" action="Update">
                                <input type="submit" value="Delete" action="Delete">
                                <input type="reset" value="Reset">
                            </c:when>
                            <c:when test="${tableName == 'branch'}">
                                <p style="font-weight:bold">Branch</p>
                                <table>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" name="name" maxlength="15" required></td>
                                    </tr>
                                    <tr>
                                        <td>BNum:</td>
                                        <td><input type="text" name="bnum" maxlength="3" required></td>
                                    </tr>
                                    <tr>
                                        <td>Location:</td>
                                        <td><input type="text" name="location" maxlength="25"></td>
                                    </tr>
                                    <tr>
                                        <td>Since:</td>
                                        <td><input type="date" name="since"></td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:when test="${tableName == 'food'}">
                                <p style="font-weight:bold">Food</p>
                                <table>
                                    <tr>
                                        <td>Name:</td>
                                        <td><input type="text" name="name" maxlength="15" required></td>
                                    </tr>
                                    <tr>
                                        <td>Cuisine:</td>
                                        <td><input type="text" name="cuisine" maxlength="15"></td>
                                    </tr>
                                    <tr>
                                        <td>Type:</td>
                                        <td><input type="text" name="type" maxlength="10"></td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:when test="${tableName == 'provides'}">
                                <p style="font-weight:bold">Provides</p>
                                <table>
                                    <tr>
                                        <td>Food Service:</td>
                                        <td>
                                        <select name="foodservice">
                                            <c:forEach var="fsname" items="${foodservice}">
                                                <option value="${fsname}"><c:out value="${fsname}"/></option>
                                            </c:forEach>
                                        </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Food:</td>
                                        <td>
                                        <select name="food">
                                            <c:forEach var="fname" items="${food}">
                                                <option value="${fname}"><c:out value="${fname}"/></option>
                                            </c:forEach>
                                        </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Price:</td>
                                        <td><input type="text" name="type" maxlength="5"></td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:when test="${tableName == 'likes'}">
                                <p style="font-weight:bold">Likes</p>
                                <table>
                                    <tr>
                                        <td>Snum:</td>
                                        <td><input type="text" name="snum" maxlength="10" required></td>
                                    </tr>
                                    <tr>
                                        <td>Food:</td>
                                        <td>
                                        <select name="food">
                                            <c:forEach var="fname" items="${food}">
                                                <option value="${fname}"><c:out value="${fname}"/></option>
                                            </c:forEach>
                                        </select>
                                        </td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <label>You must select frequents, student, foods, provides, likes, foodservice, or branch to update</label>
                            </c:otherwise>
                        </c:choose>
                    </div>  
                    </form>
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
    </body>
</html>
