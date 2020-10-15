<%@page contentType="text/html;charset=utf8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#user-tt').jqGrid({
            url: '${path}/user/showUser',
            datatype: 'json',
            colNames: ['用户名','签名','手机号','头像','状态','注册时间','分数'],
            styleUI: 'Bootstrap',

            colModel: [
                {name: 'username', editable: true,searchoptions:{ sopt:['eq','cn'] }},
                {name: 'sign', editable: true,search:false},
                { name:'mobile',editable: true,searchoptions: {sopt: ['eq','cn']} },
                {
                    name: 'headShow',
                    edittype: "file",
                    search: false,
                    editable: true,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/img/" +cellvalue + "' height='50' width='50'/>"
                    }
                },

                { name:'status',editable: true,
                    formatter:function (cellvalue, options, rowObject) {
                        //列的值  操作 行对象
                        if(cellvalue=='1'){
                            return "<a class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\")'>正常</a>";
                        }else{
                            return "<a class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\")' >冻结</a>";
                        }
                    },
                    edittype:"select",
                    editoptions:{value:"1:正常;0:冻结"},
                    searchoptions:{sopt: ["eq","cn"]}
                },
                { name:'regTime' ,editable: false,search:false,edittype: 'date',index:'regTime',
                    label:"创建时间", formatter:'date',
                    formatoptions: {srcformat:'Y-m-d',newformat:'Y-m-d'}
                },
                { name:'score' ,editable: true ,searchoptions:{sopt: ["eq","cn"]}},
            ],
            autowidth: true,//适应全屏
            afterSearch: function () {
                closeAfterAdd: true
            },
            mtype: 'get',//提交方式
            pager: '#user-pager',//指定分页的div
            rowList: [5, 10, 20],//分页下拉框指定的数量
            rowNum:"5",
            viewrecords: true,
            editurl: '${path}/user/edit',
            cellurl: '',
            multiselect: true,
            height: '400px',
            rownumbers: true,
            page: 1,
            pgbuttons: true,
        }).jqGrid('navGrid', '#user-pager',{
                add: true,
                del: true,
                addtext:"添加",
                deltext:"删除",
                edittext:"修改"
            },
            {
                closeAfterEdit: true
            },  //修改之后的额外操作
            {
                // 添加的同时提交额外参数
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.数据入库    文件数据不对   文件没有上传
                    //2.文件异步上传   添加成功之后  上传
                    //3.修改文件路径   （id,要的的字段内容）
                    //id=  data.responseText
                    // alert(data.responseJSON.message);
                    let id = data.responseJSON.message;
                    $.ajaxFileUpload({
                        url: "${path}/user/headUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "headShow", //文件选择框的id属性，即<input type="file">的id
                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#user-tt").trigger("reloadGrid");
                        }
                    });
                    return ["ok"];
                }
            },  //添加之后的额外操作
            {}   //删除之后的额外操作
        );
    });


    function updateStatus(key) {
        $.ajax({
            "url" : "${path}/user/updateStatus",
            "type" : "post",
            "data" : "id="+key,
            "success" : function () {
                $("#user-tt").trigger("reloadGrid");
            }
        })
    }
</script>
<style type="text/css">
    *{
        text-align: center;
    }
</style>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
<%--    <form action="/user/import" method="post" enctype="multipart/form-data">--%>
<%--        <input type="file" name="multipartFile">--%>
<%--        <input type="submit" value="导入">--%>
<%--        <input type="submit" value="导出">--%>
<%--    </form>--%>
    <table id="user-tt"></table>
    <div id="user-pager"></div>

</div>


