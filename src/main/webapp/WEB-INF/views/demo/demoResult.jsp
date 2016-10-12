<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Result</title>
</head>
<body>
id: ${requestDemo.id}<br/>
Field1: ${requestDemo.field1}<br/>
Field2: ${requestDemo.field2}<br/>
Field3: <spring:eval expression="requestDemo.field3" /><br/>
Field4: <spring:eval expression="requestDemo.field4" /><br/>
Msg: ${msg}
</body>
</html>