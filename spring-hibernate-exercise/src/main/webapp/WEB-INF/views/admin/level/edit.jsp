<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<core:url value="/admin/level-update" var="update" />
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Question Levels Manager</h1>
	</div>

	<!-- Content Row -->
	<div class="row">
		<div class="col-md-7">
			<div class="data-table">
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">DataTables
							Question Levels</h6>
					</div>
					<div class="card-body text-center">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="10px">No</th>
									<th>Level Name</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<core:forEach items="${levels}" var="level" varStatus="loop">
									<tr>
										<th scope="row">${loop.count}</th>
										<td>${level.name}</td>
										<td>${level.status ? "Active" : "Disable"}</td>
										<td><div class="d-flex justify-content-center">
												<a href="${rootpath}admin/level-edit/${level.id}"
													class="btn btn-warning mr-3">Edit</a>

												<!-- Button Modal -->
												<button type="button" class="btn btn-danger"
													data-toggle="modal"
													data-target="#showModelDelete-${loop.count}">
													Delete</button>

												<!-- Modal -->
												<div class="modal fade" id="showModelDelete-${loop.count}"
													tabindex="-1" role="dialog"
													aria-labelledby="showModelDelete" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title text-danger"
																	id="exampleModalLabel">Are you sure this action?</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">Do you want to delete this
																record with id: ${level.id} ?</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
																<a href="${rootpath}admin/level-delete/${level.id}"
																	class="btn btn-danger">Delete</a>
															</div>
														</div>
													</div>
												</div>
											</div></td>
									</tr>
								</core:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-5">
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
				<div class="card-header bg-warning py-3">
					<h6 class="m-0 font-weight-bold text-primary">Edit Question
						Level</h6>
				</div>
				<div class="card-body">
					<frm:form action="${update}" modelAttribute="level">

						<frm:input type="hidden" class="form-control" path="id" />

						<!-- Employee name -->
						<div class="row g-3">
							<div class="col-md-2 mt-2">
								<label for="name" class="form-label">Name: </label>
							</div>
							<div class="col-auto">
								<frm:input type="text" class="form-control" id="name"
									path="name" placeholder="High, Medium ..." />

								<div class="my-2">
									<frm:errors path="name" cssClass="form-text text-danger"></frm:errors>
								</div>
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

								<div class="my-2">
									<frm:errors path="status" cssClass="form-text text-danger"></frm:errors>
								</div>
							</div>
						</div>

						<button type="submit" class="btn btn-warning px-2 mt-3">Update
							Level</button>
					</frm:form>
				</div>
			</div>
		</div>
	</div>
</div>