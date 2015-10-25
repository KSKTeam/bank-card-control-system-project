<%@page import="java.util.Properties"%>
<%@page import="java.io.InputStream"%>
<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <% 
    	InputStream is = getServletContext().getResource("WEB-INF/classes/version/version.properties").openStream();
    	Properties gitProp = new Properties();
   		gitProp.load(is);
    	String gitVersion = gitProp.getProperty("git.version");
    %>
    <p>Project version: <%= gitVersion %></p>
    
</body>
</html>
