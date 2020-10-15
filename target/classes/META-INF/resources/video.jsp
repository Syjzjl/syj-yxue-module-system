<%@ page language="java" import="java.util.*,com.baizhi.entity.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        // 初始化视频数据表格
        $('#video-tt').jqGrid({
            url: '${path}/video/showVideo',
            datatype: 'json',
            colNames: ['id', '标题','简介','封面','视频','发布时间','发布者','类别','分组名称','点赞数','播放量'],
            styleUI: 'Bootstrap',
            colModel: [
                {'name': 'id', editable: false,search: false,align : "center"},
                {'name': 'title', editable: true,align : "center"},
                {'name': 'intro', editable: true,search: false,align : "center"},
                {'name': 'coverUrl',edittype: "file",
                    editable: false,
                    search: false,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/videos/" + cellvalue + "' width='64px' height='64px'>"
                    }
                },
                {'name': 'videoUrl',edittype: "file",
                    editable: true,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    search: false,
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<video controls='controls' <source src='http://qhazgz70l.hn-bkt.clouddn.com/" + cellvalue + "' width='100px' height='80px'>"
                    }   //http://qhazgz70l.hn-bkt.clouddn.com/
                },
                {'name': 'createAt', align: "center",search: false,
                    label: "创建时间", editable: false,edittype: 'date', formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                },
                {'name': 'fuser.username', editable: true,search: false,
                    align : "center",
                    edittype:"select",
                    editoptions:{
                        dataUrl:'${path}/video/cate?sty=user'
                    }

                },
                {'name': 'fcate.name', editable: true,search: false,
                    align : "center",
                    edittype:"select",
                    editoptions:{
                        dataUrl:'${path}/video/cate?sty=category'
                    }
                },
                {'name': 'fgroup.name', editable: false,search: false,align : "center"},
                {'name': 'likeNum', editable: false,search: false,align : "center"},
                {'name': 'fplay.playNum',search: false, editable: false,align : "center"},
            ],
            autowidth: true,
            mtype: 'get',
            pager: '#video-pager',
            rowNum:2,
            rowList: [2, 5, 10,15],
            viewrecords: true,
            editurl: '${path}/video/edit',
            cellurl: '',
            multiselect: true,
            height: '300px',
            width: '500px',
            rownumbers: true,
            surl:'${path}/video/showVideo',
        }).navGrid( '#video-pager', {
                edit: true,
                add: true,
                del: true,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {//添加之后的额外操作
                // 添加的同时提交额外参数
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.数据入库    文件数据不对   文件没有上传
                    //2.文件异步上传   添加成功之后  上传
                    //3.修改文件路径   （id,要的的字段内容）
                    //alert(data.responseJSON.message);
                    alert(123456);
                    let id = data.responseJSON.message;
                    $.ajaxFileUpload({
                        url: "${path}/video/videoUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "videoUrl", //文件选择框的id属性，即<input type="file">的id
                        success: function () {
                            //刷新页面
                            $("#video-tt").trigger("reloadGrid");
                        }
                    });
                    return ["ok"];
                }
            },  //添加之后的额外操作
            {}   //删除之后的额外操作
        );

    });

</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>

    视频
    <table id="video-tt"></table>
    <div id="video-pager"></div>

</div>
