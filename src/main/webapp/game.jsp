<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>
</head>
<body>
	<p>${sessionScope.printHangMan}</p>
	<p>${sessionScope.result}</p>
	<form method="post">
		<label>Guess:</label> <input type=text name="guess"></input>
	</form>
</body>
</html>