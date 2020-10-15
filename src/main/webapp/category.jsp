<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });
    function pageInit(){
        jQuery('#category-tt').jqGrid(
            {
                url : '${path}/category/showCategory',
                styleUI:"Bootstrap",
                datatype : "json",
                colNames : [ '分类ID', '分类名称', '级别' ],
                colModel : [
                    {name : 'id',  width : 55,align : "center"},
                    {name : 'name',editable:true,width : 90,align : "center"},
                    {name : 'level',width : 100,align : "center"},
                ],
                rowNum : 2,
                rowList : [2, 4, 6],
                pager : '#category-pager',
                viewrecords : true,
                multiselect :true, //选择框
                subGrid : true,
                page: 1,
                height: '300px',
                width: '1000px',

                autowidth: true,
                editurl: '${path}/category/edit',//编辑表单提交路径
                //rownumbers: true,//序列
                page: 1,
                surl:'',
                caption : "Grid as Subgrid",
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
                            url : '${path}/category/showByIdCategory?id='+row_id,
                            styleUI:"Bootstrap",
                            datatype : "json",
                            colNames : [ '分类ID', '分类名称', '级别'  ],
                            colModel : [
                                {name : 'id',  width : 55,align : "center"},
                                {name : 'name',editable:true,width : 90,align : "center"},
                                {name : 'level',width : 100,align : "center"},
                            ],
                            pager : pager_id,
                            height: '200px',
                            width: '1000px',
                            rowNum : 2,
                            rowList : [ 2, 4, 6,  ],
                            viewrecords : true,
                            autowidth: true,
                            multiselect :true, //选择框
                            editurl: '${path}/category/edit?pId='+row_id,//编辑表单提交路径

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
                subGridRowColapsed : function(subgrid_id, row_id) {
                    // this function is called before removing the data
                   var subgrid_table_id;
                    subgrid_table_id = subgrid_id+"_t";
                    jQuery("#"+subgrid_table_id).remove();
                }
            });
        jQuery("#category-tt").navGrid( '#category-pager', {
            add : true,
            edit : true,
            del : true,
            edittext: "修改",
            addtext: "添加",
            deltext: "删除"
        });
    }
</script>
<style type="text/css">
    *{
        text-align: center;
    }
</style>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>分类信息</h2>
    </div>
    <table id="category-tt"></table>
    <div id="category-pager"></div>
</div>


