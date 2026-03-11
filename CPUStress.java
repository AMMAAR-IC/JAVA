public class CPUStress {
    public static void main(String[] args) {

        while(true){
            double x = Math.random();
            for(int i=0;i<1000000;i++){
                x = Math.sqrt(x*i);
            }
        }

    }
}
