# eCommerce Application

In this project, you'll have an opportunity to demonstrate the security and DevOps skills that you learned in this lesson by completing an eCommerce application. You'll start with a template for the complete application, and your goal will be to take this template and add proper authentication and authorization controls so users can only access their data, and that data can only be accessed in a secure way. 

## Project Template
First, you'll want to get set up with the template. The template is written in Java using Spring Boot, Hibernate ORM, and the H2 database. H2 is an in memory database, so if you need to retry something, every application startup is a fresh copy.

To use the template, import it in the IDE of your choice as a Spring Boot application. Where required, this readme assumes the eclipse IDE.

Once the project is set up, you will see 5 packages:

* demo - this package contains the main method which runs the application

* model.persistence - this package contains the data models that Hibernate persists to H2. There are 4 models: Cart, for holding a User's items; Item , for defining new items; User, to hold user account information; and UserOrder, to hold information about submitted orders. Looking back at the application “demo” class, you'll see the `@EntityScan` annotation, telling Spring that this package contains our data models

* model.persistence.repositories - these contain a `JpaRepository` interface for each of our models. This allows Hibernate to connect them with our database so we can access data in the code, as well as define certain convenience methods. Look through them and see the methods that have been declared. Looking at the application “demo” class, you’ll see the `@EnableJpaRepositories` annotation, telling Spring that this package contains our data repositories.

* model.requests - this package contains the request models. The request models will be transformed by Jackson from JSON to these models as requests are made. Note the `Json` annotations, telling Jackson to include and ignore certain fields of the requests. You can also see these annotations on the models themselves.

* controllers - these contain the api endpoints for our app, 1 per model. Note they all have the `@RestController` annotation to allow Spring to understand that they are a part of a REST API

In resources, you'll see the application configuration that sets up our database and Hibernate, It also contains a data.sql file with a couple of items to populate the database with. Spring will run this file every time the application starts

In eclipse, you can right click the project and click  “run as” and select Spring Boot application. The application should tell you it’s starting in the console view. Once started, using a REST client, such as Postman, explore the APIs.

Some examples are as below:
To create a new user for example, you would send a POST request to:
http://localhost:8080/api/user/create with an example body like 

```
{
"username": "test"
}
```


and this would return
```
{
"id" 1,
"username": "test"
}
```


Exercise:
Once you've created a user, try  to add items to cart (see the `ModifyCartRequest` class) and submit an order. 

## Adding Authentication and Authorization
We need to add proper authentication and authorization controls so users can only access their data, and that data can only be accessed in a secure way. We will do this using a combination of usernames and passwords for authentication, as well as JSON Web Tokens (JWT) to handle the authorization.

As stated prior, we will implement a password based authentication scheme. To do this, we need to store the users' passwords in a secure way. This needs to be done with hashing, and it's this hash which should be stored. Additionally when viewing their user information, the user's hash should not be returned to them in the response, You should also add some requirements and validation, as well as a confirm field in the request, to make sure they didn't make a typo. 

1. Add spring security dependencies: 
   * Spring-boot-starter-security
1. JWT does not ship as a part of spring security, so you will have to add the 
   * java-jwt dependency to your project. 
1. Spring Boot ships with an automatically configured security module that must be disabled, as we will be implementing our own. This must be done in the Application class.
2. Create password for the user
3. Once that is disabled, you will need to implement 4 classes (at minimum, you can break it down however you like):
   * a subclass of `UsernamePasswordAuthenticationFilter` for taking the username and password from a login request and logging in. This, upon successful authentication, should hand back a valid JWT in the `Authorization` header
   * a subclass of `BasicAuthenticationFilter`. 
   * an implementation of the `UserDetailsService` interface. This should take a username and return a userdetails User instance with the user's username and hashed password.
   *  a subclass of `WebSecurityConfigurerAdapter`. This should attach your user details service implementation to Spring's `AuthenticationManager`. It also handles session management and what endpoints are secured. For us, we manage the session so session management should be disabled. Your filters should be added to the authentication chain and every endpoint but 1 should have security required. The one that should not is the one responsible for creating new users.


Once all this is setup, you can use Spring's default /login endpoint to login like so

```
POST /login 
{
"username": "test",
"password": "somepassword"
}
```

and that should, if those are valid credentials, return a 200 OK with an Authorization header which looks like "Bearer <data>" this "Bearer <data>" is a JWT and must be sent as a Authorization header for all other rqeuests. If it's not present, endpoints should return 401 Unauthorized. If it's present and valid, the endpoints should function as normal.

## Testing
You must implement unit tests demonstrating at least 80% code coverage.


## Docker

Launch an AWS EC2 instance
Using Amazon Linux 2 AMI and t2.micro instance type. This EC2 instance would already have the Docker package available on it. In addition, choose the following configuration:
Choose a default VPC, public subnet, and enable the auto-assign public IP
For the security group, allow TCP traffic on port 80 and 8080, and SSH traffic on port 22 from anywhere. Leave the remaining values as the defaults.
Download a new key-pair or use the existing one.
### Security group inbound rules for Jenkins EC2 instance
![Security Group Inbound rules for jenkins](readme-images/Security Group Inbound rules for jenkins.png)
Connect to the EC2 instance
Use SSH to connect to your Linux EC2 instance:
```
# Assuming the key name is AWS_EC2_DemoKey.pem available in the pwd
chmod 400 AWS_EC2_DemoKey.pem
# Assuming the public DNS is: ec2-18-221-37-196.us-east-2.compute.amazonaws.com
ssh -i "AWS_EC2_DemoKey.pem" ec2-user@ec2-18-221-37-196.us-east-2.compute.amazonaws.com
```

Install Docker
After successful connection, install the Docker, add the current user to the user-group, and reboot:
```
# update the existing packages
sudo yum update
# download and install Docker
sudo yum install docker
# Add the $USER user to the "docker" user group 
# The current $USER is ec2-user
sudo usermod -a -G docker $USER
sudo reboot
```

The last command above will reboot the EC2 instance, and hence, your connection will be closed. 
Reconnect to your EC2 instance after 2 minutes, using the same SSH command as used in the previous step.
```
# start Docker service
sudo service docker start
# Check if the Docker engine is running
systemctl show --property ActiveState docker
# Create and run a new Container using the "jenkinsci/blueocean" image
docker run -u root -d --name myContainer -p 8080:8080 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v "$HOME":/home jenkinsci/blueocean
```
In the command above, the various options are:

--name create a name for your container, say myContainer. However, the demo has shown the jenkins as container name.
-p specifies a port on which the Jenkins server will run. Basically, -p 8080:8080 mapping 8080 of the host (EC2 instance) to the 8080 of the container.
-d detached mode, meaning the container will run in the background
-v is binding a volume to persist the data of the Jenkins server. This is important because when we will restart the container, we would want the Jenkins related data (configuration, user-data, plugins) to be present there. We are binding three volumes.
-v jenkins-data:/var/jenkins_home is the first volume as the default home directory of Jenkins
-v "$HOME":/home is the second volume for user-specific data
-v /var/run/docker.sock:/var/run/docker.sock is the third volume where we have defined a docker socket in the container. This one will help to execute docker commands from within the container. Have a look at this discussion for more details.

At this stage, the Jenkins console will come up on the 8080 port, say http://18.221.37.196:8080 in your local browser. 
We need to generate an additional RSA key-pair (public and private) to secure the pipeline. We will place the public key in the Github account, and private key in the Jenkins console.

# Open a shell into myContainer. The container name may vary in your case
docker exec -it myContainer bash
# Since our project is a Maven project, we need to install Maven in the container
apk add maven
# Generate RSA key-pair. It will generate a public and private key. 
# We will place the public key in the Github account, and the private key in the Jenkins console
ssh-keygen -t rsa
# View the private key
cat /root/.ssh/id_rsa
# View the pubic key 
cat /root/.ssh/id_rsa.pub

#### Admin login to Jenkins console

![unlock Jenkins](readme-images/unlock Jenkins.png)
Go to the AWS dashboard to copy the public IP address of your Linux EC2 instance. Paste the public IP address into your browser, and append with :8080 port. For the first time, it will open up the Jenkins console GUI. It will ask you the admin password for the first-time.
The Jenkins admins password can be found at two places, in the host EC2 instance, and inside the container.

# Run the following commands in the host EC2 instance's terminal
docker ps
# Use the container ID from the command above
docker logs <container_id>

Since our Jenkins server is running inside of the container, therefore the admin password will also be stored there as well.

# Open the bash into the container
docker exec -it myContainer bash
# View the file
cat /var/jenkins_home/secrets/initialAdminPassword

Paste the admin password into the Jenkins console, say http://18.221.37.196:8080 in your local browser, install the suggested plugins, and create the admin account.

#### Add private key to Jenkins global credentials
At the Jenkins console, go to Manage Jenkins → Manage Credentials → Global credentials to create an SSH username and paste the private key. 

# Open the bash into the container, if you have exited from the bash
docker exec -it myContainer bash
# View the private key
cat /root/.ssh/id_rsa
![Add SSH private key to jenkins](readme-images/Add SSH private key to jenkins.png)

#### Add public key to Github repository
Go to the repository in your Github account → Settings → Deploy keys page. Paste the public key here. Recall that you can view the public key from the bash into the container as:

# View the public key 
cat /root/.ssh/id_rsa.pub

 Jenkins console: Create and build the first Job

#### Create the Freestyle project type job, say myFirstJob, or choose any other name. Enter the details as mentioned below:

- General:
- Github project ->	Provide your Github repository URL
- Source Code Management:
	- Git ->	Check
	- Repository URL ->	Provide your SSH Github repository URL
	- Credentials ->	Choose the one you've created in the Global credentials
	- Branches to build -> */main
- Build:
 	- Add build step ->	Invoke top-level Maven targets
	- Goals ->	clean compile package
	- click on Advanced
	- POM ->	Specify the POM file path relative to your repository home, such as
pom.xml

Save the job, and click on the "Build Now" option. 

### After restarting the AWS EC2 instance
Note that the Public IP addresses of an EC2 instance keep changing after every reboot
Replace the key file name and DNS as applicable to you
```
# Connect to the EC2 instance
ssh -i "AWS_EC2_DemoKey.pem" ec2-user@ec2-18-222-193-10.us-east-2.compute.amazonaws.com
# Start Docker service
sudo service docker start
# Check if the Docker engine is running
systemctl show --property ActiveState docker
# Check the stopped containers
docker ps --filter "status=exited"
# And then start your container `docker start <container_name/ID>`:
docker start myContainer
# Check the running containers
docker ps
# Then you can access jenkins using the Public IPv4 DNS of your EC2 instance.
# Open a shell into myContainer. The container name may vary in your case
docker exec -it myContainer bash
```
###  Launch a new host EC2 instance for the Tomcat server container

Launch a new EC2 instance, based on Amazon Linux 2 AMI and t2.small/t2.micro. Let's name it Host_2, assuming we already have Jenkins running inside a container on Host_1.
2. Install docker on EC2 instance

Connect to Host_2 using SSH, and install docker:
```
# update the existing packages
sudo yum update
sudo  yum install docker

3. Create a new user for Docker management, and add that user to Docker (default) group.

sudo useradd host2admin
sudo passwd host2admin
# Add the host2admin user to the "docker" user group 
sudo usermod -aG docker host2admin
# Add the $USER user to the "docker" user group. The current $USER is ec2-user
sudo usermod -a -G docker $USER
sudo reboot
```

4. Start services

Reconnect using SSH. The public IP will change after reboot and then start docker
```
sudo service docker start
# Verify that you can run docker commands without sudo.
docker run hello-world
```
5. Write a Dockerfile under /opt/docker/ directory

Create the /opt/docker/ directory
```
sudo su -
cd /opt
mkdir docker
cd docker
vi Dockerfile

```

Add the following to the new Dockerfile

```
# Pull base image 
From tomcat:8-jre8 
# Maintainer
MAINTAINER "Udacity" 
# copy war file on to container 
COPY ./*.war /usr/local/tomcat/webapps
```


6. Allow Jenkins' access to the Docker

Jenkins will attempt to write files in the Docker as the newly created user "host2admin". Therefore, enable the password-based authentication
```
vi /etc/ssh/sshd_config
# Comment the passwordauthentication line
# To disable tunneled clear text passwords, change to no here!
#PasswordAuthentication yes
#PermitEmptyPasswords no
#PasswordAuthentication no

sudo service sshd restart
```


Change ownership permissions, allowing the new user "host2admin" to write here
```
chown -R host2admin:host2admin /opt/docker/
sudo service docker restart
```


#### Add Plugins
On the Jenkins console, go to the Manage Jenkins → Manage Plugins section. Here you can add new ones, or update the existing plugins. For our deployment, we need to add the following two plugins:
Deploy to container plugin: This plugin takes a war/ear file and deploys that to a running remote application server at the end of a build.
Maven Integration plugin: This plugin is used for building Maven jobs.

Configure Java and Maven on Jenkins
While building and deploying the application, the right compatible version of Java Maven should be present on the Jenkins server. You can check the version of Java and Maven from inside of the Jenkins container:

# Open a shell into the Jenkins container
docker exec -it myContainer bash
java -version
echo $JAVA_HOME
# We already installed Maven using the command "apk add maven" earlier
mvn -version

Go to the Jenkins console, and open the Manage Jenkins → Global Tool Configuration settings.

- JDK
    - JDK Name -	openjdk-11
	- JAVA_HOME -	The output of echo $JAVA_HOME, such as /opt/java/openjdk
- Maven 	
    - Maven Name -	Check the version using mvn -version
in your container, such as Maven 3.6.3
	- Install automatically -	Check


 7. Login to Jenkins console and add Docker server to execute commands from Jenkins (you need to make sure that the jenkins docker container is running on the EC2 instance first. If it's not, start it.)
 
Manage Jenkins → Manage plugins → Install "Publish over SSH" plugin
Manage Jenkins → Configure system → Publish over SSH → SSH Server -> Add
Add the new host private IP address and username of the newly created user
 (name doesn't matter, hostname is private ip, username is host2admin in our example, click on Advanced, add password and then "Test Configuration")
 Go to *Configure system**, and provide details to publish over SSH
 8. Create Jenkins job
 
 Create a new job, mySecondJob (Type: Maven project), and configure with the following details (leaving remaining details as default):
 
Source Code Management
Repository : https://github.com/YOUR_USERNAME/nd035-c4-Security-and-DevOps
Branches to build : */master
 
Build
Root POM: pom.xml
Goals and options: clean install package
 
Post Steps
Add post-build steps: Choose Send files or execute commands over SSH
Name: Host_2 or whatever name you chose in step 7 (Choose Verbose mode)
Source files: target/*.war
Remove prefix: target
Remote directory: //opt//docker
Exec command[s]:
```
docker stop demo_container;  
docker rm -f demo_container;
docker stop demo_image;  
docker rm -f demo_image; 
docker image rm -f demo_image; 
cd /opt/docker; 
docker build -t demo_image .
``` 

 
The commands above will remove any existing container/image with the given name, and create a fresh new image, demo_image, inside the current /opt/docker/ directory. 

Add another Transfer Set, and use the following Exec command:
```
docker run -d --name demo_container -p 8888:8080 demo_image
```

 
The command above will create a new container, demo_container using the demo_image created in the previous command.
 
#### Jenkins errors

There is insufficient memory for the Java Runtime Environment to continue.
Native memory allocation (mmap) failed to map 32604160 bytes for committing reserved memory.
[Source](https://stackoverflow.com/questions/31041512/jenkins-build-throwing-an-out-of-memory-error/42521447)
```
 By default you don't have swap space. To confirm this:

free -m

Just add some. Try with 1 GB for begin.

sudo fallocate -l 1G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile

Check again:

free -m

```

### Jenkins Maven project logs:

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
Checking out Revision 5b3e2ba12979d30c16280284792a7ef9bda8f689 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 5b3e2ba12979d30c16280284792a7ef9bda8f689 # timeout=10
Commit message: "Added packaging in pom.xml and updated README"
 > git rev-list --no-walk 0244eda0e7084ab96de8f0ad2256c96dfa7b5daf # timeout=10
Parsing POMs
Discovered a new module com.example:eCommerceApp eCommerceApp
Modules changed, recalculating dependency graph
Established TCP socket on 32805
[jenkins_to_tomcat_separate_hosts] $ /opt/java/openjdk/bin/java -cp /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-agent-1.13.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/boot/plexus-classworlds-2.6.0.jar:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3/conf/logging jenkins.maven3.agent.Maven35Main /var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.6.3 /var/jenkins_home/war/WEB-INF/lib/remoting-4.10.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven35-interceptor-1.13.jar /var/jenkins_home/plugins/maven-plugin/WEB-INF/lib/maven3-interceptor-commons-1.13.jar 32805
<===[JENKINS REMOTING CAPACITY]===>channel started
Executing Maven:  -B -f /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/pom.xml clean install package
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.example:eCommerceApp >----------------------
[INFO] Building eCommerceApp 0.0.1-SNAPSHOT
[INFO] --------------------------------[ war ]---------------------------------
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-war-plugin/3.2.2/maven-war-plugin-3.2.2.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-war-plugin/3.2.2/maven-war-plugin-3.2.2.pom (9.7 kB at 15 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-war-plugin/3.2.2/maven-war-plugin-3.2.2.jar
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-war-plugin/3.2.2/maven-war-plugin-3.2.2.jar (91 kB at 1.5 MB/s)
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
[INFO] Compiling 3 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.19.1:test (default-test) @ eCommerceApp ---

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.example.demo.controllers.UserControllerTest
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/root/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/root/.m2/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
12:11:00.282 [main] INFO com.example.demo.controllers.UserController - Username set with 
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.969 sec - in com.example.demo.controllers.UserControllerTest

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[JENKINS] Recording test results
[INFO] 
[INFO] --- maven-war-plugin:3.2.2:war (default-war) @ eCommerceApp ---
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream/1.4.10/xstream-1.4.10.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream/1.4.10/xstream-1.4.10.pom (15 kB at 1.0 MB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream-parent/1.4.10/xstream-parent-1.4.10.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream-parent/1.4.10/xstream-parent-1.4.10.pom (35 kB at 2.0 MB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/xmlpull/xmlpull/1.1.3.1/xmlpull-1.1.3.1.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/xmlpull/xmlpull/1.1.3.1/xmlpull-1.1.3.1.pom (386 B at 39 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/xpp3/xpp3_min/1.1.4c/xpp3_min-1.1.4c.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/xpp3/xpp3_min/1.1.4c/xpp3_min-1.1.4c.pom (1.6 kB at 146 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-mapping/3.0.0/maven-mapping-3.0.0.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-mapping/3.0.0/maven-mapping-3.0.0.pom (4.1 kB at 414 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/22/maven-shared-components-22.pom
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-shared-components/22/maven-shared-components-22.pom (5.1 kB at 426 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-compress/1.16.1/commons-compress-1.16.1.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-interpolation/1.25/plexus-interpolation-1.25.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream/1.4.10/xstream-1.4.10.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/xmlpull/xmlpull/1.1.3.1/xmlpull-1.1.3.1.jar
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/xpp3/xpp3_min/1.1.4c/xpp3_min-1.1.4c.jar
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/xmlpull/xmlpull/1.1.3.1/xmlpull-1.1.3.1.jar (7.2 kB at 61 kB/s)
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-mapping/3.0.0/maven-mapping-3.0.0.jar
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/xpp3/xpp3_min/1.1.4c/xpp3_min-1.1.4c.jar (25 kB at 178 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/shared/maven-mapping/3.0.0/maven-mapping-3.0.0.jar (11 kB at 71 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/codehaus/plexus/plexus-interpolation/1.25/plexus-interpolation-1.25.jar (85 kB at 488 kB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-compress/1.16.1/commons-compress-1.16.1.jar (560 kB at 1.7 MB/s)
[INFO] Downloaded from central: https://repo.maven.apache.org/maven2/com/thoughtworks/xstream/xstream/1.4.10/xstream-1.4.10.jar (590 kB at 1.6 MB/s)
[INFO] Packaging webapp
[INFO] Assembling webapp [eCommerceApp] in [/var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Webapp assembled in [239 msecs]
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
[INFO] Compiling 3 source files to /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.19.1:test (default-test) @ eCommerceApp ---
[INFO] Skipping execution of surefire because it has already been run for this configuration
[JENKINS] Recording test results
[INFO] 
[INFO] --- maven-war-plugin:3.2.2:war (default-war) @ eCommerceApp ---
[INFO] Packaging webapp
[INFO] Assembling webapp [eCommerceApp] in [/var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT]
[INFO] Processing war project
[INFO] Webapp assembled in [372 msecs]
[INFO] Building war: /var/jenkins_home/workspace/jenkins_to_tomcat_separate_hosts/target/eCommerceApp-0.0.1-SNAPSHOT.war
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.1.5.RELEASE:repackage (repackage) @ eCommerceApp ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  26.932 s
[INFO] Finished at: 2021-11-01T12:11:14Z
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
Error response from daemon: No such container: demo_container
Error: No such container: demo_container
Error response from daemon: No such container: demo_image
Error: No such container: demo_image
Error: No such image: demo_image
Sending build context to Docker daemon  39.44MB

Step 1/3 : From tomcat:8-jre8
 ---> cff25871f024
Step 2/3 : MAINTAINER "Udacity"
 ---> Using cache
 ---> b8c8f57b5a17
Step 3/3 : COPY ./*.war /usr/local/tomcat/webapps
 ---> 12fe17275ab5
Successfully built 12fe17275ab5
Successfully tagged demo_image:latest
SSH: EXEC: completed after 1,802 ms
SSH: Opening exec channel ...
SSH: EXEC: channel open
SSH: EXEC: STDOUT/STDERR from command [docker run -d --name demo_container -p 8888:8080 demo_image] ...
SSH: EXEC: connected
a88fa21433b6f89899737c756237007362b022945186d4018330958432b25f98
SSH: EXEC: completed after 800 ms
SSH: Disconnecting configuration [Tomcat] ...
SSH: Transferred 1 ( 1 + 0 ) file(s)
Finished: SUCCESS
```

