<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include.jsp"%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script type="text/javascript">
			$(window.parent.hangge());	
		</script> 
</head>
 
<body> 
<div class="container-fluid" id="main-container">

	<div id="breadcrumbs">
	
	<ul class="breadcrumb">
		<li><i class="icon-home"></i><a>系统管理</a><span class="divider"><i class="icon-angle-right"></i></span></li>
		<li class="active">调度任务管理</li>
	</ul><!--.breadcrumb-->
	
	</div><!--#breadcrumbs-->

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<!-- 检索  -->
	<form id="addForm" method="post">

		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th style="width: 20xp">id</th>
					<th>name</th>
					<th>group</th>
					<th>状态</th>
					<th>cron表达式</th>
					<th>描述</th>
					<th>同步否</th>
					<th>类路径</th>
					<th>spring id</th>
					<th>方法名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="job" items="${taskList}">
					<tr>
						<td>${job.jobId }</td>
						<td>${job.jobName }</td>
						<td>${job.jobGroup }</td>
						<td>${job.jobStatus }
							<c:choose>
								<c:when test="${job.jobStatus=='1' }">
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${job.jobId}" checked="checked" onclick="changeJobStatus('${job.jobId}','stop')" /><span class="lbl"></span></label>
								</c:when>
								<c:otherwise>
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${job.jobId}" onclick="changeJobStatus('${job.jobId}','start')" /><span class="lbl"></span></label>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${job.cronExpression }</td>
						<td>${job.description }</td>
						<td>${job.isConcurrent }
							<c:choose>
								<c:when test="${job.isConcurrent=='1' }">
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${job.jobId}" checked="checked" onclick="changeJobStatus('${job.jobId}','stop')" /><span class="lbl"></span></label>
								</c:when>
								<c:otherwise>
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${job.jobId}" onclick="changeJobStatus('${job.jobId}','start')" /><span class="lbl"></span></label>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${job.beanClass }</td>
						<td>${job.springId }</td>
						<td>${job.methodName }</td>
						<td><a href="javascript:;" onclick="updateCron('${job.jobId}')">更新cron</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td>n</td>
					<td><input type="text" name="jobName" id="jobName"></input></td>
					<td><input type="text" name="jobGroup" id="jobGroup" style="width:30px;height:100%;text-align:center; padding-top: 0px;padding-bottom: 0px;"></input></td>
					<td>0<input type="hidden" name="jobStatus" value="0"></input></td>
					<td><input type="text" name="cronExpression" id="cronExpression"></input></td>
					<td><input type="text" name="description" id="description"></input></td>
					<td>0<input type="hidden" name="isConcurrent" value="0"></input></td>
					<td><input type="text" name="beanClass" id="beanClass"></input></td>
					<td><input type="text" name="springId" id="springId"></input></td>
					<td><input type="text" name="methodName" id="methodName" style="width:120px;height:100%;text-align:center; padding-top: 0px;padding-bottom: 0px;"></input></td>
					<td><input type="button" onclick="add()" value="保存" /></td>
				</tr>
			</tbody>
		</table>
	</form>
<div class="page-header position-relative">
	<table style="width:100%;">
		<tr>
		</tr>
	</table>
	</div>
</div>
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
<!-- 返回顶部  -->
<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
	<i class="icon-double-angle-up icon-only"></i>
</a>
<script>
$(window.parent.hangge());
	function validateAdd() {
		if ($.trim($('#jobName').val()) == '') {
			alert('name不能为空！');
			$('#jobName').focus();
			return false;
		}
		if ($.trim($('#jobGroup').val()) == '') {
			alert('group不能为空！');
			$('#jobGroup').focus();
			return false;
		}
		if ($.trim($('#cronExpression').val()) == '') {
			alert('cron表达式不能为空！');
			$('#cronExpression').focus();
			return false;
		}
		if ($.trim($('#beanClass').val()) == '' && $.trim($('#springId').val()) == '') {
			$('#beanClass').focus();
			alert('类路径和spring id至少填写一个');
			return false;
		}
		if ($.trim($('#methodName').val()) == '') {
			$('#methodName').focus();
			alert('方法名不能为空！');
			return false;
		}
		return true;
	}
	function add() {
		if (validateAdd()) {
			showWaitMsg();
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${basePath}task/add.do",
				data : $("#addForm").serialize(),
				success : function(data) {
					hideWaitMsg();
					if (data.flag) {

						location.reload();
					} else {
						alert(data.msg);
					}

				}//end-callback
			});//end-ajax
		}
	}
	function changeJobStatus(jobId, cmd) {
		showWaitMsg();
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			cache : false,
			url : "${basePath}task/changeJobStatus.htm",
			data : {
				jobId : jobId,
				cmd : cmd
			},
			success : function(data) {
				hideWaitMsg();
				if (data.flag) {

					location.reload();
				} else {
					alert(data.msg);
				}

			}//end-callback
		});//end-ajax
	}
	function updateCron(jobId) {
		var cron = prompt("输入cron表达式！", "")
		if (cron) {
			showWaitMsg();

			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${basePath}task/updateCron.htm",
				data : {
					jobId : jobId,
					cron : cron
				},
				success : function(data) {
					hideWaitMsg();
					if (data.flag) {

						location.reload();
					} else {
						alert(data.msg);
					}

				}//end-callback
			});//end-ajax
		}

	}
	function showWaitMsg() {
		window.parent.jzts();
	}
	function hideWaitMsg() {
		window.parent.hangge();
		$('.datagrid-mask').remove();
		$('.datagrid-mask-msg').remove();
	}
</script>
</body>
</html>




