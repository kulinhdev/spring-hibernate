<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">List Subjects</h1>
		<a href="${rootpath}admin/subject-create"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-plus  fa-sm text-white-50"></i> Add New Subject</a>
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

			<div class="data-table my-4">
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">DataTables
							Subjects</h6>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered text-left" id="dataTable"
								width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>No</th>
										<th>Name</th>
										<th>Class Name</th>
										<th>Status</th>
										<th>Description</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<core:forEach items="${listSubjects}" var="subject"
										varStatus="loop">
										<tr>
											<th scope="row">${loop.count}</th>
											<td>${subject.name}</td>
											<td>${subject.subjetClass.name}</td>
											<td>${subject.status ? "Active" : "Disable"}</td>
											<td>${fn:substring(subject.description, 0, 40)}...</td>
											<td><div class="d-flex justify-content-center">
													<a href="${rootpath}admin/subject-edit/${subject.id}"
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
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">Do you want to delete this
																	record with id: ${subject.id} ?</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Close</button>
																	<a href="${rootpath}admin/subject-delete/${subject.id}"
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
		</div>
	</div>
</div>