<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home | Task Manager</title>
<link rel="icon" type="”image/png" href="assets/logo.png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<%@include file="includes/nav.jsp"%>

	<section class="container pt-3 justify-content-start text-center">
		<div class="card-group col-4 d-inline-block">
			<div class="card shadow border rounded mr-2">
				<img src="assets/taskManager.png"
					class="card-img-top border-bottom border-secondary"
					alt="taskManager">
				<div class="card-body">
					<h4 class="card-title text-center">Manage Tasks</h4>
					<p class="card-text">This application will provide you help to
						manage the tasks you need to complete for your project.</p>
				</div>
				<div class="card-footer">
					<a href="tasks.jsp" class="link">Create a task now</a>
				</div>
			</div>
		</div>
		<div class="card-group col-4 d-inline-block">
			<div class="card shadow border rounded mr-2">
				<img src="assets/categoryManager.png"
					class="card-img-top border-bottom border-secondary"
					alt="categoryManager">
				<div class="card-body">
					<h4 class="card-title text-center">Manage Categories</h4>
					<p class="card-text">You can manage the categories of your
						project here. This categories can be used in any task.</p>
				</div>
				<div class="card-footer">
					<a href="categories.jsp" class="link">Create a category now</a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>