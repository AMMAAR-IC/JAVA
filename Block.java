import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

class Block {
    public String hash;
    public String previousHash;
    private String data; // your transaction data
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return applySha256(
            previousHash +
            Long.toString(timeStamp) +
            Integer.toString(nonce) +
            data
        );
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); 
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
            byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
            StringBuffer hexString = new StringBuffer(); 
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class SimpleBlockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 4;

    public static void main(String[] args) {
        System.out.println("Mining block 1...");
        blockchain.add(new Block("First transaction", "0"));
        blockchain.get(0).mineBlock(difficulty);

        System.out.println("Mining block 2...");
        blockchain.add(new Block("Second transaction", blockchain.get(blockchain.size()-1).hash));
        blockchain.get(1).mineBlock(difficulty);

        System.out.println("Mining block 3...");
        blockchain.add(new Block("Third transaction", blockchain.get(blockchain.size()-1).hash));
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid: " + isChainValid());
        
        for (int i = 0; i < blockchain.size(); i++) {
            System.out.println("\nBlock " + i + " Data: " + blockchain.get(i).hash);
        }
    }

    public static Boolean isChainValid() {
        Block currentBlock; 
        Block previousBlock;
        
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");			
                return false;
            }
            
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
