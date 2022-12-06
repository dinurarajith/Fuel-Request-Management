<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>User Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="update" method="post">

                        <caption>
                            <h2>
                                Update Request
                            </h2>
                        </caption>

                        <input type="hidden" name="id" value="<c:out value='${request.id}' />" />

                        <table align="center" style="font-size:20px">
                                <tr>
                                    <td>ID</td>
                                    <td>:  &nbsp;&nbsp;&nbsp;&nbsp;<%= request.getParameter("id")%></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td>Fuel Type&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td>:  &nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${request.fuel}' /></td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>:  &nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${request.quantity}' /></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td>Total</td>
                                    <td>:  &nbsp;&nbsp;&nbsp;&nbsp;Rs. <c:out value='${request.total}' /></td>
                                </tr>
                            </table>
                            <br>

                        <fieldset class="form-group">
                            <label>Quantity (L)</label> <input type="text" value="<c:out value='${request.quantity}' />" class="form-control" name="quantity">
                        </fieldset>

                        <div class="container row">
                            <div class="col-8"><a href="<%=request.getContextPath()%>/list" class="btn btn-danger">Back</a></div>
                            <div class="col"><button type="submit" class="btn btn-success">Update</button></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>