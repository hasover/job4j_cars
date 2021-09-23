
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Объявления о продаже машин</title>
</head>
<body>
<div class="container pt-4" id="welcome">
    <c:if test="${user == null}">
        <a href="<%=request.getContextPath()%>/auth.jsp">
            <c:out value="Привет, гость!|Войти"/>
        </a>
    </c:if>
    <c:if test="${user != null}">
        <a href="auth.do">
            <c:out value="Привет, ${user.name} | Выйти"/>
        </a>
    </c:if>
</div>
<div class="container pt-4">
    <div>
        <button type="button" class="btn btn-primary" onclick="location.href='newAd.jsp'">Новое объявление</button>
    </div>
    <h4>
        Cписок машин:
    </h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th style="width: 15%;">Фото</th>
            <th style="width: 10%">Марка</th>
            <th style="width: 10%">Кузов</th>
            <th style="width: 45%">Описание</th>
            <th style="width: 15%;">Владелец</th>
            <th style="width: 5%;">Статус</th>
        </tr>
        </thead>
        <tbody id="bodyTable">
        <c:forEach items="${ads}" var="ad">
            <tr>
                <td>
                    <img alt="фото нет" src='<c:url value="/download?photo=${ad.photo}"/>' width="130px"
                         height="100px"/>
                </td>
                <td>
                    <c:out value="${ad.carBrand.name}"/>
                </td>
                <td>
                    <c:out value="${ad.carBody.name}"/>
                </td>
                <td>
                    <c:out value="${ad.description}"/>
                </td>
                <td>
                    <c:out value="${ad.user.name}"/>
                </td>
                <td>
                    <c:if test="${ad.sold == false}">
                    <c:out value="Продается"/>
                    <c:if test="${ad.user.id == user.id}">
                        <br>
                        <a href='<c:url value="remove.do?id=${ad.id}"/>'>
                            <button class="btn btn-primary">Удалить</button>
                        </a>
                    </c:if>
                    </c:if>
                    <c:if test="${ad.sold == true}">
                        <c:out value="Продано"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
</table>
</div>
</body>
</html>
