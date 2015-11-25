<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include.jsp"%>
<html lang="en">
	<head>
		<title></title>
<script type="text/javascript">

	$(window.parent.hangge());

	$("#sss").click(function(){
		alert(11);
		  $.get("http://hq.sinajs.cn/list=sh601006", function(result){
			  alert(result);
		  });

			 $.get("http://hq.sinajs.cn/list=shsh601006", function(data){
			       //data就是b.html的内容，想加在哪就加在哪
				alert(data);
			});
		});
	//sh601006
	function findName(){
		//if($("#code").val()!=""){
			$.getJSON("http://hq.sinajs.cn/list=sh601006",function(data){
				alert(data);
			});

			
		//}
	}
	
	//保存
	function save(){
		findName();
		return;
		if($("#buyDate").val()==""){
			$("#buyDate").tips({
				side:3,
	            msg:'选择买入时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#buyDate").focus();
			return false;
		}
		if($("#code").val()==""){
			
			$("#code").tips({
				side:3,
	            msg:'输入股票代码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#code").focus();
			return false;
		}
		
		if($("#price").val()==""){
			
			$("#price").tips({
				side:3,
	            msg:'输入买入价',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#price").focus();
			return false;
		}	
		
		if($("#amount").val()==""){
			
			$("#amount").tips({
				side:3,
	            msg:'输入手数',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#amount").focus();
			return false;
		}		
		$("#logForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="gp/${msg }.do" name="logForm" id="logForm" method="post">
		<div id="zhongxin">
		<table class="center" style="width: 100%">
			<tr>
				<td  style="width:50%; height: 100%"><input value="${pd.BUYDATE}" type="text" name="buyDate" class="span10 date-picker" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100%;" id="buyDate" placeholder="这里输入买入日期" title="买入日期"></input></td>
				<td  style="width:50%; height: 100%"><input value="${pd.SALEDATE }" type="text" name="saleDate" class="span10 date-picker" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:100%;" id="saleDate" placeholder="这里输入卖出日期" title="卖出日期"></input></td>
			</tr>
			
			<tr>
				<td  style="width:50%; height: 100%"><input value="${pd.CODE }" type="text" name="code" id="code" style="width:100%; height: 100%" placeholder="这里输入代码" title="代码"></input></td>
				<td  style="width:50%; height: 100%"><input value="${pd.NAME }" type="text" style="width:100%; height: 100%" name="name" id="name" onfocus="findName();" placeholder="这里输入名称" title="名称"></input></td>
			</tr>
			
			<tr>
				<td  style="width:50% height: 100%"><input value="${pd.PRICE }" type="text" style="width:100%; height: 100%" name="price" id="price" placeholder="这里输入买入单价" title="买入单价"></input></td>
				<td  style="width:50%; height: 100%"><input value="${pd.AMOUNT }" type="text" style="width:100%; height: 100%" name="amount" id="amount" placeholder="这里输入买入手数" title="买入手数"></input></td>
				
			</tr>
			<tr>
				<td  style="width:50%; height: 100%"><input value="${pd.SALES }" type="text" style="width:100%; height: 100%" name="sales" id="sales" placeholder="这里输入卖出单价" title="卖出单价"></input></td>
				<td  style="width:50%; height: 100%"><input value="${pd.DP }" type="text" style="width:100%; height: 100%" name="dp" id="dp" placeholder="这里输入大盘涨跌百分比" title="大盘涨跌百分比"></input></td>
			</tr>
			<tr>
				<td  style="width:100%; height: 100%" colspan="2">
				<textarea value="${pd.REMARK }" style="width:100%; height: 50px"  name="remark" id="remark" placeholder="这里输入备注" title="备注"></textarea>
				</td>
			</tr>
			<tr>
				<td  colspan="2">
					<a id="sss" name="sss" class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
	
</body>
</html>