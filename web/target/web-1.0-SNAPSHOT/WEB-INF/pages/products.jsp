<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/productajax.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        crossorigin="anonymous"></script>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Unit</th>
        <th>Measure</th>
        <th>Purchase price</th>
        <th>Sale price</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody id="productsbody">
    <c:forEach items="${sessionScope.productList}" var="product" varStatus="status">
        <tr>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.unit}</td>
            <td>${product.measure}</td>
            <td>${product.purchasePrice}</td>
            <td>${product.salePrice}</td>
            <td>${product.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<input class="button" type="button" value="Refresh" onclick="productajaxfunc()">
</body>
</html>
