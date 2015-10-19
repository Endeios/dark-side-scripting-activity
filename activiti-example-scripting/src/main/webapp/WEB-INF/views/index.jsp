<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<thead>
		<tr>
			<td>name</td>
			<td>active</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${docs}" var="doc">
			<tr>
				<td>${doc.name}</td>
				<td>${doc.active}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot></tfoot>
</table>
<form action="<c:url value="/documents"/>" method="POST">
	<input type="text" name="text"> <input type="submit" value="Test">
</form>