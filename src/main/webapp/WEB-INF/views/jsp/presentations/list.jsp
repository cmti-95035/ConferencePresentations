<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Presentations</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Conference</th>
					<th>Title</th>
					<th>Authors</th>
					<th>Abstract</th>
					<th>Presentation File</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="presentation" items="${presentations}">
				<tr>
					<td>
						${presentation.id}
					</td>
					<td>${presentation.conferenceId}</td>
					<td>${presentation.title}</td>
					<td>${presentation.authors}</td>
					<td>${presentation.abs}</td>
					<td>${presentation.fileName}</td>
					<td>
						<spring:url value="/presentations/${presentation.id}/upload" var="presentationUrl" />
						<spring:url value="/presentations/${presentation.id}/delete" var="deleteUrl" />
						<spring:url value="/presentations/${presentation.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${presentationUrl}'">Upload A File</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>