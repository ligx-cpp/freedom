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
    <script src="/js/jquery.js"></script>
    <script src="/js/webuploader.js"></script>
    <script type="text/javascript"></script>
    <style type="text/css">
    </style>
</head>
<body >
<div id="uploader">
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="startBtn" class="btn btn-default">开始上传</button>
    </div>
</div>
</body>
<script type="text/javascript">
    var GUID = WebUploader.Base.guid();//一个GUID
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '/css/Uploader.swf',
        // 文件接收服务端。
        server: '/file/upload',
        formData:{
            guid : GUID
        },
        pick: '#picker',
        chunked : true, // 分片处理
        chunkSize : 20 * 1024 * 1024, // 每片1M,
        chunkRetry : false,// 如果失败，则不重试
        threads : 1,// 上传并发数。允许同时最大上传进程数。
        resize: false
    });
    $("#startBtn").click(function () {
        uploader.upload();
    });
    //当文件上传成功时触发。
    uploader.on( "uploadSuccess", function( file ) {
        $.post('/file/merge', { guid: GUID, fileName: file.name}, function (data) {
            if(data.code == 200){
                alert('上传成功!');
            }
        });
    });
</script>
</body>
</html>
