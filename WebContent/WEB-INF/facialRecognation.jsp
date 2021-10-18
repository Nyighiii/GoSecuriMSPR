<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<div style="text-align:center;">
	<h1>Reconnaissance faciale</h1>
		
<div>
	<img id="logo1" src="./img/logo.png" alt="Logo Go Securi" 
			style="width:538px;height:420px;"/>
</div>

	<div>
		<br>
		<h1 align="center"
			style="font-family: roboto; text-align: center; text-decoration: none; display: inline-block; font-size: 20px; border: none; border-radius: 12px; background-color: #ffffff; width: 600px; height: 50px; text-align: center; margin-top: 10px; margin-right: 10px; padding: 15px 32px;">Connexion avec la webcam</h1>
		<br>
		<video autoplay></video>
	</div>
	<div>
		<!--canvas id="canvasID" style="border: 1px solid #379EC1;"--></canvas>
	</div>
	<div>
		<input type="button" value="Prendre une photo" onclick="capture()"
			align=center
			style="text-align: center; text-decoration: none; display: inline-block; font-size: 16px; border: none; border-radius: 12px; background-color: #ffffff; width: 300px; height: 50px; text-align: center; margin-top: 10px; margin-right: 10px; padding: 15px 32px;" />
		<br> <br> <input type="button" value="Envoyer"
			onclick="capture()"
			style="text-align: center; text-decoration: none; display: inline-block; font-size: 16px; align: center; border: none; border-radius: 12px; background-color: #ffffff; width: 300px; height: 50px; text-align: center; margin-top: 10px; margin-right: 10px; padding: 15px 32px;" />
	</div>



	<script src="./js/main.js"></script>




<jsp:include page="footer.jsp" />