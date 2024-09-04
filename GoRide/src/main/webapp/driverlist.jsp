<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Driver List</h2>

    <table>
        <thead>
            <tr>
                <th>Driver ID</th>
                <th>Name</th>
                <th>License Number</th>
                <th>Vehicle Type</th>
                <th>Available</th>
            </tr>
        </thead>
        <tbody>
            <!-- JSTL forEach loop to iterate over the drivers list -->
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>${driver.driverId}</td>
                    <td>${driver.drivername}</td>
                    <td>${driver.licenseno}</td>
                    <td>${driver.vehicletype}</td>
                    <td>${driver.available ? "Yes" : "No"}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="addDriver.jsp">Add New Driver</a>
</body>
</html>
    