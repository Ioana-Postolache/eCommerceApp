### Jenkins Maven build success and project logs:
![Jenkins build success](../readme-images/Jenkins%20build%20success.png)
```
Started by user ioana
Running as SYSTEM
Building in workspace /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Deferred wipeout is used...
[WS-CLEANUP] Done
The recommended git tool is: NONE
using credential 579dbe45-83cf-491b-90db-fe34164881c1
Cloning the remote Git repository
Cloning repository git@github.com:Ioana-Postolache/eCommerceApp.git
 > git init /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts # timeout=10
Fetching upstream changes from git@github.com:Ioana-Postolache/eCommerceApp.git
 > git --version # timeout=10
 > git --version # 'git version 2.32.0'
using GIT_SSH to set credentials 
 > git fetch --tags --force --progress -- git@github.com:Ioana-Postolache/eCommerceApp.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url git@github.com:Ioana-Postolache/eCommerceApp.git # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision df881e7b4ff43e52973695415539385f327152f7 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f df881e7b4ff43e52973695415539385f327152f7 # timeout=10
Commit message: "Added tests"
 > git rev-list --no-walk 5b3e2ba12979d30c16280284792a7ef9bda8f689 # timeout=10
Parsing POMs
Modules changed, recalculating dependency graph
Established TCP socket on 34211
[jenkins_to_tomcat_separate_hosts] $ /opt/java/openjdk/bin/java -cp /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-agent-1.13.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/boot/plexus-classworlds-2.6.0.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/conf/logging jenkins.maven3.agent.Maven35Main /var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3 /var/jenkins_home/war/WEB-INF/lib/remoting-4.10.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-interceptor-1.13.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven3-interceptor-commons-1.13.jar 34211
<===[JENKINS REMOTING CAPACITY]===>channel started
Executing Maven:  -B -f /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/pom.xml clean install package
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.example:eCommerceApp >----------------------
[INFO] Building eCommerceApp 0.0.1-SNAPSHOT
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ eCommerceApp ---
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ eCommerceApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ eCommerceApp ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 20 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ eCommerceApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ eCommerceApp ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.19.1:test (default-test) @ eCommerceApp ---

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.example.demo.controllers.CartControllerTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.602 sec - in com.example.demo.controllers.CartControllerTest
Running com.example.demo.controllers.ItemControllerTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec - in com.example.demo.controllers.ItemControllerTest
Running com.example.demo.controllers.UserControllerTest
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/root/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/root/.m2/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
13:54:52.473 [main] INFO com.example.demo.controllers.UserController - Username set with test
13:54:52.478 [main] INFO com.example.demo.controllers.UserController - Username set with test
13:54:52.481 [main] ERROR com.example.demo.controllers.UserController - error with user password. Cannot create user test
13:54:52.484 [main] INFO com.example.demo.controllers.UserController - Username set with test
13:54:52.486 [main] INFO com.example.demo.controllers.UserController - Username set with test
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.307 sec - in com.example.demo.controllers.UserControllerTest
Running com.example.demo.controllers.OrderControllerTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.131 sec - in com.example.demo.controllers.OrderControllerTest

Results :

Tests run: 21, Failures: 0, Errors: 0, Skipped: 0

[JENKINS] Recording test results
[INFO] 
[INFO] --- maven-war-plugin:3.2.2:war (default-war) @ eCommerceApp ---
[INFO] Packaging webapp
[INFO] Assembling webapp [eCommerceApp] in [/var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Webapp assembled in [360 msecs]
[INFO] Building war: /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.1.5.RELEASE:repackage (repackage) @ eCommerceApp ---
[INFO] Replacing main artifact with repackaged archive
[INFO] 
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ eCommerceApp ---
[INFO] Installing /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war to /root/.m2/repository/com/example/eCommerceApp/0.0.1-SNAPSHOT/eCommerceApp-0.0.1-SNAPSHOT.war
[INFO] Installing /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/pom.xml to /root/.m2/repository/com/example/eCommerceApp/0.0.1-SNAPSHOT/eCommerceApp-0.0.1-SNAPSHOT.pom
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ eCommerceApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ eCommerceApp ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 20 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ eCommerceApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ eCommerceApp ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 6 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.19.1:test (default-test) @ eCommerceApp ---
[INFO] Skipping execution of surefire because it has already been run for this configuration
[JENKINS] Recording test results
[INFO] 
[INFO] --- maven-war-plugin:3.2.2:war (default-war) @ eCommerceApp ---
[INFO] Packaging webapp
[INFO] Assembling webapp [eCommerceApp] in [/var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Webapp assembled in [390 msecs]
[INFO] Building war: /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.1.5.RELEASE:repackage (repackage) @ eCommerceApp ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  27.084 s
[INFO] Finished at: 2021-11-03T13:55:07Z
[INFO] ------------------------------------------------------------------------
Waiting for Jenkins to finish collecting data
[JENKINS] Archiving /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/pom.xml to com.example/eCommerceApp/0.0.1-SNAPSHOT/eCommerceApp-0.0.1-SNAPSHOT.pom
[JENKINS] Archiving /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war to com.example/eCommerceApp/0.0.1-SNAPSHOT/eCommerceApp-0.0.1-SNAPSHOT.war
channel stopped
SSH: Connecting from host [a706ac60989d]
SSH: Connecting with configuration [Tomcat] ...
SSH: Creating session: username [host2admin], hostname [172.31.86.130], port [22]
SSH: Connecting session ...
SSH: Connected
SSH: Opening SFTP channel ...
SSH: SFTP channel open
SSH: Connecting SFTP channel ...
SSH: Connected
SSH: Remote root is not absolute, getting absolute directory from PWD
SSH: cd [/home/host2admin]
SSH: OK
SSH: cd [/opt/docker]
SSH: OK
SSH: put [eCommerceApp-0.0.1-SNAPSHOT.war]
SSH: OK
SSH: Opening exec channel ...
SSH: EXEC: channel open
SSH: EXEC: STDOUT/STDERR from command [docker stop demo_container;  
docker rm -f demo_container;
docker stop demo_image;  
docker rm -f demo_image; 
docker image rm -f demo_image; 
cd /opt/docker; 
docker build -t demo_image .] ...
SSH: EXEC: connected
demo_container
demo_container
Error response from daemon: No such container: demo_image
Error: No such container: demo_image
Untagged: demo_image:latest
Deleted: sha256:582b373af30ba3dd2b11b2c0bdef7dda7a733f4aa90eefadf563fe7a362b028e
Deleted: sha256:344f1b737b53c022c3cc5909bc749e285ebef1097642f19ec93d227ae248c89e
Deleted: sha256:8067ed5ae12040e4d0c93152287079379b72c40b97dbf7a7b8d4359cffe783dc
Sending build context to Docker daemon  39.45MB

Step 1/3 : From tomcat:8-jre8
 ---> cff25871f024
Step 2/3 : MAINTAINER "Udacity"
 ---> Running in c1cae14c7411
Removing intermediate container c1cae14c7411
 ---> 42cd014ecc4a
Step 3/3 : COPY ./*.war /usr/local/tomcat/webapps
 ---> 708709bc131a
Successfully built 708709bc131a
Successfully tagged demo_image:latest
SSH: EXEC: completed after 1,602 ms
SSH: Opening exec channel ...
SSH: EXEC: channel open
SSH: EXEC: STDOUT/STDERR from command [docker run -d --name demo_container -p 8888:8080 demo_image] ...
SSH: EXEC: connected
616b13cb5ebeccd2062ca31dfbb5336bcb59ed91a489117fdf2eb78880b88343
SSH: EXEC: completed after 1,001 ms
SSH: Disconnecting configuration [Tomcat] ...
SSH: Transferred 1 ( 1 + 0 ) file(s)
Finished: SUCCESS
```

