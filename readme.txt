struts2分页 

http://blog.163.com/zhaohe162/blog/static/38216797201061104558321/
struts2实现分页显示  

2010-07-01 22:45:58|  分类： Struts2 |举报 |字号 订阅
在开发web程序时，从数据库中取出来的数据一般都要分页显示在页面上，分页显示的方法非常多，有人写自定义标签，用自己写的标签进行分页显示，我个人觉得这是一种比较好的方法。下面是我用Struts2做的一个分页显示实例，基本的思路是：把数据库表中的每一行数据封装成一个对象，用一个返回类型为List的方法返回这些对象，接着在Struts2的action里面定义一个List属性，用这个List来接收从数据库中查询出来并进行了封装的那些对象，然后通过Struts2的标签遍历List里的每个对象，并把这些对象里所包含的属性取出来展现在页面上。下面是具体的步骤：

所用工具：JDK1.6 + MyEclipse6.0 + Tomcat6.0 + MySQL5.0 + struts 2.0.11

1、新建一个web工程,导入struts2中相应的jar包和mysql数据驱动包。

2、编写web.xml文件，其代码如下：
 
 
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
 http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">
 <filter>
  <filter-name>struts2</filter-name>
  <filter-class>
   org.apache.struts2.dispatcher.FilterDispatcher
  </filter-class>
 </filter>
 <filter-mapping>
  <filter-name>struts2</filter-name>
  <url-pattern>/*</url-pattern>
 </filter-mapping>
 <welcome-file-list>
  <welcome-file>index.jsp</welcome-file>
 </welcome-file-list>
</web-app>
                 
3、建一个数据库表，用于保存学员信息：

drop database if exists page;
create database page ;
use page;
create table student (
   stu_id integer auto_increment,
   stuName varchar(255) not null,
   address varchar(255) not null,
   stuPhone varchar(255)not null,
   primary key(stu_id)
);
insert into student(stuName,address,stuPhone) values('杨华林','长沙','13787825190');
insert into student(stuName,address,stuPhone) values('李明清','天津','13787525190');
insert into student(stuName,address,stuPhone) values('李小华','大连','13788451190');
insert into student(stuName,address,stuPhone) values('郑小明','苏州','13787052188');
insert into student(stuName,address,stuPhone) values('杨一新','西安','13787851190');
insert into student(stuName,address,stuPhone) values('王新任','广州','13787056460');
insert into student(stuName,address,stuPhone) values('谢小华','深圳','13787075550');
insert into student(stuName,address,stuPhone) values('王建明','厦门','13788853690');
insert into student(stuName,address,stuPhone) values('秦一生','北京','13788689236');
insert into student(stuName,address,stuPhone) values('田翠林','杭州','13787655150');
insert into student(stuName,address,stuPhone) values('陈小明','广州','13787623668');
4、编写PageDAO类，该类用于从数据库中取出所有的学员信息，把这些学员信息封装成一个Student对象并将
   其放入List中。

package org.hnylj.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PageDAO {
 private Connection conn ;
 private PreparedStatement pstmt ;
 private ResultSet rs ;
 private static final String DRIVER = "com.mysql.jdbc.Driver" ;
 private static final String URL = "jdbc:mysql://localhost:3306/page" ;
 private static final String USERNAME = "root" ;
 private static final String PASSWORD = "123" ;
 
 private Student student ;
 
 //数据库连接
 public synchronized Connection getConnection () {
  try {
   Class.forName (DRIVER) ;
   conn = DriverManager.getConnection (URL,USERNAME,PASSWORD) ;
  } catch (ClassNotFoundException e) {
   e.printStackTrace () ;
   return null ;
  } catch (SQLException e) {
   e.printStackTrace () ;
   return null ;
  }
  return conn ;
 }
 
  //分页查询
  public List<Student> queryByPage (int pageSize, int pageNow) {
   List<Student> list = new ArrayList<Student> () ;
   try {
     if (this.getConnection()!=null && pageSize>0 && pageNow>0) {
      pstmt = this.getConnection().prepareStatement(
        "select * from student order by stu_id limit "+(pageNow*pageSize-pageSize)+","+pageSize
      );
      rs = pstmt.executeQuery () ;
     
      while (rs.next()) {
       student = new Student () ;
       student.setStu_id (rs.getInt(1)) ;
       student.setStuName (rs.getString(2)) ;
       student.setAddress (rs.getString(3)) ;
       student.setStuPhone (rs.getString(4)) ;
       list.add (student) ;
      }
     }
   } catch(SQLException e) {
    e.printStackTrace() ;
   }
   return list ;
  }
}
                 
5、由于要对数据库中的学员信息进行封装，所以需要编写一个JavaBean,即Student类：

package org.hnylj.util;
public class Student {
 private int stu_id ;
 private String stuName ;
 private String address ;
 private String stuPhone ;
 
 public Student () {
 }
 
 public int getStu_id () {
  return stu_id ;
 }
 
 public void setStu_id (int stu_id) {
  this.stu_id = stu_id ;
 }
 
 public String getStuName () {
  return stuName ;
 }
 
 public void setStuName (String stuName) {
  this.stuName = stuName ;
 }
 
 public String getAddress () {
  return address ;
 }
 
 public void setAddress (String address) {
  this.address = address ;
 }
 
 public String getStuPhone () {
  return stuPhone ;
 }
 
 public void setStuPhone (String stuPhone) {
  this.stuPhone = stuPhone ;
 }
}
至此底层的代码就写好了。接下来我们需要编写web层的代码。

6、编写web层的jsp文件，分别有index.jsp,show.jsp和error.jsp，其代码分别如下：

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <title>Struts2实现分页显示</title>
 </head>
 <body>
    <div align="center">
       <s:a href="show.action">进入查看学员列表</s:a>
    </div>
 </body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Struts2实现分页显示</title>
  </head>
  <body>
  <div align="center">
  <table border="1">
    <tr>
    <th>学号</th>
    <th>姓名</th>
    <th>地址</th>
    <th>电话</th>
    </tr>
 <s:iterator value="students">
    <tr>
       <td><s:property value="stu_id"/></td>
       <td><s:property value="stuName"/></td>
       <td><s:property value="address"/></td>
       <td><s:property value="stuPhone"/></td>
    </tr>
 </s:iterator>
  </table>
     <s:url id="url_pre" value="show.action">
         <s:param name="pageNow" value="pageNow-1"></s:param>
     </s:url>
     <s:url id="url_next" value="show.action">
         <s:param name="pageNow" value="pageNow+1"></s:param>
     </s:url> 
     <s:a href="%{url_pre}">上一页</s:a>
    
     <s:iterator value="students" status="status">
        <s:url id="url" value="show.action">
            <s:param name="pageNow" value="pageNow"/>
        </s:url>
     </s:iterator>
     <s:a href="%{url_next}">下一页</s:a>
  </div>
  </body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发生错误</title>
  </head>
 
  <body>
      业务逻辑发生异常,请稍候再试。。。。。。
  </body>
</html>
7、编写struts2的action来拦截用户的请求：

 

package org.hnylj.web;
import java.util.List;
import org.hnylj.util.PageDAO;
import org.hnylj.util.Student;
import com.opensymphony.xwork2.ActionSupport;
public class ShowAction extends ActionSupport {
 
 private List<Student> students ;
 private int pageNow = 1 ; //初始化为1,默认从第一页开始显示
    private int pageSize = 5 ; //每页显示5条记录
   
 private PageDAO pageDAO = new PageDAO () ;
 
 public List<Student> getStudents() {
  return students;
 }
 public void setStudents(List<Student> students) {
  this.students = students;
 }
 
 public int getPageNow() {
  return pageNow;
 }
 public void setPageNow(int pageNow) {
  this.pageNow = pageNow;
 }
 public int getPageSize() {
  return pageSize;
 }
 public void setPageSize(int pageSize) {
  this.pageSize = pageSize;
 }
 public String execute() throws Exception {
  students = pageDAO.queryByPage(pageSize, pageNow) ;
  return SUCCESS ;
 }
}
8、配置struts.xml文件，即action和响应结果页面之间的映射：

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
 <package name="struts2" namespace="/" extends="struts-default">
  <action name="show" class="org.hnylj.web.ShowAction">
   <result name="success">show.jsp</result>
   <result name="error">error.jsp</result>
  </action>
 </package>
</struts>
至此所有的工作已经完成，接下来部署整个web工程到tomcat的webapps目录下，启动tomcat，在浏览器地址栏输入http://localhost:8080/项目名称/index.jsp，回车即可看到一个链接，点击该链接即可看到一个可以进行上一页和下一页的学员信息列表。

