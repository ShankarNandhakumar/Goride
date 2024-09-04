<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.ta.Goride.dto.Passanger" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Passenger Management</title>
</head>
<body>
    <h1>Passenger Management</h1>

    <!-- Display all passengers -->
    <h2>All Passengers</h2>
    <c:if test="${not empty passangers}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Preferences</th>
                    <th>Contact Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="passanger" items="${passangers}">
                    <tr>
                        <td>${passanger.passangerid}</td>
                        <td>${passanger.passangername}</td>
                        <td>${passanger.preferences}</td>
                        <td>${passanger.contactno}</td>
                        <td>
                            <form action="passanger" method="get">
                                <input type="hidden" name="id" value="${passanger.passangerid}"/>
                                <input type="submit" value="Edit"/>
                            </form>
                            <form action="passanger" method="post" onsubmit="return confirm('Are you sure you want to delete this passenger?');">
                                <input type="hidden" name="_method" value="delete"/>
                                <input type="hidden" name="id" value="${passanger.passangerid}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Form to add a new passenger -->
    <h2>Add Passenger</h2>
    <form action="passanger" method="post">
        <label for="passangername">Name:</label>
        <input type="text" id="passangername" name="passangername" required/>
        <br/>
        <label for="preferences">Preferences:</label>
        <input type="text" id="preferences" name="preferences" required/>
        <br/>
        <label for="contactno">Contact Number:</label>
        <input type="text" id="contactno" name="contactno" required/>
        <br/>
        <input type="submit" value="Add Passenger"/>
    </form>

    <!-- Form to update an existing passenger -->
    <c:if test="${not empty selectedPassanger}">
        <h2>Update Passenger</h2>
        <form action="passanger" method="post">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="passangerid" value="${selectedPassanger.passangerid}"/>
            <label for="passangername">Name:</label>
            <input type="text" id="passangername" name="passangername" value="${selectedPassanger.passangername}" required/>
            <br/>
            <label for="preferences">Preferences:</label>
            <input type="text" id="preferences" name="preferences" value="${selectedPassanger.preferences}" required/>
            <br/>
            <label for="contactno">Contact Number:</label>
            <input type="text" id="contactno" name="contactno" value="${selectedPassanger.contactno}" required/>
            <br/>
            <input type="submit" value="Update Passenger"/>
        </form>
    </c:if>

</body>
</html>
