import java.io.*;

/**
 * Created by Yc on 2016/5/5 for compiler2.
 */
public class ComputeLineNum {
    static int getLineNum(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String l = null;
            int n = 0;
            while ((l=br.readLine())!=null){
                n++;
            }

            System.out.println(file.getAbsolutePath());
            System.out.println("行数为: "+n);

            br.close();
            return n;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static int getLineNumAll(File f){
        int n = 0;
        if(f.isDirectory()){
            for(File file:f.listFiles()){
                n += getLineNumAll(file);
            }
        }else
            n = getLineNum(f);
        return n;
    }

    static int getLineNumFile(File f){
        int n = 0;
        if(f.isDirectory()){
            for(File file:f.listFiles()){
                if(file.isDirectory()) continue;
                n += getLineNum(file);
            }
        }else
            n = getLineNum(f);
        return n;
    }

    public static void main(String[] args) {
        String s1[]={
                "D:\\F盘\\#1 (NTFS)30.00 GB\\intellij Projects\\autoexam_system\\src",
                "D:\\F盘\\#1 (NTFS)30.00 GB\\intellij Projects\\autoexam_system\\web\\template",
                "D:\\F盘\\#1 (NTFS)30.00 GB\\intellij Projects\\autoexam_system\\web\\ajax",
                "D:\\F盘\\#1 (NTFS)30.00 GB\\intellij Projects\\autoexam_system\\web\\js",
        },s2[] = {
                "D:\\F盘\\#1 (NTFS)30.00 GB\\intellij Projects\\autoexam_system\\web\\"
        };
        int n = 0;
        for(String s:s1){
            int v = getLineNumAll(new File(s));
            n+=v;
        }
        for(String s:s2){
//            System.out.println(s);
            int v = getLineNumFile(new File(s));
//            System.out.println("行数为: "+v);
            n+=v;
        }
        System.out.println("总行数为: "+n);
    }
}
