<html>
<body>
    <!--<h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/patients">Jersey resource</a>
    <p><a href="webapi/patients/1/measure/1">Patient Measure</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!-->


    <section class="container">
        <div class="login">
            <h1>Login to Beat</h1>
            <form method="get" action="beat/SignIn">
                <p><input type="text" name="userName" placeholder="Username "/></p>
                <p><input type="password" name="password" placeholder="Password"/></p>
                <p class="remember_me">
                    <label>
                        <input type="checkbox" name="remember_me" id="remember_me"/>
                        Remember me on this computer
                    </label>
                </p>
                <p class="submit"><input type="submit" name="submit" value="Login"/></p>
            </form>
        </div>

        <div class="login-help">
            <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
        </div>
        <div class="login-help">
            <p>New to Beat? <a href="index.jsp?iframeSrc=http://localhost:3000/users/sign_up">Register</a>.</p>
        </div>
    </section>

</body>
</html>
