<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="../resources/js/react/react.js"></script>
    <script src="../resources/js/react/react-dom.js"></script>
    <script src="../resources/js/bundle.js"></script>
<title>Demo Page</title>
<script type="text/javascript">
	function json() {
		document.frm.action="../RestDemo/2";
		document.frm.submit();
	}
	
	function jsp() {
		document.frm.action="../PageDemo/submit";
		document.frm.submit();
	}
</script>

</head>
<body>
	<div id="content"></div>
	This is Demo Page
	<form name="frm" action="../PageDemo/submit" method="post">
		<table>
			<tbody>
				<tr>
					<td>String:</td>
					<td><input name="field1" /></td>
				</tr>
				<tr>
					<td>String:</td>
					<td><input name="field2" /></td>
				</tr>
				<tr>
					<td>Date:</td>
					<td><input name="field3" /></td>
				</tr>
				<tr>
					<td>Currency:</td>
					<td><input name="field4" /></td>
				</tr>
			</tbody>
		</table>
		<input type="button" value="Submit(JSP)" onclick="jsp()"/>
		<input type="button" value="Submit(JSON)" onclick="json()"/>
	</form>
</body>
</html>