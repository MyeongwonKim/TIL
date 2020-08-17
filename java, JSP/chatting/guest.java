import java.io.*;
import java.net.*;
import java.util.*;

class Guest extends Thread {
  Socket s;
  Scanner sc;
  OutputStream out;
  Server server;
  String id;

  Guest(Socket s, Server server) {
    try {
      this.server = server;
      this.s = s;
      InputStream in = s.getInputStream();
      sc = new Scanner(in);
      out = s.getOutputStream();
    } catch (Exception e) {
      System.out.println("��Ʈ��ũ �ʱ�ȭ�ϴٰ� ����~");
      closeAll();
    }
  }

  void sendMsg(String msg) {
    try {
      out.write((msg + "\n").getBytes());
    } catch (Exception e) {
      System.out.println("�������� ����~");
      closeAll();
    }
  }

  public void run() { //Ŭ���̾�Ʈ�� ���� �޽��� �б�. Thread�� ����
    try {
      while (true) {
        String msg = sc.nextLine();
        String[] array = msg.split("/");
        switch (array[0]) {
          case "enter":
            id = array[1];
            server.broadcast(msg);
            server.makeGuestlist();
            break;
          case "msg":
            server.broadcast(msg + "/" + id);
            break;
          case "leave":
            server.deleteGuest(this); // "leave/" �޽��� ���� ��� guestlist���� �ش� guest ����
            server.broadcast(msg + id); // "leave/id" ���·� �޽��� broadcast
            server.makeGuestlist(); // ���ŵ� guestlist ����
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("�о���̴��� ����~");
      closeAll();
    }
  }

  void closeAll() {
    try {
      if (out != null) {
        out.close();
        out = null;
      }
      if (sc != null) {
        sc.close();
        sc = null;
      }
      if (s != null) {
        s.close();
        s = null;
      }
    } catch (Exception e) {
      System.out.println("�ڿ������� �����߻�~");
    }
  }
}
