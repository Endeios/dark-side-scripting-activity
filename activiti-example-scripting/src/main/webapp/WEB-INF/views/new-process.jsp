<%@page import="java.util.List"%>
<%@page import="org.activiti.engine.impl.form.EnumFormType"%>
<%@page import="org.activiti.engine.impl.form.BooleanFormType"%>
<%@page import="org.activiti.engine.form.TaskFormData"%>
<%@page import="org.activiti.engine.impl.form.StringFormType"%>
<%@page import="org.activiti.engine.form.FormProperty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="<c:url value="/documents"/>" method="POST">
	${startForm}
	<input type="submit" value="Submit">
</form>