<%-- 
    Document   : home.jsp
    Created on : Oct 5, 2018 4:34:28 PM
    Author     : Phi Huynh & Sara Kim

https://stackoverflow.com/questions/12368910/html-display-image-after-selecting-filename
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <link href="styles/home.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="includes/main.js"></script>
        <title>${activeHashtag}</title>
    </head>
    <c:if test="${user == null}">
        <c:redirect url="/login.jsp" />
    </c:if>
    
    <body>
        <h1>${activeHashtag}</h1>
        <div id="errorMessage">${errorMessage}</div>
        <main>
            <homeLeft>
            <c:forEach var="trend" items="${topTrends}">
                <div class="trendsitem">
                    <a href="hashtag?action=viewTweets&hashtagText=${trend.hashtagText}"><strong>#${trend.hashtagText}</strong></a> <br>
                <small>${trend.hashtagCount} tweets</small>
                </div>
            </c:forEach>
            <div class="followWho">Who To Follow<br>
            <c:forEach var="user" items="${users}">
            <articleW>
                <strong>${user.fullname}</strong><small> @${user.username}</small><br>
                <c:set var="contains" value="false" />
                <c:forEach var="follow" items="${followedList}">
                    <c:if test="${follow.userID eq user.userID}">
                        <c:set var="contains" value="true" />
                    </c:if>
                </c:forEach>
                
                <c:choose>
                    <c:when test="${contains eq true}">
                        <a href="" class="follow" onmouseover="follow('unfollow','${user.username}');">Unfollow</a>
                    </c:when>
                    <c:otherwise>
                        <a href="" class="follow" onmouseover="follow('follow','${user.username}');">Follow</a>
                    </c:otherwise>
                </c:choose> 
            </articleW>
            </c:forEach></div>
            </homeLeft>

            <homeMiddle>
                <c:forEach var="tweet" items="${hashtagTweets}">
                <articleW>
            <strong>${tweet.fullname}</strong> 
            <small>@${tweet.username}</small> &middot; 
            <small class="timeAgo">${tweet.time}</small>
                <%-- if tests to make sure tweets by the user only has delete button shown --%>
                <c:if test="${user.userID == tweet.tweetUserID}">
                    <a class="deleteButton" id="deleteButton" href="tweet?action=deleteTweet&tweetID=${tweet.tweetID}">X</a>
                </c:if>
                <br>${tweet.twit}
                </articleW>
                </c:forEach>
            </homeMiddle>

            <homeRight>

            </homeRight>

        </main>	
    </body>
</html>

<c:import url="/footer.jsp" />
