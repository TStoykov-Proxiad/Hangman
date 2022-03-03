<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="random" class="hangman.RandomBean" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>
</head>
<body>
	<p><pre>${sessionScope.printHangMan}</pre></p>

	<p>${sessionScope.result}</p>
	<c:choose>
		<c:when test="${sessionScope.lost == null}">
			<form method="post">
				<label>Guess:</label> <input type=text name="guess"></input>
			</form>
		</c:when>
		<c:when test="${sessionScope.lost == true}">
		<p>You lost!</p>
			<form method="get" action="/game/${random.nextInt}">
				<button type="submit">Try again?</button>
			</form>
		</c:when>
		<c:when test="${sessionScope.lost == false}">
		<p>Congratulations, you guessed the word!</p>
			<form method="get" action="/game/${random.nextInt}">
				<button type="submit">Play another game?</button>
			</form>
		</c:when>
	</c:choose>
</body>
</html>