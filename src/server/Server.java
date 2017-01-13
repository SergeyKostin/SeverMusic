/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ровесник
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Waiting for a client...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         BufferedInputStream sin = new BufferedInputStream(socket.getInputStream());
         BufferedOutputStream sout = new BufferedOutputStream(socket.getOutputStream());

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         ObjectInputStream in = new ObjectInputStream(sin);
         ObjectOutputStream out = new ObjectOutputStream(sout);

         String line = null;
         while(true) {
           line = (String) in.readObject(); // ожидаем пока клиент пришлет строку текста.
           System.out.println("The dumb client just sent me this line : " + line);
           System.out.println("I'm sending it back...");
           out.writeObject(line); // отсылаем клиенту обратно ту самую строку текста.
           out.flush(); // заставляем поток закончить передачу данных.
           System.out.println("Waiting for the next line...");
           System.out.println();
         }
      } catch(Exception x) { x.printStackTrace(); }
    }
    
}
