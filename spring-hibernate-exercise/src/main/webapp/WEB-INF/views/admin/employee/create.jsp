<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<core:url value="/admin/employee-insert" var="insert" />
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Create Employee</h1>
		<a href="${rootpath}admin/employees"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-home fa-sm text-white-50"></i> List Employees</a>
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

			<frm:form action="${insert}" modelAttribute="employee"
				enctype="multipart/form-data">

				<!-- Employee name -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="name" class="form-label">Name: </label>
					</div>
					<div class="col-auto">
						<frm:input type="text" class="form-control" id="name" path="name"
							placeholder="Harry Luckky" />
					</div>
					<div class="col-auto">
						<frm:errors path="name" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee Role -->
				<div class="row g-3 mt-4 align-items-center">
					<div class="col-md-2">
						<label for="role-id" class="col-form-label">Role: </label>
					</div>
					<div class="col-auto">
						<frm:select class="form-control form-select" id="role-id"
							path="employeeRole.id">
							<core:forEach items="${listRoles}" var="role">
								<frm:option value="${role.id}">${role.name}</frm:option>
							</core:forEach>
						</frm:select>
					</div>
					<div class="col-auto">
						<frm:errors path="employeeRole" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee Avatar -->
				<div class="row g-3 mt-4 align-items-center">
					<div class="col-md-2">
						<label for="status" class="col-form-label">Avatar: </label>
					</div>
					<div class="col-auto">
						<frm:input type="hidden" path="avatar" />
						<input type="file" name=fileAvatar />
					</div>
					<div class="col-auto">
						<frm:errors path="avatar" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee email -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="email" class="form-label">Email: </label>
					</div>
					<div class="col-auto">
						<frm:input type="text" class="form-control" id="email"
							path="email" placeholder="nguyenvana@gmail.com" />
					</div>
					<div class="col-auto">
						<frm:errors path="email" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee phone -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="phone" class="form-label">Phone: </label>
					</div>
					<div class="col-auto">
						<frm:input type="text" class="form-control" id="phone"
							path="phone" placeholder="039 22332 232" />
					</div>
					<div class="col-auto">
						<frm:errors path="phone" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee Gender -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="gender" class="form-label">Gender: </label>
					</div>
					<div class="col-auto">
						<div class="form-check">
							<frm:radiobutton id="male" path="gender" value="1"
								class="form-check-input" />
							<label for="male" class="form-check-label">Male</label>
						</div>
						<div class="form-check">
							<frm:radiobutton id="female" path="gender" value="0"
								class="form-check-input" />
							<label for="female" class="form-check-label">Female</label>
						</div>
					</div>
					<div class="col-auto">
						<frm:errors path="gender" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<!-- Employee Birthday -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="birthday" class="form-label">Birthday: </label>
					</div>
					<div class="col-auto">
						<frm:input type="date" path="birthday" class="form-control" />
					</div>
					<div class="col-auto">
						<frm:errors path="birthday" cssClass="form-text text-danger"></frm:errors>
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

				<!-- Employee Address -->
				<div class="row g-3 mt-3 align-items-center">
					<div class="col-md-2">
						<label for="address" class="form-label">Address: </label>
					</div>
					<div class="col-auto">
						<frm:textarea path="address" id="address" class="form-control"
							style="height: 100px; width: 300px" />
					</div>
					<div class="col-auto">
						<frm:errors path="address" cssClass="form-text text-danger"></frm:errors>
					</div>
				</div>

				<button type="submit" class="btn btn-primary px-2 my-2">Create
					Employee</button>
			</frm:form>
		</div>
	</div>
</div>