import java.util.*;
import java.util.stream.*;

public class MatrixStatistics {

    public static void main(String[] args){

        int[][] matrix=
            {
                {1,2,3},
                {4,5,6},
                {7,8,9}
            };

        IntSummaryStatistics stats=

            Arrays.stream(matrix)
                  .flatMapToInt(
                      Arrays::stream
                  )
                  .summaryStatistics();

        System.out.println(stats);
    }
}
