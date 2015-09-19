<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${presentationForm['new']}">
			<h1>Upload A Presentation</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Presentation</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/presentations" var="presentationActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="presentationForm" action="${presentationActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="conferenceId">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Conference</label>
				<div class="col-sm-10">
					<form:input path="conferenceId" type="text" class="form-control " id="conferenceId" placeholder="Conference ID" />
					<form:errors path="conferenceId" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="title">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Title</label>
				<div class="col-sm-10">
					<form:input path="title" class="form-control" id="title" placeholder="Title" />
					<form:errors path="title" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="authors">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Authors</label>
				<div class="col-sm-10">
					<form:input path="authors" class="form-control" id="authors" placeholder="Authors" />
					<form:errors path="authors" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="abs">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Abstract</label>
				<div class="col-sm-10">
					<form:input path="abs" class="form-control" id="abstract" placeholder="Abstract" />
					<form:errors path="abs" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${presentationForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>