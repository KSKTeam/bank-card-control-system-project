<%@page import="java.util.Properties"%>
<%@page import="java.io.InputStream"%>
<html>
<body>
    <h2>Banking Card Control Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="https://github.com/KSKTeam/bank-card-control-system-project">GitHub repository</a>
    for more information on this project!
    <% 
    	InputStream is = getServletContext().getResource("WEB-INF/classes/version/version.properties").openStream();
    	Properties gitProp = new Properties();
   		gitProp.load(is);
    	String gitVersion = gitProp.getProperty("git.version");
    %>
    <p>Project version: <%= gitVersion %></p>
    
</body>
</html>
