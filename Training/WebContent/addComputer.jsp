<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Dashboard"> Application - Computer Database </a>
        </div>
        
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="AddComputer" method="POST" id="addComputerForm">
                        <fieldset>
                            <div class="form-group">
                                <label for="name">Computer name</label>
                                <input type="text" name="name" class="form-control" id="name" placeholder="Computer name" required>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" name="introduced" class="form-control" id="introduced" placeholder="Introduced date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" name="discontinued" class="form-control" id="discontinued" placeholder="Discontinued date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<c:forEach items="${companies}" var="company" >
                                    	<option value="${company.id}"><c:out value="${company.name}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input id="submit" type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="${pageContext.request.contextPath}/Dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
					<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js">
                    $(document).ready(function () {
                        $("#submit").click(function () {
                        	alert("Please input a hjsdgfhjxdsgfjhsdgjhfed");
                            var name = document.getElementById('name').value;
                            if (name.length == 0) {
                                alert("Please input a name");
                            }
                        });
                    });</script>
					<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
					<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                </div>
            </div>
        </div>
    </section>
</body>
</html>