// TinyRayTracer.java
import java.io.*;
import java.util.*;

public class TinyRayTracer {
    static class Vec {
        double x,y,z;
        Vec(double x,double y,double z){this.x=x;this.y=y;this.z=z;}
        Vec add(Vec o){return new Vec(x+o.x,y+o.y,z+o.z);}
        Vec sub(Vec o){return new Vec(x-o.x,y-o.y,z-o.z);}
        Vec mul(double s){return new Vec(x*s,y*s,z*s);}
        double dot(Vec o){return x*o.x+y*o.y+z*o.z;}
        Vec norm(){double l=Math.sqrt(dot(this)); return new Vec(x/l,y/l,z/l);}
    }

    static class Sphere {
        Vec c; double r; int color;
        Sphere(Vec c,double r,int color){this.c=c;this.r=r;this.color=color;}
        // returns t or -1
        double intersect(Vec ro, Vec rd){
            Vec oc = ro.sub(c);
            double b = oc.dot(rd);
            double c = oc.dot(oc) - r*r;
            double disc = b*b - c;
            if (disc < 0) return -1;
            double t = -b - Math.sqrt(disc);
            if (t > 1e-4) return t;
            t = -b + Math.sqrt(disc);
            return t > 1e-4 ? t : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        int W = 400, H = 300;
        Vec cam = new Vec(0,0,0);
        List<Sphere> scene = Arrays.asList(
            new Sphere(new Vec(0, -1, 3), 1, 0xFF0000),
            new Sphere(new Vec(2, 0, 4), 1, 0x00FF00),
            new Sphere(new Vec(-2, 0, 4), 1, 0x0000FF),
            new Sphere(new Vec(0, -5001, 0), 5000, 0xAAAAAA) // ground
        );
        Vec light = new Vec(5, 5, -10);

        try (PrintWriter out = new PrintWriter(new FileWriter("out.ppm"))) {
            out.println("P3");
            out.println(W + " " + H);
            out.println("255");
            for (int y = 0; y < H; y++) {
                for (int x = 0; x < W; x++) {
                    double nx = (2*(x+0.5)/ (double)W -1)* (W/(double)H);
                    double ny = 1 - 2*(y+0.5)/(double)H;
                    Vec dir = new Vec(nx, ny, 1).norm();
                    int pixel = trace(cam, dir, scene, light);
                    int r = (pixel >> 16) & 0xFF;
                    int g = (pixel >> 8) & 0xFF;
                    int b = pixel & 0xFF;
                    out.printf("%d %d %d ", r, g, b);
                }
                out.println();
            }
        }
        System.out.println("Rendered -> out.ppm (open with an image viewer or convert)");
    }

    static int trace(Vec ro, Vec rd, List<Sphere> scene, Vec light) {
        double tmin = Double.POSITIVE_INFINITY; Sphere hit = null;
        for (Sphere s : scene) {
            double t = s.intersect(ro, rd);
            if (t > 0 && t < tmin) { tmin = t; hit = s; }
        }
        if (hit == null) return 0x87CEEB; // sky color

        Vec hitPos = ro.add(rd.mul(tmin));
        Vec normal = hitPos.sub(hit.c).norm();
        Vec toLight = light.sub(hitPos).norm();
        double diff = Math.max(0, normal.dot(toLight));
        // simple shadow check
        boolean inShadow = false;
        for (Sphere s : scene) {
            if (s == hit) continue;
            double t = s.intersect(hitPos.add(normal.mul(1e-4)), toLight);
            if (t > 0) { inShadow = true; break; }
        }
        double ambient = 0.15;
        double shade = ambient + (inShadow ? 0 : 0.85*diff);
        int r = (int)(((hit.color >> 16) & 0xFF) * shade);
        int g = (int)(((hit.color >> 8) & 0xFF) * shade);
        int b = (int)((hit.color & 0xFF) * shade);
        return (clamp(r)<<16) | (clamp(g)<<8) | clamp(b);
    }

    static int clamp(int v){ return Math.max(0, Math.min(255, v)); }
}
