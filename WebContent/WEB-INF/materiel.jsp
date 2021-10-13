<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


<h1>Rendre et emprunter</h1>

<div>
	<img src="img/logo.jpg" />
</div>
<div class="container">

	<div>
		<h2>Bonjour ${user.nom} ${user.prenom}</h2>
	</div>
	<form method="post" action="materiel">


		<div class="row">

			<div>
				<c:forEach items="${materiels}" var="materiel">

					<input type="checkbox" id="chkmateriel" name="chkmateriel"
						<c:choose>
					<c:when test="${materiel.qteEnStock lt 1}">
					disabled
					</c:when>
					</c:choose>
						value="${materiel.id}" />
					<label for="${materiel.id}">${materiel.nom} Quantité en
						Stock : ${materiel.qteEnStock}</label>
					<br />
				</c:forEach>

			</div>

		</div>
		<input type="submit" value="Valider et quitter" class="submitBtn" />
	</form>
</div>

<p>
				Pour accéder à la console Admin, cliquez <a href="userscreation">ICI</a></a>
			</p>


<div>
	<c:choose>
		<c:when test="${user.isAdmin == true}">
			<p>
				Pour accéder à la console Admin, cliquez <a href="usersCreation">ICI</a></a>
			</p>
		</c:when>
	</c:choose>
</div>
<jsp:include page="footer.jsp" />