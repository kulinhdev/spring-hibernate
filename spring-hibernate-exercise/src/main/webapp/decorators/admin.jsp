<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>E-Admin</title>

<!-- Custom fonts for this template-->

<link
	href="<core:url value='/public/templates/admin/vendor/fontawesome-free/css/all.min.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<core:url value='/public/templates/admin/css/sb-admin-2.min.css' />"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="<core:url value='/public/templates/admin/vendor/datatables/dataTables.bootstrap4.min.css' />"
	rel="stylesheet">

<style>
.cancle {
	cursor: pointer;
	padding: 5px 10px;
}

.list-group-item {
	padding: 0.75rem 1rem;
}

.list-group-item+.list-group-item {
	border-top-width: 1px;
}

.fas {
	width: 20px;
}
</style>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- SideBar -->
		<jsp:include page="/layouts/admin/sidebar.jsp" />
		<!-- End of SideBar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- TopBar -->
				<jsp:include page="/layouts/admin/navbar.jsp" />
				<!-- End of TopBar -->

				<!-- Begin Page Content -->
				<dec:body></dec:body>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="/layouts/admin/footer.jsp" />
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="<core:url value='/public/templates/admin/vendor/jquery/jquery.min.js' /> "></script>
	<script
		src="<core:url value='/public/templates/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/> "></script>

	<script
		src="<core:url value='/public/templates/admin/vendor/jquery-easing/jquery.easing.min.js' /> "></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<core:url value='/public/templates/admin/js/sb-admin-2.min.js'/>"></script>

	<!-- Datatable plugins -->
	<script
		src="<core:url value='/public/templates/admin/vendor/datatables/jquery.dataTables.min.js'/> "></script>
	<script
		src="<core:url value='/public/templates/admin/vendor/datatables/dataTables.bootstrap4.min.js'/> "></script>

	<script
		src="<core:url value='/public/templates/admin/js/datatables.js'/> "></script>

	<script type="text/javascript">
		var cloneElement = $("#item-clone");

		$("#add-new-answer").click(

		function() {
			// Clone new answer
			$("#answer_container").append(`<li class="list-group-item d-flex align-items-center"
					id="item-clone"><div class="cancle mr-3" onclick="remove(this)">
					<i class="fas fa-times"></i>
				</div>
				<div class="form-group mb-0">
					<input type="text" id="answer" name="answer_content"
						class="form-control" placeholder="Answer ..">
				</div>
				<div class="ml-auto">
					<input type="radio" name="answer_status">
				</div></li>`);


			$(function() {
				$.each($('input[name="answer_status"]'), function(index) {
					$(this).val(index);
				});
			});
		}
		
		);


		// Click remove self element
		function remove(el) {
			var element = el;
			element.parentElement.remove();
		}

		$(function() {
			$.each($('input[name="answer_status"]'), function(index) {
				$(this).val(index);
			});
		});
	</script>
</body>

</html>