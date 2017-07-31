<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Customer - List</title>

<!-- reference to our stylesheet -->
<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Management</h2>
		</div>
	</div>
	<div id="contianer">
		<div id="content">
		
			<!-- create a new button: Add Customer -->
			
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForCreate'; return false;"
				class="add-button"
			/>
		
			<!-- add out html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- Loop over and print out customers in table divisions -->
				
				<c:forEach var="tempCustomer" items="${customerList}">
				
				<!-- construct an update link with customer id  -->
				<c:url var="updateLink" value="/customers/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>
				
				<!-- construct an delete link with customer id  -->
				<c:url var="deleteLink" value="/customers/delete">
					<c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>
				
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<!-- display the delete link -->
							<a href="${deleteLink}"
									onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a> 
						</td>
					</tr>
				</c:forEach>
			</table>
			
			
		</div>
	</div>

</body>
</html>