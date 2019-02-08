package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketOfClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("127.0.0.1", 7410);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            String request = "register SocketTest1 SocketTest1";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            //request = "";
            bufferedWriter.write(request);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
            String response = bufferedReader.readLine();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
