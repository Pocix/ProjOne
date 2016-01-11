<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<script type="text/javascript">
			$(window.parent.hangge());	
		</script> 
	<!-- jsp文件头和头部 -->
	<%@ include file="/jsp/top.jsp"%> 
	
	</head> 
 
<body> 
<div class="container-fluid" id="main-container">

	<div id="breadcrumbs">
	
	<ul class="breadcrumb">
		<li><i class="icon-home"></i><a>系统管理</a><span class="divider"><i class="icon-angle-right"></i></span></li>
		<li class="active">帐号调度任务</li>
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
					<th>状态</th>
					<th>开始日期</th>
					<th>截止日期</th>
					<th>同步</th>
					<th>是否已初始化</th>
					<th>URL</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pro" items="${shopList}">
					<tr>
						<td>${pro.uid }</td>
						<td>${pro.name }</td>
						<td>
							<c:choose>
								<c:when test="${pro.status=='1' }">
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${pro.uid}" checked="checked" onclick="changeStatus('${pro.uid}','0','status')" /><span class="lbl"></span></label>
								</c:when>
								<c:otherwise>
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${pro.uid}" onclick="changeStatus('${pro.uid}','1','status')" /><span class="lbl"></span></label>
								</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate  value="${pro.createtime }" type="date" /></td>
						<td><fmt:formatDate  value="${pro.effectivedate }" type="date" /></td>
						<td><c:choose>
								<c:when test="${pro.is_intask=='1' }">
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${pro.uid}" checked="checked" onclick="changeStatus('${pro.uid}','0','intask')" /><span class="lbl"></span></label>
								</c:when>
								<c:otherwise>
									<label><input type="checkbox" class="ace-switch ace-switch-3" id="qx1${pro.uid}" onclick="changeStatus('${pro.uid}','1','intask')" /><span class="lbl"></span></label>
								</c:otherwise>
							</c:choose></td>
						<td>
						<c:choose>
								<c:when test="${pro.is_init=='1' }">
									<label><span class="icon-ok"></span></label>
								</c:when>
								<c:otherwise>
									<label><span class="icon-bolt"></span></label>
								</c:otherwise>
							</c:choose></td>
						<td>${pro.url }</td>
					</tr>
				</c:forEach>
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
	function changeStatus(uid, cmd, type) {
		showWaitMsg();
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			cache : false,
			url : "${basePath}saler/updStatus.do",
			data : {
				uid : uid,
				cmd : cmd,
				type : type
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




