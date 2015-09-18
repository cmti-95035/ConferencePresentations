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
		<c:when test="${conferenceForm['new']}">
			<h1>Add Conference</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Conference</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/conferences" var="conferenceActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="conferenceForm" action="${conferenceActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="venue">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Venue</label>
				<div class="col-sm-10">
					<form:input path="venue" class="form-control" id="venue" placeholder="Venue" />
					<form:errors path="venue" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="conferenceTime">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Conference Time</label>
				<div class="col-sm-10">
					<form:input path="conferenceTime" class="form-control" id="conferenceTime" placeholder="conferenceTime" />
					<form:errors path="conferenceTime" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="organizer">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Organizer</label>
				<div class="col-sm-10">
					<form:input path="organizer" class="form-control" id="organizer" placeholder="organizer" />
					<form:errors path="organizer" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="website">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Website</label>
				<div class="col-sm-10">
					<form:input path="website" class="form-control" id="website" placeholder="website" />
					<form:errors path="website" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="emails">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Emails to the People of Interest</label>
				<div class="col-sm-10">
					<form:textarea path="emails" rows="5" class="form-control" id="emails" placeholder="emails" />
					<form:errors path="emails" class="control-label" />
				</div>
			</div>
		</spring:bind>

	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>