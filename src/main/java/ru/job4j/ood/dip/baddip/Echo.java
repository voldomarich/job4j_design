package ru.job4j.ood.dip.baddip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Запись System.out.println("Exception in log example") в catch является нарушением DIP,
потому что есть прямая зависимость самого логгирования от реализации,
в данном случае оно напрямую зависит от консольного вывода.
Решение - использование абстракции для логгирования
private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
После чего в блоке catch прописать LOG.error("Exception in log example", e);
*/

public class Echo {

    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                }
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            System.out.println("Exception in log example");
        }
    }
}
