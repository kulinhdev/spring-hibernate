<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<core:url value="/admin/question-update" var="update" />
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Edit Question</h1>
		<a href="${rootpath}admin/questions"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-home fa-sm text-white-50"></i> List Questions</a>
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
					<div class="row">
						<div class="col text-left">
							<h6 class="m-0 font-weight-bold text-warning">Edit Question</h6>
						</div>

						<div class="col text-right">
							<h6 class="m-0 font-weight-bold text-success">Correct Answer</h6>
						</div>
					</div>
				</div>
				<div class="card-body">
					<frm:form action="${update}" modelAttribute="question"
						id="question-form">

						<frm:input type="hidden" class="form-control" path="id" />

						<div class="row">
							<div class="col-md-6">
								<!-- Employee name -->
								<div class="row g-3 mt-3 align-items-center">
									<div class="col-md-2">
										<label for="name" class="form-label">Title: </label>
									</div>
									<div class="col-auto">
										<frm:input type="content" class="form-control" id="content"
											path="content" placeholder="What your name ?" />
									</div>
									<div class="col-auto">
										<frm:errors path="content" cssClass="form-text text-danger"></frm:errors>
									</div>
								</div>

								<!-- Levels -->
								<div class="row g-3 mt-4 align-items-center">
									<div class="col-md-2">
										<label for="role-id" class="col-form-label">Level: </label>
									</div>
									<div class="col-auto">
										<frm:select class="form-control form-select" id="role-id"
											path="questionLevel.id">
											<core:forEach items="${levels}" var="level">
												<frm:option value="${level.id}">${level.name}</frm:option>
											</core:forEach>
										</frm:select>
									</div>
									<div class="col-auto">
										<frm:errors path="questionLevel"
											cssClass="form-text text-danger"></frm:errors>
									</div>
								</div>

								<!-- Subjects -->
								<div class="row g-3 mt-4 align-items-center">
									<div class="col-md-2">
										<label for="role-id" class="col-form-label">Subject: </label>
									</div>
									<div class="col-auto">
										<frm:select class="form-control form-select" id="role-id"
											path="questionSubject.id">
											<core:forEach items="${subjects}" var="subject">
												<frm:option value="${subject.id}">${subject.name}</frm:option>
											</core:forEach>
										</frm:select>
									</div>
									<div class="col-auto">
										<frm:errors path="questionSubject"
											cssClass="form-text text-danger"></frm:errors>
									</div>
								</div>

								<!-- Status -->
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
							</div>
							<div class="col-md-6">
								<div id="answerWrapper" class="mb-4">
									<ul class="list-group" id="answer_container">
										<core:forEach items="${question.getListAnswers()}"
											var="answer">
											<li class="list-group-item d-flex align-items-center"
												id="item-clone"><div class="cancle mr-3">
													<i class="fas fa-times"></i>
												</div>
												<div class="form-group mb-0">
													<input type="text" id="answer" name="answer_content"
														class="form-control" value="${answer.content}"
														placeholder="Answer ..">
												</div>
												<div class="ml-auto">
													<core:set var="answerStatus" value="${answer.status}" />
													<span>${answerStatus ? "Correct" : "Incorrect"} -</span> <input
														type="radio" name="answer_status"
														checked="${answerStatus}">
												</div></li>
										</core:forEach>
									</ul>
								</div>

								<div class="add-new-answer">
									<div class="form-group mb-0">
										<button type="button" class="btn btn-success"
											id="add-new-answer">New Answer</button>
									</div>
								</div>
							</div>

							<button type="submit" class="btn btn-warning ml-2 my-4">Update
								Question</button>
						</div>
					</frm:form>
				</div>
			</div>
		</div>
	</div>
</div>