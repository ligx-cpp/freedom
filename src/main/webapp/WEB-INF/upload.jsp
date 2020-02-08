<%--
  Created by IntelliJ IDEA.
  User: 李光新
  Date: 2020/2/3
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=UTF-8">
    <title>myUploader</title>
    <link rel="stylesheet" type="text/css" href="/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <script src="/js/jquery.js"></script>
    <script src="/js/webuploader.js"></script>
    <script src="/js/upload.js"></script>
    <script type="text/javascript"></script>
</head>
<body >
<div id="wrapper">
    <div id="container">
        <!--头部，相册选择和格式选择-->
        <div id="uploader">
            <div class="queueList">
                <div id="dndArea" class="placeholder">
                    <div id="filePicker"></div>
                </div>
            </div>
            <div class="statusBar" style="display:none;">
                <div class="progress">
                    <span class="text">0%</span>
                    <span class="percentage"></span>
                </div><div class="info"></div>
                <div class="btns">
                    <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
