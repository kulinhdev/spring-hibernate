<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<core:url value="/admin/subject-insert" var="insert" />
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Create Subject</h1>
		<a href="${rootpath}admin/subjects"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-home fa-sm text-white-50"></i> List Subjects</a>
	</div>

	<!-- Content Row -->
	<div class="row">
		<div class="col-xl-12">

			<core:if test="${message != null}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					<strong>${message}</strong>
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</core:if>

			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Add New Subject</h6>
				</div>
				<div class="card-body">
					<frm:form action="${insert}" modelAttribute="subject">

						<!-- Employee name -->
						<div class="row g-3 mt-3 align-items-center">
							<div class="col-md-2">
								<label for="name" class="form-label">Name: </label>
							</div>
							<div class="col-auto">
								<frm:input type="text" class="form-control" id="name"
									path="name" placeholder="Math, English ..." />
							</div>
							<div class="col-auto">
								<frm:errors path="name" cssClass="form-text text-danger"></frm:errors>
							</div>
						</div>

						<!-- Class -->
						<div class="row g-3 mt-4 align-items-center">
							<div class="col-md-2">
								<label for="role-id" class="col-form-label">Class: </label>
							</div>
							<div class="col-auto">
								<frm:select class="form-control form-select" id="role-id"
									path="subjetClass.id">
									<core:forEach items="${listClasses}" var="aClass">
										<frm:option value="${aClass.id}">${aClass.name}</frm:option>
									</core:forEach>
								</frm:select>
							</div>
							<div class="col-auto">
								<frm:errors path="subjetClass" cssClass="form-text text-danger"></frm:errors>
							</div>
						</div>

						<!-- Employee Status -->
						<div class="row g-3 mt-3 align-items-center">
							<div class="col-md-2">
								<label for="gender" class="form-label">Status: </label>
							</div>
							<div class="col-auto">
								<div class="form-check">
									<frm:radiobutton id="active" path="status" value="1"
										class="form-check-input" />
									<label for="active" class="form-check-label">Active</label>
								</div>
								<div class="form-check">
									<frm:radiobutton id="disable" path="status" value="0"
										class="form-check-input" />
									<label for="disable" class="form-check-label">Disable</label>
								</div>
							</div>
							<div class="col-auto">
								<frm:errors path="status" cssClass="form-text text-danger"></frm:errors>
							</div>
						</div>

						<!-- Description -->
						<div class="row g-3 mt-3 align-items-top">
							<div class="col-md-2">
								<label for="description" class="form-label">Description:
								</label>
							</div>
							<div class="col-auto">
								<frm:textarea path="description" id="description"
									class="form-control" style="height: 100px; width: 300px" />
							</div>
							<div class="col-auto">
								<frm:errors path="description" cssClass="form-text text-danger"></frm:errors>
							</div>
						</div>

						<button type="submit" class="btn btn-primary px-2 my-3">Create
							Subject</button>
					</frm:form>
				</div>
			</div>
		</div>
	</div>
</div>