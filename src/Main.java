import xyz.moyu.modal.WordPlayer;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Yc on 2016/3/9 for myCompiler.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        String path = args[0];
        FileInputStream fis = new FileInputStream("src/test.cpp");
        byte[] buf = new byte[1024];
        int v = -1;
        StringBuffer src = new StringBuffer();
        while ((v=fis.read(buf))!=-1)
            src.append(new String(buf,0,v));
        System.out.println(src);
        WordPlayer wp = new WordPlayer(src);
        while (wp.hasNext()) {
            System.out.print(wp.next());
            System.out.print(wp.nextString());
        }
    }
}
