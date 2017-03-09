package xyz.moyuyc.work3;

import xyz.moyuyc.model.WordPlayer;

import java.util.Stack;

/**
 * Created by Yc on 2016/4/12 for compiler2.
 */
public class Compiler3 {

    String E() throws Exception {
//        log("E bg");
        String p = null;
        String tp = T();
        String e1p = E1();
        if(e1p!=null) {
            p = newTemp();
            String o = getOperation("+", "-");
            if (o != null)
                log(p + "=" + tp + o + e1p);
        }else{
            p = tp;
        }
//        log("E ed");
        return p;
    }
    String E1() throws Exception {
//        log("E1 bg");
        String p = null;
        if(sym.equals("+")||sym.equals("-")){
            op.push(sym);
            getSymbol();
            String tp = T();
            String e1p = E1();
            if(e1p!=null){
                p = newTemp();
                String o = getOperation("+", "-");
                if(o!=null)
                    log(p+"="+tp+o+e1p);
            }else{
                p = tp;
            }
        }
//        log("E1 ed");
        return p;
    }
    String T() throws Exception {
//        log("T bg");
        String p = null;
        String fp = F();
        String t1p = T1();
        if(t1p!=null) {
            p = newTemp();
            String o = getOperation("*", "/");
            if (o != null)
                log(p + "=" + fp + o + t1p);
        }else{
            p = fp;
        }
//        log("T ed");
        return p;
    }
    String T1() throws Exception {
//        log("T1 bg");
        String p = null;
        if(sym.equals("*")||sym.equals("/")){
            op.push(sym);
            getSymbol();
            String fp = F();
            String t1p = T1();
            if(t1p!=null){
                p = newTemp();
                String o = getOperation("*","/");
                if(o!=null)
                    log(p+"="+fp+o+t1p);
            }else{
                p = fp;
            }
        }
//        log("T1 ed");
        return p;
    }
    int n = 0;
    String newTemp(){
        return "T"+n++;
    }
    String getOperation(String s1,String s2) throws Exception {
        if(op.isEmpty())
            return null;
        String oper = op.peek();
        if(!oper.equals(s1) && !oper.equals(s2)){
//            printError();
            return null;
        }else{
            op.pop();
        }
        return oper;
    }
    String F() throws Exception {
//        log("F bg");
        String p = null;
        if(tv.getType()== Type.INT||tv.getType()== Type.FLOAT){ // F->i
            p = sym;
            getSymbol();
        }else {
            if(sym.equals("(")){
                getSymbol();
                String ep = E();
                if(sym.equals(")")) {
                    p = ep;
                    getSymbol();
                    if(tv.getType()== Type.INT||tv.getType()== Type.FLOAT){
                        printError();
                    }
                }
                else
                    printError();
            }else
                printError();
        }
//        log("F ed");
        return p;
    }

    void log(Object o){
        System.out.println(o);
    }

    Stack<String> op = new Stack<>();

    String sym;
    TypeVal tv;
    WordPlayer wp;
    void getSymbol() throws Exception {
        if(wp.hasNext()) {
            sym = wp.nextString();
            tv = wp.next();
            if(tv.getType()!=Type.MINUS&&tv.getType()!=Type.DIVIDE&&tv.getType()!=Type.MULTIPLY&&tv.getType()!=Type.PLUS&&tv.getType()!=Type.FLOAT&&tv.getType()!=Type.INT&&tv.getType()!=Type.LSB&&tv.getType()!=Type.RSB)
                printError();
            //System.out.println(tv);
        }
    }
    boolean start(String str) throws Exception {
        wp = new WordPlayer(new StringBuffer(str));
        getSymbol();
        log("print("+E()+")");
        return true;
    }
    void printError() throws Exception {
        int v = wp.getCurIndex() - 1;
        StringBuffer space = new StringBuffer();
        while (v-- > 0) {
            space.append(" ");
        }
        throw new Exception("\n" + wp.getSrc() + "\n" + space + "^");
    }
    public static void main(String[] args) throws Exception {
        System.out.println(new Compiler3().start("(5+0.23+4)-8.8/9"));//"6-3*(1+2)"
    }
}
