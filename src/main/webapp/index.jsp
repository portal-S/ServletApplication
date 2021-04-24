<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>FileUploadDemo</title>
</head>
<body>
<h1>File Upload Demo</h1>
<h3>Please, select file to upload</h3> <br/>
<form action="files" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="100"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>
</body>
</html>