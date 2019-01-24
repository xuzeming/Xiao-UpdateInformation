$(function () {
    /*当改变的时候进行视频上传*/
    $("#fileInput").change(function () {
        var objUrl = getObjectURL(this.files[0]);
        $(".videoPath").attr("src", objUrl);
        $("#videoContent").css("visibility","visible");
        getTime();
    });
});
<!--获取mp3文件的时间 兼容浏览器-->
function getTime() {
    setTimeout(function () {
        var duration = $(".audio")[0].duration;
        if(isNaN(duration)){
            getTime();
        }
        else{
            console.info("该歌曲的总时间为："+$(".audio")[0].duration+"秒")
        }
    }, 10);
}
<!--把文件转换成可读URL-->
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
