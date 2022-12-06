<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Request List</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              crossorigin="anonymous">

    </head>
    <body>
        <div>
            <div class="offset-1" style="text-align: center">
                <a href="<%=request.getContextPath()%>" class="navbar-brand" style="font-weight: bold;color: black;font-size: 40px"> FUEL REQUEST MANAGEMENT SYSTEM </a>
            </div>
        </div>
        <hr>
        <br>
        <div class="container row">
            <div class="col-3 offset-3">
                <h3 style="font-weight: bold" class="offset-1">Requests</h3>
            </div>

            <div class="col-4">
                <table style=" margin-left: 30px">
                    <tr style="color: purple;font-weight: bold">
                        <td>Requests Total </td> 
                        <td>| &nbsp;&nbsp;Rs. <c:out value="${totalValue.sums}" /></td>
                    </tr>
                </table>
            </div>
            <div class="col-2">
                <a style=" margin-left: 50px; font-weight: bold" href="<%=request.getContextPath()%>" class="btn btn-success">CATALOG</a>
            </div>
        </div>
        <hr>
        <br>
        <div  class="container row col-md-12">

            <div class="col-6 offset-3">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Order ID</th>
                            <th class="text-center">Fuel Type</th>
                            <th class="text-center">Quantity (ltr)</th>
                            <th class="text-center">Total Amount (Rs)</th>
                            <th class="text-center"></th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="request" items="${listRequests}">
                            <tr>
                                <td class="text-center"><c:out value="${request.id}" /></td>
                                <td class="text-center"><c:out value="${request.fuel}" /></td>
                                <td class="text-center"><c:out value="${request.quantity}" /></td>
                                <td class="text-center"><c:out value="${request.total}" /></td>
                                <td class="text-center"><a href="edit?id=<c:out value='${request.id}' />">Update</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete?id=<c:out value='${request.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
            </div>

        </div>
    </body>
</html>