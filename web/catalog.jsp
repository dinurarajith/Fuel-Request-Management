<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fuel Request Management</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              crossorigin="anonymous">
        <style>
            body {
                background-image: url("images/newImage.PNG");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
                -moz-filter: blur(50px);

            }
        </style>

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
            <div class="col-9"><h2 class="offset-1" style="color: green;font-weight: bold">Choose Fuel Type</h2></div>
            <div class="col"><a href="<%=request.getContextPath()%>/list" class="btn btn-success"  style="font-weight: bold;color: white; margin-left: 100px">REQUEST LIST</a></div>
        </div>

        <div class="col-12">
            <table class="col-10 offset-2">
                <tr>
                    <td>
                        <div class="col-6">
                            <h3 style="font-weight: bold;color: green">Petrol 92</h3>
                            <br>
                            <h5  style="margin-top: 1px; margin-left: 20px">1 L = Rs. 410.00</h5>
                            <form action="insert" method="post">

                                <input type="hidden"
                                       value="Petrol 92" class="form-control"
                                       name="fuel" required="required">

                                <input type="hidden"
                                       value="2" class="form-control"
                                       name="fuelId" required="required">

                                <fieldset class="form-group">
                                    <label>Quantity (L)</label> <input type="text"
                                                                       value="<c:out value='${request.quantity}' />" class="form-control"
                                                                       name="quantity">
                                </fieldset>

                                <button type="submit" class="btn btn-success">REQUEST</button>

                            </form>
                        </div><br>
                    </td>
                    <td>
                        <div class="col-6">
                            <h3 style="font-weight: bold;color: green">Petrol 95</h3>
                            <br>
                            <h5  style="margin-top: 1px; margin-left: 20px">1 L = Rs. 510.00</h5>
                            <form action="insert" method="post">

                                <input type="hidden"
                                       value="Petrol 95" class="form-control"
                                       name="fuel" required="required">

                                <input type="hidden"
                                       value="1" class="form-control"
                                       name="fuelId" required="required">


                                <fieldset class="form-group">
                                    <label>Quantity (L)</label> <input type="text"value="<c:out value='${request.quantity}' />" class="form-control"name="quantity">
                                </fieldset>

                                <button type="submit" class="btn btn-success">REQUEST</button>
                            </form>
                        </div><br>                                           
                    </td>
                </tr>
                <tr><hr></tr>
                <tr>
                    <td>
                        <div class="col-6">
                            <h3 style="font-weight: bold;color: green">Diesel</h3>
                            <br>
                            <h5  style="margin-top: 1px; margin-left: 20px">1 L = Rs. 430.00</h5>
                            <form action="insert" method="post">

                                <input type="hidden"
                                       value="Deisel" class="form-control"
                                       name="fuel" required="required">

                                <input type="hidden"
                                       value="3" class="form-control"
                                       name="fuelId" required="required">

                                <fieldset class="form-group">
                                    <label>Quantity (L)</label> <input type="text"
                                                                       value="<c:out value='${request.quantity}' />" class="form-control"
                                                                       name="quantity">
                                </fieldset>

                                <button type="submit" class="btn btn-success">REQUEST</button>
                            </form>
                        </div>
                    </td>
                    <td>
                        <div class="col-6">
                            <h3 style="font-weight: bold;color: green">Super Deisel</h3>
                            <br>
                            <h5  style="margin-top: 1px; margin-left: 20px">1 L = Rs. 510.00</h5>
                            <form action="insert" method="post">

                                <input type="hidden"
                                       value="Super Deisel" class="form-control"
                                       name="fuel" required="required">

                                <input type="hidden"
                                       value="4" class="form-control"
                                       name="fuelId" required="required">

                                <fieldset class="form-group">
                                    <label>Quantity (L)</label> <input type="text"
                                                                       value="<c:out value='${request.quantity}' />" class="form-control"
                                                                       name="quantity">
                                </fieldset>

                                <button type="submit" class="btn btn-success">REQUEST</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>


    </body>
</html>
