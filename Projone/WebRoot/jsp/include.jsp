<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>${pd.SYSNAME}</title>
<!-- basic styles -->
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path%>/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
<![endif]-->
<!-- page specific plugin styles -->

<!-- checkout-->
<link rel="stylesheet" href="<%=path%>/css/chosen.css" />

<!-- ace styles -->
<link rel="stylesheet" href="<%=path%>/css/ace-skins.min.css" />
<link rel="stylesheet" href="<%=path%>/css/ace-responsive.min.css" />
<link rel="stylesheet" href="<%=path%>/css/ace.min.css" />
<!--[if lt IE 9]>
  <link rel="stylesheet" href="css/ace-ie.min.css" />
<![endif]-->

<link rel="stylesheet" href="<%=path%>/css/datepicker.css" />

<!--Dialog start-->
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDialog.js"></script>
<!--Dialog end-->

<!-- <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>  -->
<script src="<%=path%>/js/jquery-1.7.2.js"></script>
<script src="<%=path%>/js/ace-elements.min.js"></script>

<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/ace.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.tips.js"></script>