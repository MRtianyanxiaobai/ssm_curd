<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mr_xiaobai
  Date: 2021/8/3
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <%--    引入 jquer--%>
    <%-- web路径问题
     不以 / 开始的相对路径，找资源，当前资源路径为基准。经常出问题
     以 / 开始的相对路径找资源，以服务器的路径为标准： http://localhost:3306/crud: 因此需要加上项目名

    --%>
    <%
        //设置一个全局变量，即项目名
        pageContext.setAttribute("APP_PATH", request.getContextPath());

    %>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <%--    引入boostrap--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>

<!--创建显示页面-->
<div class="container">
    <%-- 标题--%>
    <div class="row">
        <%--大屏幕，该行总共占了12列    --%>
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%-- 按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="button" class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button type="button" class="btn btn-danger" id="emp_delete_all_btn">删除</button>
        </div>
    </div>
    <%-- 表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="check_all"/>
                        </th>
                        <th>#</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <%--       用于存取主体内容         --%>
                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%-- 分页文字信息--%>
        <div class="col-md-6" id="page_info_area"> </div>

        <%-- 分页条信息 ,分页条的模板可在官方文档中的组件中找到--%>
        <div class="col-md-6" id="page_nav_area">
        </div>
    </div>
</div>
<script type="text/javascript">
    var totalRecord,currentPage;
    //1、页面加载完成以后，直接发送 ajax 请求，要到分页数据
    $(function (){
        to_page(1);
    })
    function  to_page(pn){
        $.ajax({
            url:"${APP_PATH}/emps",
            data:"pn="+pn,
            type:"GET",
            success: function (result){
                //输出配置信息
                console.log(result);
                //1、解析并显示员工数据
                build_emps_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        })
        function  build_emps_table(res){
            //清空table
            $("#emps_table tbody").empty();
            var emps = res.extend.pageInfo.list;
            $.each(emps,function (index,item) {
                var charBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
                var empIdTd = $("<td></td>").append(item.stuId);
                var empstuName = $("<td></td>").append(item.stuName);
                var empgender = $("<td></td>").append(item.gender=='M'?'男':'女');
                var empemail= $("<td></td>").append(item.email);
                var deptNameTd = $("<td></td>").append(item.department.deptName);
                //添加编辑和删除按钮
                var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
                //为编辑按钮添加一个自定义的属性，来表示当前员工id
                editBtn.attr("edit-id",item.empId);
                var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
                //为删除按钮添加一个自定义的属性来表示当前删除的员工id
                delBtn.attr("del-id",item.empId);
                var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
                //利用 append 方法把上面的这些组件连起来
                $("<tr></t>").append(charBoxTd)
                    .append(empIdTd)
                    .append(empstuName)
                    .append(empgender)
                    .append(empemail)
                    .append(deptNameTd)
                    .append(btnTd)
                    .appendTo("#emps_table tbody");
            })
        }
        //解析显示分页信息
        function build_page_info(result){
            $("#page_info_area").empty();
            $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
                result.extend.pageInfo.pages+"页,总"+
                result.extend.pageInfo.total+"条记录");
            //定义这两个变量，作为全局变量
            totalRecord = result.extend.pageInfo.total;
            currentPage = result.extend.pageInfo.pageNum;
        }
        //解析显示分页条，点击分页要能去下一页....
        function build_page_nav(result){
            //page_nav_area
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");

            //构建元素
            var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
            if(result.extend.pageInfo.hasPreviousPage == false){
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            }else{
                //为元素添加点击翻页的事件
                firstPageLi.click(function(){
                    to_page(1);
                });
                prePageLi.click(function(){
                    to_page(result.extend.pageInfo.pageNum -1);
                });
            }



            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
            if(result.extend.pageInfo.hasNextPage == false){
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            }else{
                nextPageLi.click(function(){
                    to_page(result.extend.pageInfo.pageNum +1);
                });
                lastPageLi.click(function(){
                    to_page(result.extend.pageInfo.pages);
                });
            }

            //添加首页和前一页 的提示
            ul.append(firstPageLi).append(prePageLi);
            //1,2，3遍历给ul中添加页码提示
            $.each(result.extend.pageInfo.navigatepageNums,function(index,item){

                var numLi = $("<li></li>").append($("<a></a>").append(item));
                if(result.extend.pageInfo.pageNum == item){
                    numLi.addClass("active");
                }
                numLi.click(function(){
                    to_page(item);
                });
                ul.append(numLi);
            });
            //添加下一页和末页 的提示
            ul.append(nextPageLi).append(lastPageLi);

            //把ul加入到nav
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");
        }
    }
</script>
</body>
</html>
