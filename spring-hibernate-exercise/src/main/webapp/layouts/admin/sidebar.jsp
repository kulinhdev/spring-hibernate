<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<core:url value="/" var="rootpath" />

<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- SideBar -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${rootpath}admin">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">E-Admin</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link"
		href="${rootpath}admin"> <i class="fas fa-fw fa-tachometer-alt"></i>
			<span>Dashboard</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">System</div>

	<!-- Roles -->
	<li class="nav-item"><a class="nav-link"
		href="${rootpath}admin/roles"> <i class="fas fa-user-check"></i> <span>Employee
				Roles</span>
	</a></li>

	<!-- Classes -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#class" aria-expanded="true"
		aria-controls="class"> <i class="fas fa-school"></i> <span>Classes</span>
	</a>
		<div id="class" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Classes pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/classes">Classes</a>
				<a class="collapse-item" href="${rootpath}admin/class-create">Create
					Class</a>
			</div>
		</div></li>

	<!-- Employees -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#employee" aria-expanded="true"
		aria-controls="employee"> <i class="fas fa-users"></i> <span>Employees</span>
	</a>
		<div id="employee" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Employee pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/employees">Employees</a>
				<a class="collapse-item" href="${rootpath}admin/employee-create">Create
					Employee</a>
			</div>
		</div></li>


	<!-- Students -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#student" aria-expanded="true"
		aria-controls="employee"> <i class="fas fa-user-graduate"></i> <span>Students</span>
	</a>
		<div id="student" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Student pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/students">Students</a>
				<a class="collapse-item" href="${rootpath}admin/student-create">Create
					Student</a>
			</div>
		</div></li>




	<!-- Subjects -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#subject" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-book"></i> <span>Subjects</span>
	</a>
		<div id="subject" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Subjects pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/subjects">Subjects</a>
				<a class="collapse-item" href="${rootpath}admin/subject-create">Create
					Subject</a>
			</div>
		</div></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Exercises</div>

	<!-- Levels -->
	<li class="nav-item"><a class="nav-link"
		href="${rootpath}admin/question-levels"> <i
			class="fas fa-level-up-alt"></i> <span>Levels</span>
	</a></li>


	<!-- Question -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#question" aria-expanded="true"
		aria-controls="question"> <i class="fas fa-question"></i> <span>Questions</span>
	</a>
		<div id="question" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Tests pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/questions">Questions</a>
				<a class="collapse-item" href="${rootpath}admin/question-create">Create
					Question</a>
			</div>
		</div></li>


	<!-- Tests -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#test" aria-expanded="true"
		aria-controls="test"> <i class="fas fa-dumbbell"></i> <span>Tests</span>
	</a>
		<div id="test" class="collapse">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Tests pages:</h6>
				<a class="collapse-item" href="${rootpath}admin/tests">Tests</a> <a
					class="collapse-item" href="${rootpath}admin/test-create">Create
					Test</a>
			</div>
		</div></li>


	<!-- Nav Item - Charts -->
	<li class="nav-item"><a class="nav-link" href=""> <i
			class="fas fa-fw fa-chart-area"></i> <span>Scores</span></a></li>

	<!-- Feedbacks -->
	<li class="nav-item"><a class="nav-link" href=""> <i
			class="fas fa-comment-alt"></i> <span>Feedbacks</span></a></li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>
</ul>