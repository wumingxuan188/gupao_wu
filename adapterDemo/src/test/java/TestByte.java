import org.junit.Test;

import java.io.*;

public class TestByte {
    public static void main(String[] args) throws Exception {


        System.err.println("工作目录: " + System.getProperty("user.dir") + "SecurityManager:" + System.getSecurityManager());
        //系统分隔符
        //File.separator;
        String str = "11111";
//        DataInputStream dis=new DataInputStream()
        byte[] bs = "abcdefghijklmnopqstuvxyz".getBytes();
        byte[] b = new byte[bs.length];
        // FileInputStream fis = new FileInputStream("D:\\test.txt");
        ByteArrayInputStream stream = new ByteArrayInputStream(b);
        String sb = "";
        int i = 0;
        while ((i = stream.read()) != -1) {
            sb += (char) i;
        }
        stream.close();
        System.err.println(sb);
        System.err.println(new String(b));
    }

    @Test
    public void test1() throws Exception {
        FileInputStream fis = new FileInputStream("D:\\test.txt");
        PushbackInputStream pb = new PushbackInputStream(fis);
        byte[] bytes = new byte[1024];
        int i;
        while ((i = pb.read()) != -1) {
            if (i == 49) {
                pb.unread(45);
                // i=  pb.read();
            }


            System.err.println((char) i);
            // System.err.println(i);
        }
       /* int read = pb.read();
        pb.unread(1);
        int read2 = pb.read();
        char read1 = (char) read2;*/

    }
}
