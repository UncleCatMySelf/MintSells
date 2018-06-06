<html>
    <#include "../common/header.ftl">
    <body>

        <div id="wrapper" class="toggled">

            <#--边栏-->
            <#include "../common/nav.ftl">

            <#--主要内容-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>类目ID</th>
                                    <th>名字</th>
                                    <th>TYPE</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list categoryList as category>
                                <tr>
                                    <td>${category.categoryId}</td>
                                    <td>${category.categoryName}</td>
                                    <td>${category.categoryType}</td>
                                    <td>${category.createTime}</td>
                                    <td>${category.updateTime}</td>
                                    <td>
                                        <a href="/sb/seller/category/index?categoryId=${category.categoryId}">修改</a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<#--<#list orderDTOPage.content as orderDTO>-->

<#--</#list>-->