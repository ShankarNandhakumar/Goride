<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ta.Goride.dto.Driver" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Driver Management</title>
</head>
<body>
    <h1>Driver Management</h1>

    <!-- Display all drivers -->
    <h2>All Drivers</h2>
    <%
        // Retrieve the list of drivers from the request scope
        List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
        
        if (drivers != null && !drivers.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Vehicle Type</th>
                    <th>License Number</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Iterate through the list of drivers using a normal for loop
                    for (int i = 0; i < drivers.size(); i++) {
                        Driver driver = drivers.get(i);
                %>
                    <tr>
                        <td><%= driver.getDriverId() %></td>
                        <td><%= driver.getDrivername() %></td>
                        <td><%= driver.getVehicletype() %></td>
                        <td><%= driver.getLicenseno() %></td>
                        <td>
                            <form action="drivers" method="get">
                                <input type="hidden" name="id" value="<%= driver.getDriverId() %>"/>
                                <input type="submit" value="Edit"/>
                            </form>
                            <form action="drivers" method="post" onsubmit="return confirm('Are you sure you want to delete this driver?');">
                                <input type="hidden" name="_method" value="delete"/>
                                <input type="hidden" name="id" value="<%= driver.getDriverId() %>"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No drivers found.</p>
    <%
        }
    %>
</body>
</html>
