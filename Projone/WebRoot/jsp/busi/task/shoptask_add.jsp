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
		window.parent.hangge();
		
		function setStatus(){
			
		}
	</script>
	</head> 
<body>
	<form  action="saler/addTask.do" name="shopItem" id="shoptaskForm" method="post" class="form-horizontal">
		<input type="hidden" name="is_init" id="t_is_init" value="0"/>
		<input type="hidden" name="is_intask" id="t_is_intask" value="0"/>
		<div id="zhongxin">
			<div class="row-fluid">
	  			<div class="span5">
					<div class="control-group">
						<label class="control-label" for="t_name">名称:</label>
						<div class="controls">
							<input type="text" name="name" id="t_name" placeholder="这里输入菜单名称" value="" title="名称"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">是否启用:</label>
						<div class="controls">
							<input type="text" name="status" id="t_status" placeholder="这里输入菜单名称" value="" title="名称"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">截止日期:</label>
						<div class="controls">
							<input type="text" name="effectivedate" id="t_effectivedate" placeholder="这里输入截止日期" value="" title="名称"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">链接:</label>
						<div class="controls">
							<input type="text" name="url" id="t_url" placeholder="这里输入链接地址" value="" title="名称"/>
						</div>
					</div>
					<div class="form-actions">
						<button class="btn btn-info" type="submit" onclick="save();"><i class="icon-ok"></i>保存</button>
						<button class="btn btn-info" type="submit" onclick="top.Dialog.close();"><i class="icon-ok"></i>取消</button>
					</div>
				</div>
			</div>
		</div>
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><img src="images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
	</form>
</body>
</html>