<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hangman</title>
</head>
<body>
	<h1>Welcome to Hangman!</h1><br/>
	<form method="get" action="/game/${sessionScope.gameID}">
	<button type="submit">Start a new game!</button>
	</form>
</body>
</html>