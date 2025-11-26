// HuffmanCompressor.java
import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int freq; int b; Node left,right;
    Node(int b,int f){this.b=b;this.freq=f;}
    Node(Node l,Node r){this.left=l;this.right=r;this.freq=l.freq+r.freq; this.b=-1;}
    public int compareTo(Node o){return Integer.compare(freq,o.freq);}
    boolean isLeaf(){return left==null && right==null;}
}

public class HuffmanCompressor {
    static Map<Integer,String> table;
    public static void compress(String in, String out) throws Exception {
        byte[] data = Files.readAllBytes(java.nio.file.Path.of(in));
        int[] freq = new int[256];
        for(byte x: data) freq[x & 0xFF]++;
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int i=0;i<256;i++) if(freq[i]>0) pq.add(new Node(i,freq[i]));
        if(pq.isEmpty()) return;
        while(pq.size()>1) pq.add(new Node(pq.poll(), pq.poll()));
        Node root = pq.poll();
        table = new HashMap<>();
        buildTable(root, "");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(out))) {
            dos.writeInt(data.length);
            writeTree(dos, root);
            StringBuilder bits = new StringBuilder();
            for (byte x : data) bits.append(table.get(x & 0xFF));
            int pad = (8 - bits.length()%8) % 8;
            dos.writeByte(pad);
            for (int i=0;i<bits.length();i+=8) {
                int end = Math.min(i+8, bits.length());
                String byteStr = bits.substring(i,end);
                if (byteStr.length()<8) byteStr += "0".repeat(8-byteStr.length());
                dos.writeByte(Integer.parseInt(byteStr,2));
            }
        }
        System.out.println("Compressed -> " + out);
    }

    static void writeTree(DataOutputStream dos, Node n) throws IOException {
        if (n.isLeaf()) { dos.writeByte(1); dos.writeByte(n.b); return; }
        dos.writeByte(0); writeTree(dos, n.left); writeTree(dos, n.right);
    }

    static Node readTree(DataInputStream dis) throws IOException {
        int flag = dis.readByte();
        if (flag==1) return new Node(dis.readUnsignedByte(), 0);
        Node l = readTree(dis); Node r = readTree(dis); return new Node(l,r);
    }

    static void buildTable(Node n, String s){
        if (n.isLeaf()) { table.put(n.b & 0xFF, s.length()==0?"0":s); return; }
        buildTable(n.left, s+"0"); buildTable(n.right, s+"1");
    }

    public static void decompress(String in, String out) throws Exception {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(in))) {
            int origLen = dis.readInt();
            Node root = readTree(dis);
            int pad = dis.readByte();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int val;
            StringBuilder bits = new StringBuilder();
            while ((val = dis.read()) != -1) bits.append(String.format("%8s", Integer.toBinaryString(val & 0xFF)).replace(' ', '0'));
            if (pad>0) bits.setLength(bits.length()-pad);
            Node cur = root;
            for (int i=0; i<bits.length() && baos.size()<origLen; i++){
                cur = bits.charAt(i)=='0' ? cur.left : cur.right;
                if (cur.isLeaf()){ baos.write(cur.b); cur = root; }
            }
            Files.write(java.nio.file.Path.of(out), baos.toByteArray());
            System.out.println("Decompressed -> " + out);
        }
    }

    public static void main(String[] args) throws Exception {
        // Usage: compress file -> java HuffmanCompressor compress in out
        if (args.length<3) { System.out.println("Usage: compress|decompress in out"); return;}
        if (args[0].equals("compress")) compress(args[1], args[2]);
        else decompress(args[1], args[2]);
    }
}
