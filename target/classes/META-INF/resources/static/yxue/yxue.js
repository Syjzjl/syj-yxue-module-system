$(function () {
    /**
     *
     * @param obj 要控制的标签对象
     * @param wait  等待的时间
     */
    function time(obj,wait) {
        let $this = $(obj);

        if (wait==0) {
            // 如果等待时间wait为0的时候，设置按钮可以触发
            $this.css('pointer-events','');
            $this.html('发送验证码');
            wait = 60;
        } else {
            // 如果等待时间wait大于0,设置按钮禁用（不可以触发事件）
            $this.css('pointer-events','none');
            $this.html(wait+'秒后可以重新发送');
            // 手动减秒
            wait--;
            setTimeout(function () {
                // 递归调用time函数
                time(obj, wait);
            }, 1000);
        }


    }

    // 点击发送验证码
    $('#sendPhoneCode').click(function () {
        // 控制发送验证码的周期
        time(this, 60);

        // 获取手机号 发送到后台
        alert('是否发送验证码？');
        var  phone=$('#phone').val();
        $.ajax({
            url:'${path}/admin/sendPhoneCode',
            data:"phone="+phone,
            type:"post",
            success:function (re) {

            }
        });
    })
});