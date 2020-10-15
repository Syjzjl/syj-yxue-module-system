<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });
    function pageInit(){
        jQuery('#comment-tt').jqGrid(
            {
                url : '${path}/comment/showAllComment',
                styleUI:"Bootstrap",
                datatype : "json",
                colNames : [ '评论ID', '用户名称', '视频','评论内容','评论时间' ],
                colModel : [
                    {'name' : 'id',  width : 55,align : "center"},
                    {'name' : 'fuser.username',editable: true,search: false,
                        align : "center",
                        edittype:"select",
                        editoptions:{
                            dataUrl:'${path}/video/cate?sty=user'
                        }
                    },
                    {'name': 'fvideo.videoUrl',
                        editable: true,
                        index: 'name asc, invdate',
                        align: "center",
                        edittype:"select",
                        editoptions:{
                            dataUrl:'${path}/video/cate?sty=video'
                        },
                        //参数：各子的值,操作,行对象
                        formatter: function (cellvalue, options, rowObject) {
                            return "<video controls='controls' <source src='http://qhazgz70l.hn-bkt.clouddn.com/" + cellvalue + "' width='100px' height='80px'>"
                        }
                    },
                    {'name' : 'content',editable: true,width : 100,align : "center"},
                    {'name': 'createAt', align: "center",
                        label: "创建时间", editable: false,edittype: 'date', formatter: "date",
                        formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                    },
                ],
                rowNum : 2,
                rowList : [2, 4, 5, 10 ],
                pager : '#comment-pager',
                viewrecords : true,
                multiselect :true, //选择框
                subGrid : true,
                page: 1,
                height: '400px',
                width: '1000px',
                autowidth: true,
                editurl: '${path}/comment/edit',//编辑表单提交路径
                //rownumbers: true,//序列
                surl:'',
                //caption : "Grid as Subgrid",
                subGridRowExpanded : function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            url : '${path}/comment/showByIdComment?id='+row_id,
                            styleUI:"Bootstrap",
                            datatype : "json",
                            colNames : [ '评论ID', '用户名称', '视频','评论内容','评论时间'  ],
                            colModel : [
                                {'name' : 'id',  width : 55,align : "center"},
                                {'name' : 'fuser.username',editable: true,search: false,
                                    align : "center",
                                    edittype:"select",
                                    editoptions:{
                                        dataUrl:'${path}/video/cate?sty=user'
                                    }
                                },
                                {'name': 'fvideo.videoUrl',
                                    editable: true,
                                    index: 'name asc, invdate',
                                    align: "center",
                                    edittype:"select",
                                    editoptions:{
                                        dataUrl:'${path}/video/cate?sty=video'
                                    },
                                    //参数：各子的值,操作,行对象
                                    formatter: function (cellvalue, options, rowObject) {
                                        return "<video controls='controls' <source src='http://qhazgz70l.hn-bkt.clouddn.com/" + cellvalue + "' width='100px' height='80px'>"
                                    }
                                },
                                {'name' : 'content',editable: true,width : 100,align : "center"},
                                {'name': 'createAt', align: "center",
                                    label: "创建时间", edittype: 'date', formatter: "date",
                                    formatoptions: {srcformat: 'Y-m-d', newformat: 'Y-m-d'}
                                },
                            ],
                            pager : pager_id,
                            height: '200px',
                            width: '1000px',
                            rowNum : 2,
                            rowList : [ 2, 4, 5, 10 ],
                            viewrecords : true,
                            autowidth: true,
                            multiselect :true, //选择框
                            editurl: '${path}/comment/edit?interactId='+row_id,//编辑表单提交路径

                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : true,
                            add : true,
                            del : true,
                            edittext: "修改",
                            addtext: "添加",
                            deltext: "删除"
                        });
                },
            });
        jQuery("#comment-tt").navGrid( '#comment-pager', {
            add : true,
            edit : true,
            del : true,
            edittext: "修改",
            addtext: "添加",
            deltext: "删除"
        });
    }
</script>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>评论信息</h2>
    </div>
    <table id="comment-tt"></table>
    <div id="comment-pager"></div>
</div>


