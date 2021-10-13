<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


<div style="text-align:center;">
	<h1>Inscription</h1>
	
	<div>
		<img src="img/logo.jpg" />
	</div>

<div id="inscription">
	<form method="post" action="userscreation" id="inscription">
		<input 
			id="nom" name="nom" type="text" class="inputChamp"
			placeholder="Nom *" 
		    /><br /> 
		<input 
			id="prenom" name="prenom" type="text" class="inputChamp"
			placeholder="Prenom *" 
		    /><br /> 
		<input
			id="dob" name="dob" type="date" class="inputChamp"
			placeholder="Date de naissance *"
			/><br />
			<input 
			id="login" name="login" type="text" class="inputChamp"
			placeholder="Identifiant *" 
		    /><br /> 
			<input 
			id="password" name="password" type="text" class="inputChamp"
			placeholder="Mot de Passe *" 
		    /><br /> 
		<input 
			id="photo" name="photo" type="text" 
			placeholder="Photo *"
			/><br /> 
		<input 
			type = "checkbox" id="IsAdmin" name="IsAdmin" />
			<label for ="IsAdmin">Administrateur ?</label>

			<br />
			
		<input type="submit" value="Inscription" class="submitBtn" />
	</form>
	</div>

<jsp:include page="footer.jsp" />