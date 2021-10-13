<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


	<h1>Bienvenue</h1>
	
	<div>
		<img src="img/logo.jpg" />
	</div>
	
	<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<a href="identification" >Identification</a>
		</div>
		<div class="col-sm-4">
			<a href="facialRecognation" >Reconnaissance faciale</a>
		</div>
	</div>
	</div>
	
<jsp:include page="footer.jsp" />