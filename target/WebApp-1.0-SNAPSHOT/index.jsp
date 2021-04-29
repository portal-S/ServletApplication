<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>hello, servlets!</h1>
<br/>
<br/>
<form method="post" action="/files" enctype="multipart/form-data">
    <input type="text" name="user-id">
    <input type="file" name="file">
    <button>Send</button>
</form>
<br/>
<br/>
<h1>Get file</h1>
<br/>
<form method="get" action="/files" enctype="multipart/form-data">
    <input type="text" name="user-id">
    <input type="text" name="file-id">
    <button>Send</button>
</form>
<br/>
<br/>
<h2>Create Account</h2>
<br/>
<br/>
<form method="post" action="/accounts" enctype="multipart/form-data">
    <input type="text" name="name">
    <input type="password" name="pass">
    <button>Send</button>
</form>
<br/>
<br/>
<form method="get" action="/accounts" enctype="multipart/form-data">
    <input type="text" name="account-id">
    <button>Send</button>
</form>
</body>
</html>