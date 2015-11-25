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
		<li><i class="icon-home"></i> <a>业务管理</a><span class="divider"><i class="icon-angle-right"></i></span></li>
		<li class="active">产品管理</li>
	</ul><!--.breadcrumb-->
	
	</div><!--#breadcrumbs-->

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center"  style="width: 50px;">序号</th>
						<th class='center'>名称</th>
						<th class='center'>时间</th>
						<th class='center'>价格</th>
						<th class='center'>折后价</th>
						<th class='center'>人气</th>
						<th class='center'>评价</th>
						<th class='center'>交易</th>
						<th class='center'>近30天交易</th>
						<th class='center'>地址</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty productList}">
						<c:forEach items="${productList}" var="product" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${product.name }" id="${user.EMAIL }" alt="${user.PHONE }"/><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center'>${product.name }</td>
								<td class='center'>${product.createdate }</td>
								<td class='center'>${product.price}</td>
								<td class='center'>${product.tbprice }</td>
								<td class='center'>${product.trove }</td>
								<td class='center'>${product.evaluateCount }</td>
								<td class='center'>${product.tradeCount }</td>
								<td class='center'>${product.tradeCountFor30 }</td>
								<td class='center'>${product.url }</td>
							</tr>
						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
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
		
		<script type="text/javascript">
		
		$(window.parent.hangge());
		
		//检索
		function search(){
			window.parent.jzts();
			$("#userForm").submit();
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
