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
		
		<link rel="stylesheet" href="/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="/js/jquery.tips.js"></script>
</head>
 
<body> 
<div class="container-fluid" id="main-container">

	<div id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="icon-home"></i>
			<a>业务管理</a>
			<span class="divider">
				<i class="icon-angle-right"></i>
			</span>
		</li>
		<li class="active">日志管理</li>
	</ul><!--.breadcrumb-->
	
	</div><!--#breadcrumbs-->

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
		<!-- <table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center" >序号</th>
						<th class='center'>买入时间</th>
						<th class='center'>卖出时间</th>
						<th class='center'>代码</th>
						<th class='center'>名称</th>
						<th class='center'>买入</th>
						<th class='center'>手数</th>
						<th class='center'>卖出</th>
						<th class='center'>大盘</th>
						<th class='center'>备注</th>
						<th>操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty gpLogList}">
						<c:forEach items="${gpLogList}" var="obj" varStatus="vs">
							<tr>
								<td class='center' >${vs.index+1}</td>
								<td class='center' >${obj.buyDate }</td>
								<td class='center' >${obj.saleDate }</td>
								<td class='center' >${obj.code}</td>
								<td class='center' >${obj.name }</td>
								<td class='center' >${obj.price }</td>
								<td class='center' >${obj.amount }</td>
								<td class='center' >${obj.sales }</td>
								<td class='center' >${obj.dp }</td>
								<td class='center'>${obj.remark }</td>
								<td><a href="javascript:;" onclick="updateCron('${obj.id}')">更新</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据,是否增加数据?</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			-->
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr><a class="btn btn-small btn-success" onclick="add();">新增</a>
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
		
		<script type="text/javascript">
		
		$(window.parent.hangge());
		
		//检索
		function search(){
			window.parent.jzts();
			$("#userForm").submit();
		}
		function add(){
			alert(1);
			 $.post("http://localhost:8080",function(data){
					alert(2);
				});
			 $.post("http://hq.sinajs.cn/list=sh601006",{}, function(response){
				 alert(3);
				},'html');
		}
		//新增
		function add1(){
			 window.parent.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增会员";
			 diag.URL = 'gp/addLog.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 window.parent.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					var emstr = '';
					var phones = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  	
						  	if(emstr=='') emstr += document.getElementsByName('ids')[i].id;
						  	else emstr += ';' + document.getElementsByName('ids')[i].id;
						  	
						  	if(phones=='') phones += document.getElementsByName('ids')[i].alt;
						  	else phones += ';' + document.getElementsByName('ids')[i].alt;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							$.ajax({
								type: "POST",
								url: 'happuser/deleteAllU.do?tm='+new Date().getTime(),
						    	data: {USER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
						    		$.each(data.list, function(i, list){
										nextPage(${page.currentPage});
								 });
								}
							});
						}else if(msg == '确定要给选中的用户发送邮件吗?'){
							sendEmail(emstr);
						}else if(msg == '确定要给选中的用户发送短信吗?'){
							sendSms(phones);
						}
						
					}
				}
			});
		}

		function updateCron(jobId) {
			var cron = prompt("输入cron表达式！", "");
			if (cron) {
				showWaitMsg();

				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : "${basePath}busi/updateCron.htm",
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
		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			var STATUS = $("#STATUS").val();
			window.location.href='<%=basePath%>/happuser/excel.do?USERNAME='+USERNAME+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID+'&STATUS='+STATUS;
		}
		</script>
		
</body> 
</html>
