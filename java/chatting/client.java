import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Client extends Frame implements ActionListener {
  TextArea ta;
  java.awt.List list;
  TextField tf;
  Button b;

  Socket s;
  Scanner sc;
  OutputStream out;

  String id;

  Client(String id) {
    super(id + "�� ä��â");
    this.id = id;
    ta = new TextArea();
    list = new java.awt.List();
    tf = new TextField();
    b = new Button("������");

    Panel centerPanel = new Panel();
    Panel southPanel = new Panel();
    centerPanel.setLayout(new BorderLayout());
    southPanel.setLayout(new BorderLayout());
    centerPanel.add(ta);
    centerPanel.add(list, "East");
    southPanel.add(tf);
    southPanel.add(b, "East");

    add(centerPanel);
    add(southPanel, "South");

    tf.addActionListener(this);
    b.addActionListener(this);
  }

  void initNet() {
    try {
      s = new Socket("localhost", 7788);
      InputStream in = s.getInputStream();
      sc = new Scanner(in);
      out = s.getOutputStream();
      sendMsg("enter/" + id);
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

  void readMsg() {
    try {
      while (true) {
        String msg = sc.nextLine();
        String[] array = msg.split("/");
        switch (array[0]) {
          case "enter":
            ta.append("###" + array[1] + "�� ����~\n");
            break;
          case "msg": // [�����ڸ�] + �Է³��� ���·� msg ���
            ta.append("[" + array[2] + "]" + array[1] + "\n");
            break;
          case "guestlist": // guestlist�� ������ �����̳� ����� ���ŵ�
            list.removeAll();
            int size = array.length;
            for (int i = 1; i < size; i++) list.add(array[i]);
            break;
          case "leave": // "leave/id" �޽��� ���� ��
            ta.append("###" + array[1] + "�� ����~\n");
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

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == tf) { //textfield�� �Է� �� ���� ���� �� "msg/�Է³���" �޽��� ����
      sendMsg("msg/" + tf.getText());
      tf.setText("");
    } else { //"������" ��ư ���� ��� "leave/" �޽��� ����
      sendMsg("leave/");
      setVisible(false);
      dispose();
    }
  }

  public static void main(String args[]) {
    Client client = new Client(args[0]);
    client.initNet();
    client.setBounds(200, 200, 500, 500);
    client.setVisible(true);
    client.readMsg();
  }
}
