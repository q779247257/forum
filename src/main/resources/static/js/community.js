

/* 回复按钮 点击触发事件 一级评论 */
function post() {
    //获取文章id
    var questionId = $("#question_id").val();
    //获取评论内容
    var content = $("#comment_content").val();
    if (!content) {
        alert("不能回复空内容");
        return;
    }
    comment2target(questionId,1,content);
}

/* 二级评论按钮 点击改变颜色 */
function change_view_color(e) {
    var id = e.getAttribute("data-id");
    //获取元素
    var $1 = $("#"+id);
    //获取子评论是否展开
    var isOpen = e.getAttribute("aria-expanded");
    console.log(isOpen)
    if (isOpen == "true"){
        //恢复颜色
        $1.css("color","");
        //获取清空的元素
        var commentBody = $("#id" + id);
        //删除选定元素中的  div标签
        commentBody.find("div").remove();
    } else {
        //改变颜色 评论展开
        $1.css("color","#499ef3");
        //请求获取二级评论
        $.getJSON("/comment/comment/"+id , function (data) {
            console.log(data);
            //获取需要追加的元素
            var commentBody = $("#comment-hr-id" + id);
            //遍历二级评论列表
            $.each(data.data, function(index,value) {
                console.log("二级评论列表循环开始");
                console.log(value);
                console.log("追加的元素id为:");
                console.log(commentBody.attr("id"));
                //在此元素之前追加元素
                commentBody.before("\n" +
                    "                             <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\" >\n" +
                    "                                <div class=\"media-left media-middle\">\n" +
                    "                                    <a href=\"#\">\n" +
                    "                                        <img class=\"media-object img-rounded\" style=\"width: 40px\"\n" +
                    "                                             src=\" " + value.user.avatarUrl +" \" alt=\"...\"/>\n" +
                    "                                    </a>\n" +
                    "                                </div>\n" +
                    "\n" +
                    "                                <div class=\"media-body\" style=\"margin: 10px 0px; padding: 10px\">\n" +
                    "                                    <h4 class=\"media-heading\" >\n" +
                    "                                        <span class=\"comment_name\" >" + value.user.name + "</span>\n" +
                    "                                    </h4>\n" +
                    "                                    <span class=\"comment_name\" > " +value.user.bio+ "</span>\n" +
                    "                                    <br>\n" +
                    "                                </div>\n" +
                    "                                <!-- 评论内容 -->\n" +
                    "                                <div class=\"comment_value\" style=\"color: #333333\"> "+ value.content + "</div>\n" +
                    "                                <div class=\"comment_list_value\" >\n" +
                    "                                    <span class=\"pull-right\"\n" +
                    "                                          >"+timestampToTime(value.gmtCreate)+"</span>\n" +
                    "                                </div>\n" +
                    "                            " +
                    "</div>");
            });
        });
    }
}


/**
 * 插入评论
 * @param targetId 目标id (父id)
 * @param type 评论类型
 * @param comment 评论内容
 */
function comment2target(targetId , type , comment) {
    if (!comment){
        alert("评论内容不能为空");
        return;
    }
    console.log('文章id：'+targetId);
    console.log('评论内容：'+comment);
    $.ajax({
        contentType:'application/json',
        url: "/comment/increase",
        data: JSON.stringify({
            "parentId": targetId,
            "content": comment,
            "type": type
        }),
        type: "POST",
        dataType: "json",
        success: function(response) {
            if (response.code == 200){
                //刷新页面
                window.location.reload();
            }else {
                //2002 没有登录
                if (response.code == 2002){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=0a3644bfe6f3a24b63fe&redirect_uri=http://localhost:8081/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }
            }
            console.log(response);
        }
    });

}

/**
 * 提交子评论
 */
function comment(e) {

    var isLogin = $("#login-"+commentId);
    if(isLogin == null || isLogin == "undefined"){
        alert("请登录之后再评论")
        return;
    }
    //获取id
    var commentId = e.getAttribute("data-id");
    console.log(commentId);
    //获取二级评论的内容
    var commentValue = $("#input"+commentId).val();
    console.log(commentValue)
    comment2target(commentId,2,commentValue);
}

/**
 * 标签点击事件
 */
function selectTag(value) {
    //获取原来的值
    var previous = $("#tag").val();

    //判断标签是否已经点击,标签是否存在
    if (previous.indexOf(value) != -1){

    }else {
        //如果存在则追加内容
        if (previous) {
            $("#tag").val(previous+','+value);
        }else {
            //如果不存在则直接追加value
            $("#tag").val(value);
        }
    }
}

/*
* display: block;
    display: none;
* */
/**
 * 标签 input 框框获取焦点 修改 标签栏的css
 */
function showSelectTag() {
    //标签展示
    $("#select-tag").css("display","block");
}


/**
 * 时间戳转日期
 * @param timestamp
 * @returns {*}
 */
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
    s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
    return Y+M+D+h+m+s;
}