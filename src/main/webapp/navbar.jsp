<jsp:useBean id="sessionBean"
             class="edu.jhu.ep.su16.final_project.mmoore91.beans.SessionBean"
             scope="session" >
</jsp:useBean>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath()%>">Canyon Trails</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <% if (sessionBean.isAuthenticated() == true) { %>

            <li><a href="<%=request.getContextPath()%>/mytrails">My Trails</a></li>
            <li><a href="<%=request.getContextPath()%>/trails/add">Add Trail</a></li>

                <% } %>

            </ul>
            <% if (sessionBean.isAuthenticated() == false) { %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span class="caret"></span></a>
                    <ul id="login-dp" class="dropdown-menu">
                        <li>
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form" role="form" method="post" action="<%=request.getContextPath()%>/login" accept-charset="UTF-8"
                                          id="login-nav">
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                            <input type="email" class="form-control" id="exampleInputEmail2"
                                                   placeholder="Email address" name="email" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="exampleInputPassword2">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword2"
                                                   placeholder="Password" name="password" required>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                                        </div>

                                    </form>
                                </div>
                                <div class="bottom text-center">
                                    No account? <a href="<%=request.getContextPath()%>/register.jsp"><b>Register</b></a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
            <% } else { %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                       data-toggle="dropdown"><b><%= sessionBean.getAuthenticatedUserEmailAddress() %>
                    </b> <span class="caret"></span></a>
                    <ul id="" class="dropdown-menu">

                        <li><a href="<%=request.getContextPath()%>/account.jsp">My Account</a></li>

                        <li>
                            <a href="<%=request.getContextPath()%>/logout"><b>Logout</b></a>
                        </li>
                    </ul>
                </li>
            </ul>
            <% } %>
        </div><!--/.nav-collapse -->
    </div>
</nav>