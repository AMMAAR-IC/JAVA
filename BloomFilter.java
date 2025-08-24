import java.util.*;

public class BloomFilter {
    private final BitSet bitset;
    private final int size, hashCount;

    public BloomFilter(int size, int hashCount) {
        this.size = size;
        this.hashCount = hashCount;
        this.bitset = new BitSet(size);
    }

    private int[] hashes(String s) {
        int[] res = new int[hashCount];
        int h1 = s.hashCode();
        int h2 = Objects.hash(s, 1234567);
        for (int i = 0; i < hashCount; i++)
            res[i] = Math.abs((h1 + i * h2) % size);
        return res;
    }

    public void add(String s) {
        for (int h : hashes(s)) bitset.set(h);
    }

    public boolean mightContain(String s) {
        for (int h : hashes(s)) if (!bitset.get(h)) return false;
        return true;
    }

    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter(1000, 3);
        bf.add("hello"); bf.add("world");
        System.out.println(bf.mightContain("hello")); // true
        System.out.println(bf.mightContain("java"));  // maybe false
    }
}
