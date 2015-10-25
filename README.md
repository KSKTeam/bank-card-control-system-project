# bank-card-control-system-project

The project allows bank managers to control costs from corporate banking cards that spent by bank emplyees.

##Software requirements:

* jre1.7 or above
* Tomcat7
* Maven 2.5 or above

##Usage:

1) clone this repo
```
git clone https://github.com/KSKTeam/bank-card-control-system-project.git
```
2) add user with <manager-script> role to your `<TOMCAT_HOME>/conf/tomcat-users.xml` file

Example:

```xml
<tomcat-users>
..........
<role rolename="manager-script"/>
<user password="mvn_user" username="mvn_user" roles="manager-script"/>
..........
</tomcat-users>
```

3) add user from point 2) to your server(id needs to be "TomcatServer") in `<MAVEN_HOME>/conf/settings.xml` file

Example:

```xml
<servers>
..........
<server>
	<id>TomcatServer</id>
	<username>mvn_user</username>
	<password>mvn_user</password>
</server>
..........
</servers>
```

4) start up your Tomcat7 server

5) go to cloned repository
```
cd CardControlProject
```
6) enter command "mvn clean tomcat7:redeploy"

7) Done! You may access the application by openning the browser and going to http://localhost:8080/CorporateCardControlProject/
