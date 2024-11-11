<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="head.jsp"/>
    <style>
        form {max-width: 400px;margin-left: auto; margin-right: auto;}
    </style>
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
    <h1 class="text-center">Login</h1>
    <div class="row">
        <div class="col-12">
            <form action="<%=request.getContextPath()%>/login" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" name="email" id="email" ariadescribedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" id="password"
                           placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>