<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="random" class="hangman.logic.RandomBean" scope="application" />

<html>
    <head>
        <meta charset="UTF-8">
        <title>Game</title>
    </head>
    <body style="text-align:center">
		<pre>${visual}</pre>
		<c:choose>
			<c:when test = "${isOver==null}">
				<form:form method="post" modelAttribute="gameModel">
					<label path="input">Guess:</label>
					<input path="input" name="input" required />
					<br/>
					<form:errors path="input" style="color:red"/>
				</form:form>
			</c:when>
			<c:otherwise>
			    <p>${isOver}</p>
				<form method="get" action="/games/${random.nextInt}">
					<button type="submit">New Game?</button>
				</form>
			</c:otherwise>
		</c:choose>
    </body>
</html>