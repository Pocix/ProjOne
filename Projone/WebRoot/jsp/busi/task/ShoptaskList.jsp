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
	<!-- jsp文件头和头部 -->
	<%@ include file="/jsp/top.jsp"%> 
	<script type="text/javascript">
		$(function(){
			window.parent.hangge();
			alert(1);
			var myChart = echarts.init(document.getElementById('main'));
			option = {
				    title : {
				        text: '未来一周气温变化',
				        subtext: '纯属虚构'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['最高气温','最低气温']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} °C'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'最高气温',
				            type:'line',
				            data:[11, 11, 15, 13, 12, 13, 10],
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },
				        {
				            name:'最低气温',
				            type:'line',
				            data:[1, -2, 2, 5, 3, 2, 0],
				            markPoint : {
				                data : [
				                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name : '平均值'}
				                ]
				            }
				        }
				    ]
				};
			myChart.setOption(option);
		});
		
	</script> 
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
</div><!--/row-->

<!--row2-->
<div class="row-fluid">
	<div class="row-fluid">
		<div id="main" style="height:400px;"></div>
	</div>
</div>

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




