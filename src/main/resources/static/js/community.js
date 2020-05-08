

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
    var data = e.getAttribute("data-id");
    //获取元素
    var $1 = $("#"+data);
    //获取子评论是否展开
    var isOpen = e.getAttribute("aria-expanded");
    console.log(isOpen)
    if (isOpen == "true"){
        //恢复颜色
        $1.css("color","");
    } else {
        $.getJSON("/comment/comment/"+data , function (data) {
            console.log(data);
        })

        //改变颜色
        $1.css("color","#499ef3");
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
    //获取id
    var commentId = e.getAttribute("data-id");
    console.log(commentId);
    //获取二级评论的内容
    var commentValue = $("#input"+commentId).val();
    console.log(commentValue)
    comment2target(commentId,2,commentValue);
}