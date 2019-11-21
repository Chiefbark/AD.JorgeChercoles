
<%
	String style = "active";
	String uri = request.getRequestURI();
%>
<nav id="nav" class="pt-1">
	<ul class="nav nav-tabs">
		<li class="nav-item"><a
			class="nav-link <%=(uri.equals("/AD.Tasks/") || uri.equals("/AD.TaskManager/index.jsp")) ? style : ""%>"
			href="index.jsp">Home</a></li>
		<li class="nav-item"><a
			class="nav-link <%=(uri.equals("/AD.TaskManager/tasks.jsp")) ? style : ""%>"
			href="tasks.jsp">Tasks</a></li>
		<li class="nav-item"><a
			class="nav-link <%=(uri.equals("/AD.TaskManager/categories.jsp")) ? style : ""%>"
			href="categories.jsp">Categories</a></li>
	</ul>
</nav>