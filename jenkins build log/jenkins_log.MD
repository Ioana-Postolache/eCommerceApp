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
Checking out Revision 797bd3d3615130fffe9f435fbc17300ea40bd843 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 797bd3d3615130fffe9f435fbc17300ea40bd843 # timeout=10
Commit message: "Added jenkins build log"
 > git rev-list --no-walk df881e7b4ff43e52973695415539385f327152f7 # timeout=10
Parsing POMs
Modules changed, recalculating dependency graph
Established TCP socket on 45707
[jenkins_to_tomcat_separate_hosts] $ /opt/java/openjdk/bin/java -cp /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-agent-1.13.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/boot/plexus-classworlds-2.6.0.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/conf/logging jenkins.maven3.agent.Maven35Main /var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3 /var/jenkins_home/war/WEB-INF/lib/remoting-4.10.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-interceptor-1.13.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven3-interceptor-commons-1.13.jar 45707
<===[JENKINS REMOTING CAPACITY]===>channel started
Executing Maven:  -B -f /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/pom.xml clean install package
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.example:eCommerceApp >----------------------
[INFO] Building eCommerceApp 0.0.1-SNAPSHOT
[INFO] --------------------------------[ war ]---------------------------------
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-starter-log4j2/2.1.5.RELEASE/spring-boot-starter-log4j2-2.1.5.RELEASE.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-starter-log4j2/2.1.5.RELEASE/spring-boot-starter-log4j2-2.1.5.RELEASE.pom (2.7 kB at 5.2 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-slf4j-impl/2.11.2/log4j-slf4j-impl-2.11.2.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-slf4j-impl/2.11.2/log4j-slf4j-impl-2.11.2.pom (10 kB at 363 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.11.2/log4j-core-2.11.2.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.11.2/log4j-core-2.11.2.pom (23 kB at 724 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jul/2.11.2/log4j-jul-2.11.2.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jul/2.11.2/log4j-jul-2.11.2.pom (7.7 kB at 511 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-starter-log4j2/2.1.5.RELEASE/spring-boot-starter-log4j2-2.1.5.RELEASE.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.11.2/log4j-core-2.11.2.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-slf4j-impl/2.11.2/log4j-slf4j-impl-2.11.2.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jul/2.11.2/log4j-jul-2.11.2.jar
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-starter-log4j2/2.1.5.RELEASE/spring-boot-starter-log4j2-2.1.5.RELEASE.jar (407 B at 9.0 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-jul/2.11.2/log4j-jul-2.11.2.jar (24 kB at 179 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-slf4j-impl/2.11.2/log4j-slf4j-impl-2.11.2.jar (23 kB at 153 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.11.2/log4j-core-2.11.2.jar (1.6 MB at 3.5 MB/s)
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ eCommerceApp ---
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ eCommerceApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 4 resources
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
13:59:05.111 [main] ERROR com.example.demo.controllers.CartController - error with adding to cart. User with user name test not found.
13:59:05.226 [main] ERROR com.example.demo.controllers.CartController - error with adding to cart. Item with id 0 not found.
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.525 sec - in com.example.demo.controllers.CartControllerTest
Running com.example.demo.controllers.ItemControllerTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 sec - in com.example.demo.controllers.ItemControllerTest
Running com.example.demo.controllers.UserControllerTest
13:59:05.383 [main] ERROR com.example.demo.controllers.UserController - error with user password. Cannot create user test
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.082 sec - in com.example.demo.controllers.UserControllerTest
Running com.example.demo.controllers.OrderControllerTest
13:59:05.514 [main] ERROR com.example.demo.controllers.OrderController - error with getting order history. User with user name test not found.
13:59:05.526 [main] ERROR com.example.demo.controllers.OrderController - error with submitting order. User with user name test not found.
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.13 sec - in com.example.demo.controllers.OrderControllerTest

Results :

Tests run: 20, Failures: 0, Errors: 0, Skipped: 0

[JENKINS] Recording test results
[INFO] 
[INFO] --- maven-war-plugin:3.2.2:war (default-war) @ eCommerceApp ---
[INFO] Packaging webapp
[INFO] Assembling webapp [eCommerceApp] in [/var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Webapp assembled in [233 msecs]
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
[INFO] Copying 4 resources
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
[INFO] Webapp assembled in [415 msecs]
[INFO] Building war: /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.1.5.RELEASE:repackage (repackage) @ eCommerceApp ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  28.119 s
[INFO] Finished at: 2021-11-03T13:59:19Z
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
Deleted: sha256:708709bc131a3adccce43f31283406b498818c8206304a65116fe111f7e3d2d2
Deleted: sha256:30965255c319fc5244ae505730b2b592774488eaa4bac6005170745f65949e41
Deleted: sha256:42cd014ecc4ae191d1f379fd8d7bdcd9775147bb8567df06eeb098fd010e3a19
Sending build context to Docker daemon  39.84MB

Step 1/3 : From tomcat:8-jre8
 ---> cff25871f024
Step 2/3 : MAINTAINER "Udacity"
 ---> Running in 124f0c1e30ef
Removing intermediate container 124f0c1e30ef
 ---> aa46ae9c8e9c
Step 3/3 : COPY ./*.war /usr/local/tomcat/webapps
 ---> 11315df8931b
Successfully built 11315df8931b
Successfully tagged demo_image:latest
SSH: EXEC: completed after 1,802 ms
SSH: Opening exec channel ...
SSH: EXEC: channel open
SSH: EXEC: STDOUT/STDERR from command [docker run -d --name demo_container -p 8888:8080 demo_image] ...
SSH: EXEC: connected
fbfd0522661865227aedca9085207288f68915cf0e429e9708e60a74fd67bea8
SSH: EXEC: completed after 601 ms
SSH: Disconnecting configuration [Tomcat] ...
SSH: Transferred 1 ( 1 + 0 ) file(s)
Finished: SUCCESS
```

