package client;

import org.json.JSONTokener;
import org.json.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fromServer.Family;
import fromServer.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private final Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectMapper jsonConvert;
    private StringWriter writer;

    public Client() {
        try {
            client = new Socket("127.0.0.1", 1024);
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean signUp(User user) {
        writer = new StringWriter();
        jsonConvert = new ObjectMapper();
        String result;
        byte check = 1;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer, user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            check = in.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return check != 0;
    } //Регистрация

    public boolean updateUser(User user) {
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        String result;
        byte check = 3;
        try {
            client.setSoTimeout(10000);
            out.writeByte(check);
            jsonConvert.writeValue(writer, user);
            result = writer.toString();
            out.writeUTF(result);
            check = in.readByte();
        } catch (IOException e) {
            return false;
        }
        return check != 0;
    } //Изменение пользователя
    public User signIn(User user) {
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        String result;
        byte check = 2;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer, user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            check = in.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (check == 0) return null;
        else {
            try {
                client.setSoTimeout(10000);
                result = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                user = null;
                user = jsonConvert.readValue(result, User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return user;
        }
    } //Вход
    public void disconnect() {
        try {
            out.writeByte(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Family addFamily(Family family) {
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        String result;
        byte check = 4;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer, family);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            result = in.readUTF();
            family = jsonConvert.readValue(result, Family.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return family;
    } //Добавление новой семьи
    public ArrayList<Family> getFamily() {
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        ArrayList<Family> family = new ArrayList<>();
        String result;
        byte check = 5;
        try {
            out.writeByte(check);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            result = in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray array=new JSONArray();
        array=(JSONArray) new JSONTokener(result).nextValue();
        try {
            for (int i = 0; i < array.length(); i++) {
                result = array.get(i).toString();
                Family tmp=jsonConvert.readValue(result,Family.class);
                family.add(tmp);
            }
        }catch (JsonProcessingException e){};
        return family;
    }
    public ArrayList<User> getUsersFromFamily(Family family,boolean connect){
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        ArrayList<User> users = new ArrayList<>();
        String result;
        byte check;
        if(connect) check=7;
        else check=6;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer, family);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.setSoTimeout(10000);
            result = in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray array=new JSONArray();
        array=(JSONArray) new JSONTokener(result).nextValue();
        try {
            for (int i = 0; i < array.length(); i++) {
                result = array.get(i).toString();
                User tmp=jsonConvert.readValue(result,User.class);
                users.add(tmp);
            }
        }catch (JsonProcessingException e){};
        return users;
    }
    public void getNewCreator(Family family){
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        String result;
        byte check = 8;
        try {
            out.writeByte(check);
            jsonConvert.writeValue(writer, family);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = writer.toString();
        try {
            out.writeUTF(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUserFromFamily(String login){
        jsonConvert = new ObjectMapper();
        writer = new StringWriter();
        String result;
        byte check = 9;
        try {
            out.writeByte(check);
            out.writeUTF(login);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
