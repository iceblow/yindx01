/**
 * Created by paul on 2017/4/9.
 */
    //图片上传
var upToken = '', key = '';
$(function() {
    var uploader = Qiniu.uploader({
        runtimes: 'html5,flash,html4',      // 上传模式，依次退化
        browse_button: 'pickfiles',         // 上传选择的点选按钮，必需
        uptoken_url: createUrl('/file/getUploadTokenWeb', {}, false),
        get_new_uptoken: false,             // 设置上传文件的时候是否每次都重新获取新的uptoken
        domain: 'uncle',     // bucket域名，下载资源时用到，必需
        container: 'container',             // 上传区域DOM ID，默认是browser_button的父元素
        max_file_size: '100mb',             // 最大文件体积限制
        flash_swf_url: '../plupload/Moxie.swf',  //引入flash，相对路径
        max_retries: 3,                     // 上传失败最大重试次数
        dragdrop: true,                     // 开启可拖曳上传
        drop_element: 'container',          // 拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
        chunk_size: '4mb',                  // 分块上传时，每块的体积
        auto_start: true,                   // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
        init: {
            'FilesAdded': function(up, files) {},
            'BeforeUpload': function(up, file) {},
            'UploadProgress': function(up, file) {},
            'FileUploaded': function(up, file, info) {
                var domain = up.getOption('domain');
                var res = JSON.parse(info);
                // var sourceLink = domain +"/"+ res.key; //获取上传成功后的文件的Url
//
//                    $("#pickfiles").data('key', res.key);
//                    $("#avatar").attr('src', qiniuUrl + res.key);
                $.ajax({
                    url: createUrl('/file/uploadFilePath', {key: res.key, fileType:0}, false), type: 'get'
                }).done(function (response) {
                    console.log(5555)
                    var url=qiniuUrl+res.key;
                    service.imgUrl.push(url);
                    var id=JSON.parse(response.r);
                    service.imgId.push(id.fileid);
                    service.imgList.push({
                        id:id.fileid,
                        url:url
                    });
                });
            },
            'Error': function(up, err, errTip) {
                //上传出错时，处理相关的事情
            },
            'Key': function(up, file) {
                var date = new Date();

                var month = date.getMonth() + 1;
                if(month >= 1 && month <= 9) {
                    month ="0" + month;
                }
                var day = date.getDate();
                if(day >= 1 && day <= 9) {
                    day ="0" + day;
                }

                return date.getFullYear() + '' + month + day + date.getHours() + date.getMinutes() + date.getMilliseconds() + '_' + getRandom(100)
            }
        }
    });

    function getRandom(n){
        return Math.floor(Math.random()*n+1)
    }
});
//关闭照片预览
function closeImg() {
    $('body .img_see_box').remove();

}
//删除照片
function delImg(id) {
    console.log(id)
    for (var i in service.imgList){
        if(service.imgList[i].id == id){
            service.imgList.splice(i,1)
        }
    }
    $('body .img_see_box').remove();
}