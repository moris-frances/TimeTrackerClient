package com.fhtw.taskmanagerclient.model;

import com.fhtw.taskmanagerclient.model.dto.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.core.TreeMarshallingStrategy;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;

public class Client {
    private String hostName;
    private String token = "";
    private int portNumber;
    XStream xstream = null;
    public AssociateDto user = new AssociateDto();
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    public Client(String hostName, int portNumber) throws IOException {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.socket = new Socket(hostName, portNumber);
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        initializeXstream();
    }

    public String getToken() {
        return token;
    }
    //sends a login request to the server
    public boolean login(String username, String password) throws IOException {

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername(username);
        signInRequest.setPassword(password);
        //send request
        out.writeUTF(xstream.toXML(signInRequest));
        //read response
        SignInResponse signInResponse = (SignInResponse) xstream.fromXML(in.readUTF());

        if (signInResponse.isRequestSucceeded()) {
            token = signInResponse.getJwtToken();
            this.user.setAssociateId(signInResponse.getEmployeeId());
            this.user.setAssociateName(username);
            System.out.println("Server response: " + signInResponse);
            return true;
        }
        return false;
    }
    //requests user info and updates it when received
    //used after login and after every entering the system
    public void userInfoUpdate(){

    }
    public void addTask(TaskDto task) throws IOException, ParseException {
        AddTaskRequest request = new AddTaskRequest (this.token,
                                                    task.getEmployeeTask(),
                                                    task.getEmployeeDateFrom(),
                                                    task.getEmployeeHoursSpent());
        out.writeUTF(xstream.toXML(request));
        String serverResponseXml = in.readUTF();
        AddTaskResponse addTaskResponse = (AddTaskResponse) xstream.fromXML(serverResponseXml);

        System.out.println("Server response: "+addTaskResponse);
    }

    public GetAssociateTasksResponse getTasksInAnInterval(String startDate, String endDate) throws IOException, ParseException {

        GetAssociateTasksRequest getAssociateTasksRequest = new GetAssociateTasksRequest(startDate, endDate, this.token);
        System.out.println(getAssociateTasksRequest.getStartDate()+ "   " + getAssociateTasksRequest.getEndDate()+ "   " + getAssociateTasksRequest.getToken());
        out.writeUTF(xstream.toXML(getAssociateTasksRequest));

        GetAssociateTasksResponse TasksResponse = (GetAssociateTasksResponse) xstream.fromXML(in.readUTF());

        System.out.println("Server response: " + TasksResponse);
        return TasksResponse;
    }

    public boolean updatePassword(String newPassword, String repeatNewPassword) throws IOException {

        if(!newPassword.equals(repeatNewPassword)) return false;
        UpdateCredentialRequest request  = new UpdateCredentialRequest(newPassword, this.token);
        out.writeUTF(xstream.toXML(request));
        UpdateCredentialResponse response = (UpdateCredentialResponse) xstream.fromXML(in.readUTF());
        if(!response.isRequestSucceeded()) return false;
        return true;
    }

    private void initializeXstream(){
        xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.setMode(XStream.XPATH_RELATIVE_REFERENCES);
        xstream.alias("SignInRequest", SignInRequest.class);
        xstream.alias("SignInResponse", SignInResponse.class);
        xstream.alias("AddTaskRequest", AddTaskRequest.class);
        xstream.alias("AddTaskResponse", AddTaskResponse.class);
        xstream.alias("GetAllAssociatesByManagerIdRequest", GetAllAssociatesByManagerIdRequest.class);
        xstream.alias("GetAllAssociatesByManagerIdResponse", GetAllAssociatesByManagerIdResponse.class);
        xstream.alias("GetAssociateTasksRequest", GetAssociateTasksRequest.class);
        xstream.alias("GetAssociateTasksResponse", GetAssociateTasksResponse.class);
        xstream.alias("UpdateCredentialRequest", UpdateCredentialRequest.class);
        xstream.alias("UpdateCredentialResponse", UpdateCredentialResponse.class);
        xstream.alias("ExitRequest", ExitRequest.class);
        xstream.alias("org.fhtw.dto.TaskDto", TaskDto.class);
        xstream.registerConverter(new NullConverter(), XStream.PRIORITY_VERY_HIGH);
        xstream.setMarshallingStrategy(new TreeMarshallingStrategy());
    }
}
