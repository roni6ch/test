<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah"
	rel="stylesheet">



<meta name="google-signin-client_id"
	content="948056088352-oqnb644o2nacjbcigr3h54llrqo49uj5.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>



<title>TODO List - by Roni Chabra</title>


<link href="/test/css/bootstrap.min.css" rel="stylesheet">
<link href="/test/css/style.css" rel="stylesheet">

<script src="/test/js/jquery-3.2.1.min.js"></script>
<script src="/test/js/bootstrap.min.js"></script>
<script src="/test/js/site.js"></script>

</head>

<body>
	<div class="container">
		<div class="row">
			<form action="post" method="/test/servletController?action=login">
				<div class="loginBT g-signin2" data-onsuccess="onSignIn"
					data-theme="dark">Login Button</div>
				<input type="hidden" />
			</form>
		</div>
	</div>


	<script>
		function onSignIn(googleUser) {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.disconnect();

			var profile = googleUser.getBasicProfile();
			var redirectUrl = "/test/servletController?action=login";
			var form = $('<form action="' + redirectUrl + '" method="post">'
					+ '<input type="hidden" name="name" value="'
					+ profile.getId()
					+ '" /><input type="hidden" name="image" value="'
					+ profile.getImageUrl()
					+ '" /><input type="hidden" name="email" value="'
					+ profile.getEmail() + '" /></form>');
			$('body').append(form);
			form.submit();
		};
		function signOut() {
			console.log("sign out");
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
			});
		}
	</script>

</body>
</html>