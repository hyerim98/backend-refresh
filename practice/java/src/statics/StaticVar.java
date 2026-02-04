package statics;

public class StaticVar {
    public String name;
    public static int count;

    public StaticVar(String name) {
        this.name = name;
        count++;
    }
}
