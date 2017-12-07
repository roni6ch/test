<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="test.Item"%>
<%
	ArrayList<Item> listOfItems = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>TODO List - by Roni Chabra</title>
<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah"
	rel="stylesheet">

<link href="/test/css/bootstrap.min.css" rel="stylesheet">
<link href="/test/css/style.css" rel="stylesheet">

<script src="/test/js/jquery-3.2.1.min.js"></script>
<script src="/test/js/bootstrap.min.js"></script>
<script src="/test/js/site.js"></script>

</head>
<body>
	<div class="container text-center">
		<h1>TODO List</h1>

		<div class="btn btn-secondary userProfile" data-container="body"
			data-toggle="popover" data-placement="bottom"
			data-content="<a href='/test/index.jsp?disconnect=true'>LogOut</a>">
			<img src="<%=session.getAttribute("userimage")%>" />
		</div>
		<script>
			function signOut() {
				console.log("sign out");
				var auth2 = gapi.auth2.getAuthInstance();
				auth2.signOut().then(function() {
					console.log('User signed out.');
				});
			}
		</script>
		<!-- 	<a href="/test/index.jsp" class="logout" onclick="revokeAllScopes();"><span
			class="glyphicon glyphicon-log-out"> LogOut</span></a>
 -->
		<div class="row">
			<div class="col-middle text-center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Delete</th>
							<th class="noteTitle">Note</th>
							<th>Done</th>
						</tr>
					</thead>
					<tbody class="col-middle listOfItems">

						<%
							listOfItems = (ArrayList<Item>)session.getAttribute("listOfItems");
										if (listOfItems!= null){
											for(Item item : listOfItems){
						%>
						<tr id="<%=item.getId()%>">
							<td><a href="#" class="btn btn-info removeItem"> <span
									class="glyphicon glyphicon-remove"></span> Remove
							</a></td>
							<td><input type="text" value="<%=item.getNote()%>"
								class="note" /> <a href="#" class="btn updateItem"> <span
									class="glyphicon glyphicon-refresh"></span> Update
							</a></td>
							<td><label class="checkbox-inline"><input
									<%if (item.isDone() == true) out.print("checked");%>
									type="checkbox" class="doneItems"></label></td>
						</tr>
						<%
							}; }
						%>
					</tbody>
				</table>

			</div>
		</div>
		<div class="row">
			<div class="form-group col-middle" id="newNote">
				<textarea class="form-control" rows="5"></textarea>
				<button type="button" class="btn btn-primary addNewItem">Add
					new Note</button>
			</div>
		</div>
	</div>


</body>
</html>

<script>
	$(function() {
		$('[data-toggle="popover"]').popover({
			html : true
		});
		$(".userProfile").click(function() {
			//auth2.disconnect();
			event.preventDefault();
			//window.location.href = "/test/index.jsp";
		});
		$(".addNewItem")
				.click(
						function() {
							var note = $("#newNote textarea").val();
							$
									.post(
											"/test/servletController?action=addItem",
											{
												note : note,
												date : new Date()
											},
											function(result) {
												$(".listOfItems").html("");
												$newItem = "";
												if (result) {
													$
															.each(
																	result,
																	function(
																			key,
																			value) {
																		$checked = value["done"] == true ? "checked"
																				: "";
																		$newItem += '<tr id="'+value["id"]+'"><td><a href="#" class="btn btn-info removeItem"><span class="glyphicon glyphicon-remove"></span> Remove </a></td> <td><input type="text" value="'+value["note"]+'" class="note" /> <a href="#" class="btn updateItem"> <span class="glyphicon glyphicon-refresh"></span> Update </a></td> <td><label class="checkbox-inline"><input '+$checked+' type="checkbox" class="doneItems"></label></td> </tr>';

																	});
													$(".listOfItems").append(
															$newItem);

													$("#newNote textarea").val(
															"");
												} else {
													console
															.log("error - add to listOfItems");
												}
											});
						});
		$(document).on('click', '.removeItem', function() {
			$self = $(this);
			var id = $(this).parent().parent().attr("id");
			$.post("/test/servletController?action=removeItem", {
				id : id
			}, function(result) {
				if (result) {
					$self.parent().parent().remove();
				}
			});
		});
		$(document).on('click', '.doneItems', function() {
			var id = $(this).parent().parent().parent().attr("id");
			var checked = $(this).is(":checked");
			$.post("/test/servletController?action=doneItem", {
				id : id,
				checked : checked
			}, function(result) {
				if (result != null) {
					$(this).attr("checked", result);
				}
			});
		});
		$(document).on('click', '.note', function() {
			$(this).parent().find(".updateItem").show();
		});

		$(document).on('click', '.updateItem', function() {
			$thisBT = $(this);
			var id = $(this).parent().parent().attr("id");
			var updatedNote = $(this).parent().parent().find(".note").val();
			$.post("/test/servletController?action=updateItem", {
				id : id,
				updatedNote : updatedNote
			}, function(result) {
				if (result != null) {
					$thisBT.hide();
				}
			});
		});
	});
</script>