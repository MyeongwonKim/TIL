import java.io.*;
class A{
public static void main(String args[]){
try(
	FileInputStream fis=new FileInputStream("t2.java");
	){
	int size = fis.available();
	byte[] buffer = new byte[size];
	fis.read(buffer);
	System.out.print(new String(buffer));
	}catch(FileNotFoundException e){
	System.out.println("�ش������� �������� �ʽ��ϴ�.");
	}catch(IOException e){
	System.out.println("�аų� ���ٰ� �����߻�~");
}
}
}