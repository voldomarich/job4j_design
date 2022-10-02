package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = in.readLine();
                    if (string.contains("Exit")) {
                        server.close();
                        System.out.println("Server is successfully closed");
                        break;
                    } else if (string.contains("Hello")) {
                        out.write("Hello".getBytes());
                        System.out.println(string);
                        System.out.println("Hello");
                    } else {
                        out.write("What".getBytes());
                        System.out.println(string);
                        System.out.println("What");
                    }
                    out.flush();
                }
            }
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
