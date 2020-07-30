import java.awt.*;
import java.awt.event.*;
import java.io.*;
class A extends Frame implements ActionListener{
	MenuItem item1,item2;
	Menu m;
	MenuBar mb;
	TextArea ta;
	A(){
		item1=new MenuItem("����");
		item2=new MenuItem("����");
		m=new Menu("����");
		m.add(item1);
		m.add(item2);
		mb=new MenuBar();
		mb.add(m);
		setMenuBar(mb);
		ta=new TextArea();
		add(ta);

		item1.addActionListener(this);
		item2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==item1){
		//���� ����
		FileDialog fd=new FileDialog(this);
		fd.setVisible(true);
		readFile(fd.getDirectory()+fd.getFile());
		}else{
		//���� ����
		FileDialog fd=new FileDialog(this, "��������", FileDialog.SAVE);
		fd.setVisible(true);
		writeFile(fd.getDirectory()+fd.getFile());
		}
	}

	void writeFile(String fn){
		try(
			FileOutputStream fos = new FileOutputStream(fn);
		){
			byte[] buffer = ta.getText().getBytes();
			fos.write(buffer);
		}catch(IOException e){
			System.out.println("���ٰ� �����߻�~");
		}
	}

	void readFile(String fn){
		try(
			FileInputStream fis=new FileInputStream(fn);
		){
			byte[] buffer=new byte[fis.available()];
			fis.read(buffer);
			ta.setText(new String(buffer));
		}catch(FileNotFoundException e1){
			System.out.println("�ش����� �����~");
		}catch(IOException e2){
			System.out.println("�дٰ� �����߻�~");
		}
	}
}

class B{
	public static void main(String args[]){
		A f=new A();
		f.setBounds(100,100,500,500);
		f.setVisible(true);
	}
}
