import java.util.*;

public class CronParser {
    public static void main(String[] args) {
        String cron = "*/5 9-17 * * 1-5"; // every 5 min, business hours, weekdays
        parse(cron);
    }

    static void parse(String c){
        String[] p = c.split(" ");
        System.out.println("Minutes: " + expand(p[0],0,59));
        System.out.println("Hours:   " + expand(p[1],0,23));
    }

    static List<Integer> expand(String s,int min,int max){
        List<Integer> out = new ArrayList<>();
        if (s.equals("*")) { for (int i=min;i<=max;i++) out.add(i); return out; }

        for (String part : s.split(",")) {
            if (part.contains("/")) {
                String[] ss = part.split("/");
                int step = Integer.parseInt(ss[1]);
                if (ss[0].equals("*"))
                    for (int i=min;i<=max;i+=step) out.add(i);
                else {
                    int[] r = range(ss[0]);
                    for (int i=r[0];i<=r[1];i+=step) out.add(i);
                }
            }
            else if (part.contains("-")) {
                int[] r = range(part);
                for (int i=r[0];i<=r[1];i++) out.add(i);
            }
            else out.add(Integer.parseInt(part));
        }
        return out;
    }

    static int[] range(String r){
        String[] s = r.split("-");
        return new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};
    }
}
