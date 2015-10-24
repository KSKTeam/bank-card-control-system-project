<%@page import="org.ksk_team.projects.CorporateCardControlProject.service.versioning.GitVersionControl"%>
<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <p>Project version: <%= GitVersionControl.getInstance().getGitVersion()%></p>
    
</body>
</html>
