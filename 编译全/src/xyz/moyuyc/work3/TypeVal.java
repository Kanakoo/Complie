package xyz.moyuyc.work3;

/**
 * Created by Yc on 2016/3/14 for myCompiler.
 */
public class TypeVal<T>{
    public static final Object NULL = null;
    Type type;
    T value;
    public TypeVal(){}
    public TypeVal(Type type, T value) {
        this.type = type;
        this.value = value;
    }



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeVal)) return false;

        TypeVal<?> typeVal = (TypeVal<?>) o;

        if (type != typeVal.type) return false;
        return !(value != null ? !value.equals(typeVal.value) : typeVal.value != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "("+type+","+value+")";
    }
}

