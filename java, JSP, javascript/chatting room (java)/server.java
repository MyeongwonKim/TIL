import java.io.*;
import java.net.*;
import java.util.*;

class Server {
  ServerSocket ss;
  List<Guest> list;

  void initNet() { //��Ʈ��ũ �ʱ�ȭ
    try {
      list = new ArrayList<Guest>();
      ss = new ServerSocket(7788);
      while (true) {
        Socket s = ss.accept();
        Guest guest = new Guest(s, this); //Guest ��ü ���� �� Socket ����
        guest.start(); //guest �б� Thread ����
        addGuest(guest);
      }
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("��Ʈ��ũ �ʱ�ȭ�ϴٰ� ����~");
      closeAll();
    }
  }

  void addGuest(Guest guest) { //guest ����� list�� �߰�
    list.add(guest);
    System.out.println("�����ڼ�:" + list.size());
  }

  void deleteGuest(Guest guest) {
    list.remove(guest); //guest ����� list���� ����
    System.out.println("�����ڼ�:" + list.size());
  }

  void broadcast(String msg) { //guest�κ��� msg�� ������ ��� client���� broadcasting
    for (Guest guest : list) guest.sendMsg(msg);
  }

  void makeGuestlist() { //guest list�� String���� ��ȯ�Ͽ� broadcasting
    StringBuffer buffer = new StringBuffer("guestlist/");
    for (Guest guest : list) {
      buffer.append(guest.id);
      buffer.append("/");
    }
    broadcast(buffer.toString());
  }

  void closeAll() { //�ڿ� ����
    try {
      if (ss != null) {
        ss.close();
        ss = null;
      }
    } catch (Exception e) {
      System.out.println("�ڿ������� �����߻�~");
    }
  }

  public static void main(String args[]) {
    Server server = new Server();
    server.initNet();
  }
}
