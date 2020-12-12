<%-- 
    Document   : notification
    Created on : Oct 25, 2018, 4:26:20 PM
    Author     : sarakim & phihuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" /> 

<!DOCTYPE html>
<html>
        <link href="styles/home.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="includes/main.js"></script>
        <title>Notification</title>
</head>
<body>
    <c:if test="${user == null}">
        <c:redirect url="/login.jsp"></c:redirect>
    </c:if>
    <main>
        <homeLeft>
            <div id="profileInfo">
                <img src="images/default.jpg" height="80" width="80">
                <div id="profileName"> <c:out value="${user.fullname}"/> </div>
                <div id="profileUsername"> @<c:out value="${user.username}"/> </div>
            </div>
                <div id="tweetCount">
                    Tweets: ${tweetCount}<br>
                    Following: ${followingCount}<br>
                    Followers: ${followersCount}<br>
                </div>
            <c:forEach var="trend" items="${topTrends}">
                <div class="trendsitem">
                    <a href="hashtag?action=viewTweets&hashtagText=${trend.hashtagText}"><strong>#${trend.hashtagText}</strong></a> <br>
                <small>${trend.hashtagCount} tweets</small>
                </div>
            </c:forEach>			
        </homeLeft>
                    
        <homeMiddle>
           <c:forEach var="notification" items="${notifications}">
                <articleW>
                    <div class="media-content">
                        <div class="content">
                            <strong>${notification.fullname}</strong> <small>@${notification.username}</small>
                            <c:choose>
                                <c:when test="${notification.tablename eq 'mention'}">
                                    <br>
                                    <p>
                                        ${notification.twit}
                                        <br>
                                        <small class="timeAgo">${notification.date}</small>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    is following you!
                                    <br>
                                    <small class="timeAgo">${notification.date}</small>
                                </c:otherwise>
                            </c:choose> 
                            
                        </div>
                    </div>
                </articleW>
            </c:forEach>
        </homeMiddle>
                    
        <homeRight>
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
        </homeRight>

    </main>	
</body>
<c:import url="/footer.jsp" />