<%@ include file="include.jsp" %>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark shadow mb-4">
    <a class="navbar-brand" href="#">LoginDemo</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <%
                    String contextPath = request.getContextPath();
                    String home = ("".equalsIgnoreCase(contextPath)) ? "/" : contextPath;
                %>
                <a class="nav-link" href="<%=home%>">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login">Login</a>
            </li>
        </ul>
    </div>
</nav>