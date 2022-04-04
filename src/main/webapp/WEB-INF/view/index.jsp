<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="random" class="hangman.logic.RandomBean" scope="application" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hangman</title>
</head>
<body style="text-align:center">
	<h1>Welcome to Hangman!</h1><br/>
	<form method="get" action="/games/${random.nextInt}">
	<button type="submit">Start a new game!</button>
	</form>
</body>
</html>