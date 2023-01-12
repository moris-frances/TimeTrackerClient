package com.fhtw.taskmanagerclient.model;

import com.fhtw.taskmanagerclient.model.dto.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.core.TreeMarshallingStrategy;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.net.Socket;

public class Client {
    private String hostName;
    private int portNumber;
    public UserModel userInfo = new UserModel();
    Socket socket;

    public Client(String hostName, int portNumber) throws IOException {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.socket = new Socket(hostName, portNumber);
    }
    //sends a login request to the server
    public boolean loginRequest(String username, String password) throws IOException {
        XStream xstream = new XStream();
        System.out.println("initialized Xstream");
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
        xstream.registerConverter(new NullConverter(), XStream.PRIORITY_VERY_HIGH);
        xstream.setMarshallingStrategy(new TreeMarshallingStrategy());
        SignInRequest signInRequest = new SignInRequest();

        signInRequest.setUsername(username);
        signInRequest.setPassword(password);

        System.out.println("wrote to server");

        String xml = xstream.toXML(signInRequest);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        out.writeUTF(xml);

        String userToken;
        String employeeRole;
        while (true) {

            String serverResponseXml = in.readUTF();
            SignInResponse signInResponse = (SignInResponse) xstream.fromXML(serverResponseXml);
            if (signInResponse.isRequestSucceeded()) {
                userToken = signInResponse.getJwtToken();
                employeeRole = signInResponse.getRole();
                System.out.println("Server response: ");
                System.out.println(signInResponse);
                break;
            }
            System.out.println(signInResponse);
        }
        this.userInfo.setUsername(username);
        //make request
        return true;
    }
    //requests user info and updates it when received
    //used after login and after every entering the system
    public void userInfoUpdate(){

    }
    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        out.flush();
        userInfoUpdate();
    }
}
