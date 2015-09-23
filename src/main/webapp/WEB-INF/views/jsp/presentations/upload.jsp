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

	<h1>Upload A File To Presentation</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${presentation.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Conference</label>
		<div class="col-sm-10">${presentation.conferenceId}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Title</label>
		<div class="col-sm-10">${presentation.title}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Authors</label>
		<div class="col-sm-10">${presentation.authors}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Abstract</label>
		<div class="col-sm-10">${presentation.abs}</div>
	</div>

</div>

	<form action="/ConferencePresentations/presentations/upload" method="POST" enctype="multipart/form-data">
		<table border="0">
			<tbody>
				<tr>
					<td>Upload File</td>
					<td>
						<input type="file" name="fileUpload">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="submit" value="Upload">
					</td>
				</tr>
			</tbody>
		</table>

	</form>
<jsp:include page="../fragments/footer.jsp" />

</body>
</html>