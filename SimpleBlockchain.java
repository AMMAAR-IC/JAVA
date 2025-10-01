// SimpleBlockchain.java
import java.util.*;
import java.security.MessageDigest;

public class SimpleBlockchain {
    static class Block {
        public int index;
        public long timestamp;
        public String data;
        public String prevHash;
        public String hash;
        public int nonce;

        Block(int index, String data, String prevHash) {
            this.index = index;
            this.timestamp = System.currentTimeMillis();
            this.data = data;
            this.prevHash = prevHash;
            this.nonce = 0;
            this.hash = calculateHash();
        }

        String calculateHash() {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                String txt = index + Long.toString(timestamp) + data + prevHash + nonce;
                byte[] digest = md.digest(txt.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) sb.append(String.format("%02x", b));
                return sb.toString();
            } catch (Exception e) { throw new RuntimeException(e); }
        }

        void mine(int difficulty) {
            String target = "0".repeat(difficulty);
            while (!hash.substring(0, difficulty).equals(target)) {
                nonce++;
                hash = calculateHash();
            }
            System.out.println("Mined block: " + hash);
        }
    }

    public static void main(String[] args) {
        List<Block> chain = new ArrayList<>();
        int difficulty = 4;

        Block genesis = new Block(0, "Genesis", "0");
        genesis.mine(difficulty);
        chain.add(genesis);

        Block b1 = new Block(1, "Alice pays Bob 10", chain.get(chain.size()-1).hash);
        b1.mine(difficulty);
        chain.add(b1);

        Block b2 = new Block(2, "Bob pays Carol 5", chain.get(chain.size()-1).hash);
        b2.mine(difficulty);
        chain.add(b2);

        System.out.println("Chain valid? " + isChainValid(chain));
    }

    static boolean isChainValid(List<Block> chain) {
        for (int i = 1; i < chain.size(); i++) {
            Block cur = chain.get(i);
            Block prev = chain.get(i-1);
            if (!cur.hash.equals(cur.calculateHash())) return false;
            if (!cur.prevHash.equals(prev.hash)) return false;
        }
        return true;
    }
}
