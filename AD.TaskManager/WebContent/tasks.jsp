
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Task"%>
<%@ page import="model.TaskList"%>
<%@ page import="model.Category"%>
<%@ page import="model.CategoryList"%>
<%
	CategoryList categoryList = CategoryList.selectAll();

	TaskList taskList = TaskList.selectAll();

	Task task = new Task();
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("id"));
	} catch (NullPointerException | NumberFormatException e) {
	}
	if (id != 0) {
		task = Task.selectById(id);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tasks | Task Manager</title>
<link rel="icon" type="”image/png" href="assets/logo.png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/form.css">
<style type="text/css">
td:hover>ion-icon, td:hover>a {
	color: #000000 !important;
}
</style>
<script src="js/form.js"></script>
<script src="js/validateTaskForm.js"></script>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<%@include file="includes/nav.jsp"%>

	<section class="container-fluid p-3">
		<div class="mb-2">
			<button class="btn btn-outline-primary" onclick="showForm();">New
				task</button>

			<div class="float-right ml-3">
				High priority
				<div class="rounded-circle bg-danger d-inline-block align-middle"
					style="width: 20px; height: auto; padding-top: 20px;"></div>
			</div>
			<div class="float-right ml-3">
				Medium priority
				<div class="rounded-circle bg-warning d-inline-block align-middle"
					style="width: 20px; height: auto; padding-top: 20px;"></div>
			</div>
			<div class="float-right">
				Low priority
				<div class="rounded-circle bg-success d-inline-block align-middle"
					style="width: 20px; height: auto; padding-top: 20px;"></div>
			</div>
		</div>
		<h2 class="border-bottom border-secondary">Task List</h2>
		<table class="table table-borderless">
			<%=taskList.toHTML()%>
		</table>
	</section>

	<div id="formContainer" class="<%=(id == 0) ? "d-none" : ""%>">
		<div class="col-6 border border-primary rounded d-inline-block">
			<form class="p-3" method="post" action="ServletTask">
				<h2 class="border-bottom border-primary mt-3 mb-3">New Task</h2>
				<input type="hidden" name="id" value="<%=task.getId()%>">
				<div class="form-group">
					<label for="title">Title</label> <input type="text" name="title"
						class="form-control" id="title" placeholder="enter title"
						value="<%=task.getTitle()%>">
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<textarea class="form-control" id="description" name="description"
						rows="3"> <%=task.getDescription()%></textarea>
				</div>
				<div class="mb-2">Priority</div>
				<div class="form-group">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="priority"
							id="priority1" value="1"
							<%=(task.getPriority() == 1 || task.getPriority() == 0) ? "checked" : ""%>>
						<label class="form-check-label" for="priority1"> low </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="priority"
							<%=(task.getPriority() == 2) ? "checked" : ""%> id="priority2"
							value="2"> <label class="form-check-label"
							for="priority2"> medium </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="priority"
							id="priority3" value="3"
							<%=(task.getPriority() == 3) ? "checked" : ""%>> <label
							class="form-check-label" for="priority3"> high </label>
					</div>
				</div>
				<div class="form-group">
					<label for="status">Status</label> <select class="form-control"
						name="status" id="status">
						<option selected value="0">--no status--</option>
						<option value="1" <%=(task.getStatus() == 1) ? "selected" : ""%>>Pending</option>
						<option value="2" <%=(task.getStatus() == 2) ? "selected" : ""%>>On
							progress</option>
						<option value="3" <%=(task.getStatus() == 3) ? "selected" : ""%>>Ended</option>
					</select>
				</div>
				<div class="form-group">
					<label for="init_date"> Start date </label> <input
						class="form-control" type="date" name="init_date" id="init_date"
						value="<%=task.getInitDate()%>">
				</div>
				<div class="form-group">
					<label for="end_date"> Deadline </label> <input
						class="form-control" type="date" name="end_date" id="end_date"
						value="<%=task.getEndDate()%>">
				</div>
				<div class="form-group">
					<label for="category">Category</label> <select class="form-control"
						name="category" id="category">
						<option selected value="0">--no category--</option>
						<%=categoryList.toHTMLForm(task.getFk_category())%>
					</select>
				</div>
				<div class="form-group">
					<label for="dependency">Depends on...</label> <select
						class="form-control" id="dependency" name="dependency">
						<option selected value="0">--no task selected--</option>
						<%=taskList.toHTMLForm(task.getFk_dependency())%>
					</select>
				</div>
			</form>
			<button class="btn btn-primary ml-3 mt-0 mb-3" onclick="validate();">Submit</button>
		</div>
	</div>

	<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
</body>
</html>