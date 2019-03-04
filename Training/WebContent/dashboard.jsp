<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
            <h1 id="homeTitle">
                <c:out value="${size} computers found"></c:out>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="Dashboard" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${searchAttri}" required/>
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="${pageContext.request.contextPath}/AddComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table id="computersTable" class="table table-striped table-bordered display">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name 
                            <i class="fa fa-fw fa-sort"></i>
                        </th>
                        <th>
                            Introduced date 
<!--                             <i class="fa fa-fw fa-sort-asc"></i> -->
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date 
<!--                             <i class="fa fa-fw fa-sort-asc"></i> -->
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company 
<!--                             <i class="fa fa-fw fa-sort-asc"></i> -->
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                      <c:forEach items="${list}" var="item">
                      	<tr>
                      		<td class="editMode">
                            	<input type="checkbox" name="cb" class="cb">
                        	</td>
                        	<td>
                        		<form action="EditComputer" method="GET" id="invisibleForm">
                        			<input type="hidden" name="computerId" value="${item.id}">
                        			<input type="hidden" name="computerName" value="${item.name}">  
                        			<input type="hidden" name="computerIntroduced" value="${item.introduced}">  
                        			<input type="hidden" name="computerDiscontinued" value="${item.discontinued}">
                        			<input type="hidden" name="companyName" value="${item.companyName}">
                        			<input type="hidden" name="companyId" value="${item.company}">
                        			<input type="submit" value="${item.name}" class="btn btn-primary">
<%--                         			<a href="${pageContext.request.contextPath}/EditComputer" onclick="document.invisibleForm.submit();">${item.name}</a>       --%>
                            	</form>
                        	</td>
                        	<td>${item.introduced}</td>
                        	<td>${item.discontinued}</td>
                        	<td>${item.companyName}</td>
                    		</tr>
      						</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              	</li>
      			<c:forEach var = "i" begin = "1" end = "${nbPages}">
         			<li><a href="${pageContext.request.contextPath}/Dashboard?&page=${i}"><c:out value="${i}">${i}</c:out></a></li>
      			</c:forEach>
<!--               	<li><a href="#">2</a></li> -->
              	<li>
                	<a href="#" aria-label="Next">
                    	<span aria-hidden="true">&raquo;</span>
                	</a>
            	</li>
        	</ul>
        </div>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default">10</button>
            <button type="button" class="btn btn-default">50</button>
            <button type="button" class="btn btn-default">100</button>
        </div>

    </footer>
<%-- <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
	
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "non-empty-string-asc": function (str1, str2) {
	        if(str1 == "")
	            return 1;
	        if(str2 == "")
	            return -1;
	        return ((str1 < str2) ? -1 : ((str1 > str2) ? 1 : 0));
	    },
	 
	    "non-empty-string-desc": function (str1, str2) {
	        if(str1 == "")
	            return 1;
	        if(str2 == "")
	            return -1;
	        return ((str1 < str2) ? 1 : ((str1 > str2) ? -1 : 0));
	    }
	} );
	
    $('#computersTable').DataTable( {
        stateSave: true,
        columnDefs: [
            {type: 'non-empty-string', targets: [2,3,4]}
         ]
    } );
} );
</script>

</body>
</html>