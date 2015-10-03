<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Conference Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${conference.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${conference.name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Research Fields</label>
		<div class="col-sm-10">${conference.fields}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Venue</label>
		<div class="col-sm-10">${conference.venue}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Conference Time</label>
		<div class="col-sm-10">${conference.conferenceTime}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Organizer</label>
		<div class="col-sm-10">${conference.organizer}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Website</label>
		<div class="col-sm-10">${conference.website}</div>
	</div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>