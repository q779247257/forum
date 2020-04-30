

/* 回复按钮 点击触发事件 */
function post() {
    //获取文章id
    var questionId = $("#question_id").val();
    //获取评论内容
    var content = $("#comment_content").val();
    console.log('文章id：'+questionId);
    console.log('评论内容：'+content);

    $.ajax({
        contentType:'application/json',
        url: "/comment/increase",
        data: JSON.stringify({
                "parentId": questionId,
                "content": content,
                "type": 1
        }),
        type: "POST",
        dataType: "json",
        success: function(response) {
            if (response.code == 200){
                //请求成功 清空评论框框
                $("#comment_section").hide();
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