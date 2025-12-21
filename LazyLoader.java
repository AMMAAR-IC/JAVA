public class LazyLoader {
    static class Heavy {
        Heavy() { System.out.println("Loaded"); }
    }

    static Heavy h;

    static Heavy get() {
        if (h == null) h = new Heavy();
        return h;
    }

    public static void main(String[] args) {
        System.out.println("Before");
        get();
    }
}
