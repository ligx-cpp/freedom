<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">+0
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<head>
    <link href="/css/video-js.css" rel="stylesheet">
    <style>
        .video-js{
            width:100%;
        }
    </style>
    <!-- If you'd like to support IE8 -->
    <script src="/js/videojs-ie8.min.js"></script>
</head>

<body>
<video id="my-video" class="video-js vjs-big-play-centered" controls preload="auto" width="640" height="320">
    <source id type='video/mp4'>
    <p class="vjs-no-js">
        To view this video please enable JavaScript, and consider upgrading to a web browser that
        <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
    </p>
</video>
<script src="/js/jquery.js"></script>
<script src="/js/video.js"></script>
<script>

    (function($){
        var list = [
            {name:"mov_bbb", url: "http://www.w3school.com.cn/example/html5/mov_bbb.mp4", lastTime:0},
            {name:"shichang", url: "http://www.w3school.com.cn/example/html5/mov_bbb.mp4", lastTime:0},
        ];

        var resetVideoSize = function(myPlayer){
            var videoJsBoxWidth = $(".video-js").width();
            var ratio = 1440/900;
            var videoJsBoxHeight = videoJsBoxWidth/ratio;
            myPlayer.height(videoJsBoxHeight);
        }
        var myPlayer = videojs("my-video").ready(function(){
            var i = 0;
            var videoObj = list[i];
            var lastTime = localStorage.getItem(videoObj.name + ".currentTime") || 0;
            this.currentTime(lastTime);

            this.width("100%");
            resetVideoSize(myPlayer);

            //绑定视频播放结束事件
            this.on("ended", function(){
                if(i >= list.length - 1){
                    i = 0;
                    this.pause();
                    return;
                }
                i++;
                this.src({type: "video/mp4", src: videoObj.url});
                localStorage.setItem(videoObj.name+".currentTime", 0);
                this.play();
            });

            //绑定视频播放中事件
            this.on("timeupdate", function(data){
                if(this.currentTime() - lastTime > 2){
                    lastTime = this.currentTime();
                    localStorage.setItem(videoObj.name+".currentTime", lastTime);
                }
            })
            this.play();
        });
        $(window).on("resize", function(){
            resetVideoSize(myPlayer);
        });

    })(jQuery)
</script>
</body>
</body>
</html>