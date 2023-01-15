Task Manager Client

    This project is a client-side application that allows a user to interact with a task manager server through a graphical user interface.
    The client sends requests in XML format to the server and receives responses also in XML format.

Requirements:

    1. Java 8 or above
    2. XStream library
    3. Maven

Compiling the project:
    
    1. Navigate to the root directory of the project in your command line. This is the directory that contains the pom.xml file.
    2. To compile the project, run the command "mvn compile". This will download all the necessary dependencies and compile the code.
    3. To run the project, you can use the command "mvn exec:java -Dexec.mainClass="com.fhtw.taskmanagerclient.ClientApplication". This will execute the main class of the project, which is specified by the "exec.mainClass" property.
    4. You may also need to configure the server's hostname and port in the "src/main/java/com/fhtw/taskmanagerclient/ClientApplication.java" file before running the project.
       

Usage

    1. The application allows a user to login with a username and password 
    2. Once logged in, the user can view their tasks in a given date range
    3. The user can also add new tasks to the server
    4. If the user is a manager, they can view the tasks of their employees in a given date range.