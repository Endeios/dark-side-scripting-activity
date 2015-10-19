<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul style="list-style:none;line-height:28px;">
    <li>
	    <spring:url value="/deploy-bar" var="deployBar" htmlEscape="true" />
        <a href="${homeUrl}">Deploy Bar</a>
    </li>
 
    <li>
	    <spring:url value="/new-process" var="newProcess" htmlEscape="true" />
        <a href="${newProcess}">New Process</a>
    </li>
 
</ul>