<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Category"%>
<%@ page import="model.CategoryList"%>
<%
	CategoryList list = CategoryList.selectAll();

	Category category = new Category();
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("id"));
	} catch (NullPointerException | NumberFormatException e) {
	}
	if (id != 0) {
		category = Category.selectById(id);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories | Task Manager</title>
<link rel="icon" type="”image/png" href="assets/logo.png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/form.css">
<style type="text/css">
div[class !="float-right"]:hover>ion-icon, div:hover>a {
	color: #000000 !important;
}
</style>
<script src="js/form.js"></script>
<script src="js/validateCategoryForm.js"></script>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<%@include file="includes/nav.jsp"%>

	<section class="container-fluid p-3">
		<div class="mb-2">
			<button class="btn btn-outline-primary" onclick="showForm();">New
				category</button>

			<div class="float-right ml-3">
				If you delete a category, all the tasks of that category will be
				deleted
				<ion-icon name="warning" class="text-danger h2 align-middle"></ion-icon>
			</div>
		</div>
		<h2 class="border-bottom border-secondary">Category List</h2>
		<%=list.toHTML()%>
	</section>
	<div id="formContainer" class="<%=(id == 0) ? "d-none" : ""%>">
		<div class="col-6 border border-primary rounded d-inline-block">
			<form class="p-3" method="post" action="ServletCategory">
				<h2 class="border-bottom border-primary mt-3 mb-3">New Category</h2>
				<input type="hidden" name="id" value="<%=category.getId()%>">
				<div class="form-group">
					<label for="name">Name</label> <input type="text" name="name"
						class="form-control" id="name" placeholder="enter name"
						value="<%=category.getName()%>">
				</div>
			</form>
			<button class="btn btn-primary ml-3 mt-0 mb-3" onclick="validate();">Submit</button>
		</div>
	</div>

	<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
</body>
</html>