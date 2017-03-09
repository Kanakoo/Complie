package xyz.moyuyc.model;

import xyz.moyuyc.work3.Type;
import xyz.moyuyc.work3.TypeVal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Yc on 2016/3/9 for myCompiler.
 */
public class WordPlayer {

    public static Map<String,Type> stringTypeMap = new HashMap<>();
    static {
        stringTypeMap.put("{",Type.LBB);stringTypeMap.put("}",Type.RBB);stringTypeMap.put("(",Type.LSB);stringTypeMap.put(")",Type.RSB);
        stringTypeMap.put("=",Type.ASSIGN);stringTypeMap.put(";",Type.SEMI);stringTypeMap.put(",",Type.COMMA);
        stringTypeMap.put("<",Type.LT);stringTypeMap.put("<=",Type.LE);stringTypeMap.put(">",Type.GT);stringTypeMap.put(">=",Type.GE);
        stringTypeMap.put("|",Type.BITOR);stringTypeMap.put("&", Type.BITAND);stringTypeMap.put("~",Type.BITNOT);
        stringTypeMap.put("!",Type.NOT);stringTypeMap.put("||",Type.OR);stringTypeMap.put("&&",Type.AND);
        stringTypeMap.put("-",Type.MINUS);stringTypeMap.put("*",Type.MULTIPLY);stringTypeMap.put("+",Type.PLUS);
        stringTypeMap.put("/",Type.DIVIDE);
        stringTypeMap.put("abstract",Type.ABSTRACT);
        stringTypeMap.put("boolean",Type.BOOLEAN);stringTypeMap.put("break",Type.BREAK);stringTypeMap.put("byte",Type.BYTE);
        stringTypeMap.put("case",Type.CASE);stringTypeMap.put("catch",Type.CATCH);stringTypeMap.put("char",Type.CHAR);
        stringTypeMap.put("class",Type.CLASS);stringTypeMap.put("continue",Type.CONTINUE);stringTypeMap.put("default",Type.DEFAULT);
        stringTypeMap.put("do",Type.DO);stringTypeMap.put("double",Type.DOUBLE);stringTypeMap.put("else",Type.ELSE);
        stringTypeMap.put("extends",Type.ELSE);stringTypeMap.put("false", Type.FALSE);stringTypeMap.put("final",Type.FINAL);
        stringTypeMap.put("finally",Type.FINALLY);stringTypeMap.put("if",Type.IF);stringTypeMap.put("implements",Type.IMPLEMENTS);
        stringTypeMap.put("import",Type.IMPORT);stringTypeMap.put("instanceof",Type.INSTANCEOF);stringTypeMap.put("int",Type.INT);
        stringTypeMap.put("interface",Type.INTERFACE);stringTypeMap.put("long",Type.LONG);stringTypeMap.put("native",Type.NATIVE);
        stringTypeMap.put("new",Type.NEW);stringTypeMap.put("null",Type.NULL);stringTypeMap.put("package",Type.PACKAGE);
        stringTypeMap.put("private",Type.PRIVATE);stringTypeMap.put("protected",Type.PROTECTED);stringTypeMap.put("public",Type.PUBLIC);
        stringTypeMap.put("return",Type.RETURN);stringTypeMap.put("short",Type.SHORT);stringTypeMap.put("static",Type.STATIC);
        stringTypeMap.put("super",Type.SUPER);stringTypeMap.put("switch",Type.SWITCH);stringTypeMap.put("synchronized",Type.SYNCHRONIZED);
        stringTypeMap.put("this",Type.THIS);stringTypeMap.put("throw",Type.THROW);stringTypeMap.put("throws",Type.THROWS);
        stringTypeMap.put("transient",Type.TRANSIENT);stringTypeMap.put("try",Type.TRY);stringTypeMap.put("true",Type.TRUE);
        stringTypeMap.put("void",Type.VOID);stringTypeMap.put("volatile",Type.VOLATILE);stringTypeMap.put("while",Type.WHILE);
        stringTypeMap.put("for",Type.FOR);stringTypeMap.put("extends",Type.EXTENDS);stringTypeMap.put("enum",Type.ENUM);
        stringTypeMap.put("goto",Type.GOTO);
    }
    private int curIndex = 0;
    private static final String[] A_PRIMARY = {"abstract"};
    private static final String[] B_PRIMARY = {"boolean","break","byte"};
    private static final String[] C_PRIMARY = {"case","catch","char","class","continue"};
    private static final String[] D_PRIMARY = {"default","do","double"};
    private static final String[] E_PRIMARY = {"else","extends"};
    private static final String[] F_PRIMARY = {"false","final","finally","float","for"};
    private static final String[] I_PRIMARY = {"if","implements","import","instanceof","int","interface"};
    private static final String[] L_PRIMARY = {"long"};
    private static final String[] N_PRIMARY = {"native","new","null"};
    private static final String[] P_PRIMARY = {"package","private","protected","public"};
    private static final String[] R_PRIMARY = {"return"};
    private static final String[] S_PRIMARY = {"short","static","super","switch","synchronized"};
    private static final String[] T_PRIMARY = {"this","throw","throws","transient","try","true"};
    private static final String[] V_PRIMARY = {"void","volatile"};
    private static final String[] W_PRIMARY = {"while"};
    public List<TypeVal> getContainer() {
        return container;
    }

    private List<TypeVal> container = new ArrayList<>();
    private StringBuffer src;
    public WordPlayer(final StringBuffer src){
        this.src = src;
    }

    public StringBuffer getSrc() {
        return src;
    }

    private boolean addTypeVal(TypeVal tv){
        return container.add(tv);
    }

    private String IsPrimaryWordFirstly(StringBuffer src,int bgIndex,String... primarys){
        for (String primary:primarys) {
            char endCh = src.charAt(bgIndex +primary.length());
            if(src.substring(bgIndex, bgIndex +primary.length()).equals(primary)&&!isLetter(endCh)&&!isNumber(endCh))
                return primary;
        }
        return null;
    }

    public int getCurIndex() {
        return curIndex;
    }
    public boolean isDone(){
        return curIndex==src.length();
    }
    public boolean hasNext() {
        try {
            for (int i = curIndex; i < src.length(); ) {
                char curCh = src.charAt(i);
                if (isEditSign(curCh)) {
                    curIndex = ++i;
                    continue;
                } else if (isLetter(curCh)) {
                    String s = null;
                    switch (curCh) {
                        case 'a':
                            s = IsPrimaryWordFirstly(src, i, A_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'b':
                            s = IsPrimaryWordFirstly(src, i, B_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'c':
                            s = IsPrimaryWordFirstly(src, i, C_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'd':
                            s = IsPrimaryWordFirstly(src, i, D_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'e':
                            s = IsPrimaryWordFirstly(src, i, E_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'f':
                            s = IsPrimaryWordFirstly(src, i, F_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'i':
                            s = IsPrimaryWordFirstly(src, i, I_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'w':
                            s = IsPrimaryWordFirstly(src, i, W_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'n':
                            s = IsPrimaryWordFirstly(src, i, N_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'p':
                            s = IsPrimaryWordFirstly(src, i, P_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'r':
                            s = IsPrimaryWordFirstly(src, i, R_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 's':
                            s = IsPrimaryWordFirstly(src, i, S_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 't':
                            s = IsPrimaryWordFirstly(src, i, T_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'v':
                            s = IsPrimaryWordFirstly(src, i, V_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                        case 'l':
                            s = IsPrimaryWordFirstly(src, i, L_PRIMARY);
                            i += (s != null ? s.length() - 1 : 0);
                            break;
                    }
                    if (s == null) {
                        StringBuffer id = new StringBuffer();
                        i = getIdentityEnd(i + 1, id.append(curCh)) - 1;
                        addTypeVal(new TypeVal(Type.IDEN, id));
                        word.add(id);
                    } else {
                        addTypeVal(new TypeVal(stringTypeMap.get(s), TypeVal.NULL));
                        word.add(new StringBuffer(s));
                    }
                } else if (isNumber(curCh)) {
                    StringBuffer number = new StringBuffer();
                    Object[] rets = getNumberEnd(i + 1, number.append(curCh), false);
                    i = (int) rets[0] - 1;
                    if ((boolean) rets[1])
                        addTypeVal(new TypeVal(Type.FLOAT, Float.parseFloat(number.toString())));
                    else
                        addTypeVal(new TypeVal(Type.INT, Integer.parseInt(number.toString())));
                    word.add(number);
                } else if (isDot(curCh)) {
                    StringBuffer number = new StringBuffer();
                    Object[] rets = getNumberEnd(i + 1, number.append(curCh), true);
                    i = (int) rets[0] - 1;
                    addTypeVal(new TypeVal(Type.FLOAT, Float.parseFloat(number.toString())));
                    word.add(number);
                } else if (curCh == '\'') {
                    StringBuffer chs = new StringBuffer();
                    i = getCharEnd(i + 1, chs, true) - 1;
                    addTypeVal(new TypeVal(Type.CHAR, chs));
                    word.add(chs);
                } else if (curCh == '>') {
                    char next = src.charAt(i + 1);
                    if (next == '=') {
                        addTypeVal(new TypeVal(Type.GE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.GT, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '<') {
                    char next = src.charAt(i + 1);
                    if (next == '=') {
                        addTypeVal(new TypeVal(Type.LE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.LT, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    }
                } else if (curCh == '|') {
                    if (src.charAt(i + 1) == '|') {
                        addTypeVal(new TypeVal(Type.OR, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append('|'));
                    } else {
                        addTypeVal(new TypeVal(Type.BITOR, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '&') {
                    if (src.charAt(i + 1) == '&') {
                        addTypeVal(new TypeVal(Type.AND, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append('&'));
                    } else {
                        addTypeVal(new TypeVal(Type.BITAND, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '+') {
                    char next = src.charAt(i + 1);
                    if (next == '+') {
                        addTypeVal(new TypeVal(Type.DBPLUS, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else if (next == '=') {
                        addTypeVal(new TypeVal(Type.PLUSE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.PLUS, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '-') {
                    char next = src.charAt(i + 1);
                    if (next == '-') {
                        addTypeVal(new TypeVal(Type.DBMINUS, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else if (next == '=') {
                        addTypeVal(new TypeVal(Type.MINUSE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.MINUS, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '*') {
                    char next = src.charAt(i + 1);
                    if (next == '=') {
                        addTypeVal(new TypeVal(Type.MULTIPLYE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.MULTIPLY, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '/') {
                    char next = src.charAt(i + 1);
                    if (next == '=') {
                        addTypeVal(new TypeVal(Type.DIVIDEE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.DIVIDE, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else if (curCh == '=') {
                    char next = src.charAt(i + 1);
                    if (next == '=') {
                        addTypeVal(new TypeVal(Type.EE, TypeVal.NULL));
                        i++;
                        word.add(new StringBuffer(String.valueOf(curCh)).append(next));
                    } else {
                        addTypeVal(new TypeVal(Type.ASSIGN, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                } else {
                    Type type = stringTypeMap.get(String.valueOf(curCh));
                    if (type == null) {
                        return false;
                    } else {
                        addTypeVal(new TypeVal(type, TypeVal.NULL));
                        word.add(new StringBuffer(String.valueOf(curCh)));
                    }
                }
                curIndex = ++i;
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public TypeVal next(){
        return container.get(container.size()-1);
    }
    public String nextString(){
        return word.get(word.size()-1).toString();
    }
    private List<StringBuffer> word = new ArrayList<StringBuffer>();

    private int getCharEnd(int i,StringBuffer schar,boolean isFirst){
        if(i==src.length()){
            return i;
        }
        char ch = src.charAt(i);
        if(ch=='\''){
            return i+1;
        }else {
            if(!isFirst||ch!='\\')
                schar.append(ch);
            return getCharEnd(i+1,schar,false);
        }
    }
    private Object[] getNumberEnd(int i,StringBuffer number,boolean hasDot){
        if(i==src.length()){
            return new Object[]{i,hasDot};
        }
        char ch = src.charAt(i);
        if(isDot(ch)){
//            if(hasDot)
//                return new Object[]{i,hasDot};
            return getNumberEnd(i + 1, number.append(ch),true);
        }else if(isNumber(ch)){
            return getNumberEnd(i + 1, number.append(ch),hasDot);
        }else {
            return new Object[]{i,hasDot};
        }
    }

    private int getIdentityEnd(int i,StringBuffer id){
        if(i==src.length()){
            return i;
        }
        char ch = src.charAt(i);
        if(isLetter(ch)||isNumber(ch)){
            return getIdentityEnd(i + 1, id.append(ch));
        } else {
            return i;
        }
    }
    private boolean isEditSign(char ch){
        return ch==' '||ch=='\t'||ch=='\n'||ch=='\r';
    }
    private boolean isLetter(char ch){
        return (ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||ch=='$'||ch=='_';
    }
    private boolean isDot(char ch){
        return ch=='.';
    }
    private boolean isNumber(char ch){
        return ch>='0'&&ch<='9';
    }
}


class CompilerError extends Exception {

    public CompilerError(String s) {
        super(s);
    }
}