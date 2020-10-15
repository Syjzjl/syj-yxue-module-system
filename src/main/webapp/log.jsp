<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#log-tt').jqGrid({
            url: '${path}/log/showLog',
            datatype: 'json',

            colNames: ['日志编号', '操作的用户名','操作时间','操作的表名','操作的业务类型','操作的方法签名','操作数据的ID','恢复数据'],
            styleUI: 'Bootstrap',
            colModel: [
                {name : 'id',width : 55,editable: false,align : "center"},
                {name : 'username',editable:true,width : 80,align : "center"},
                {
                    name: 'operationAt', editable: true,align: "center",
                    label: "创建时间", editable: true,edittype: 'date', formatter: "date",
                    formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}
                },
                {name : 'tableName',editable:true,width : 80,align : "center"},
                {name : 'operationMethod',editable:true,width : 80,align : "center"},
                {name : 'methodName',editable:true,width : 80,align : "center"},
                {name : 'dataId',editable:true,width : 80,align : "center"},
                {name : 'dataInfo',editable:true,width : 80,align : "center"},

            ],
            autowidth: true,
            mtype: 'get',
            pager: '#log-pager',
            rowNum : 3,
            rowList: [3,6,9,12],
            viewrecords: true,
            editurl: '${path}/log/edit',//编辑表单提交路径
            multiselect: true,
            height: '300px',
            width: '500px',
            rownumbers: true,
            page: 1,
            surl:'',
            subgrid:true,
        }).navGrid( '#log-pager', {
                edit: false,
                add: false,
                del: true,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {//添加之后的额外操作

            },  //添加之后的额外操作
            {}   //删除之后的额外操作
        );
    });

</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>
    <table id="log-tt"></table>
    <div id="log-pager"></div>

</div>


