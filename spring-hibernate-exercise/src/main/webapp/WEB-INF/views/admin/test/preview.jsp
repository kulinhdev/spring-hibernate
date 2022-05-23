<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<core:url value="/" var="rootpath" />

<div class="container-fluid">
	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Test Preview</h1>
		<a href="${rootpath}admin/tests"
			class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
			class="fas fa-home fa-sm text-white-50"></i> List Tests</a>
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
					<h6 class="m-0 font-weight-bold text-primary">Test Preview:
						${test.title}</h6>
				</div>
				<div class="card-body">
					<core:forEach items="${test.listQuestions}" var="question"
						varStatus="loop">
						<div class="QA my-3">
							<h4>Q${loop.count} : ${question.content} ?  ${question.questionLevel.name} </h4>

							<div class="answer ml-3">
								<core:forEach items="${question.listAnswers}" var="answer"
									varStatus="loop">
									<p class="${answer.status ? 'text-success' : 'text-danger'}">${loop.count}
										. ${answer.content} <span> - </span> ${answer.status ? "Correct" : "Wrong"}
									</p>
								</core:forEach>
							</div>
						</div>
					</core:forEach>
				</div>
			</div>
		</div>
	</div>
</div>