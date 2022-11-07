package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fromServer.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;

public class Client {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectMapper jsonConvert;
    private StringWriter writer;
    public Client(){
        try {
            client=new Socket("127.0.0.1",1024);
            in=new DataInputStream(client.getInputStream());
            out=new DataOutputStream(client.getOutputStream());
            jsonConvert=new ObjectMapper();
            writer=new StringWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean signUp(User user){
        String result;
        byte check=1;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result=writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            check=in.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(check==0) return false;
        else return true;
    } //Регистрация
    public boolean updateUser(User user){
        String result="";
        byte check=3;
        try {
            //client.setSoTimeout(10000);
            out.writeByte(check);
            jsonConvert.writeValue(writer,user);
            result=writer.toString();
            out.writeUTF(result);
            check=in.readByte();
        } catch (IOException e) {
            return false;
        }
        if(check==0) return false;
        else return true;
    }
    public User signIn(User user){
        String result;
        byte check=2;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result=writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            check=in.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(check==0) return null;
        else{
            try {
                client.setSoTimeout(10000);
                result=in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                user=null;
                user=jsonConvert.readValue(result,User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return user;
        }
    } //Вход
    public void disconnect(){
        try {
            out.writeByte(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
