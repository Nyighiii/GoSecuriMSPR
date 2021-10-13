<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<h1>Identification</h1>
<div id="inscription">
	<div>
		<img src="img/logo.jpg" />
	</div>
	
	<form method="post" action="identification">

		<input
			id="pseudo" name="pseudo" type="text" class="inputChamp"
			placeholder="Votre pseudo *"
			/><br />

		<input 
			id="mdp" name="mdp"
			type="password" class="inputChamp" placeholder="Votre mot de passe *"
			/><br /> 
			
			
		<input type="submit" value="Connexion" class="submitBtn" />
	</form>
	<br>
	<label>${erreurs}</label>
	
	
	<p>Si vous n'avez pas de compte, contactez votre administrateur</p>
	
</div>

<jsp:include page="footer.jsp" />