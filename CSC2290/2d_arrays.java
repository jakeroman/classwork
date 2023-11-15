// 2D Array Processing Jake Roman
import java.util.*;
public class Arrays {
    public static void main(String[] args) {
        // create 2d array
        int sizeX = 3;
        int sizeY = 3;
        int[][] array = {{150,130,110},{200,190,210},{80,100,90}};

        // populate 2d array
        //Random rng = new Random();
        //int a = 1;
        //for (int x = 0; x < array.length; x++) {
        //    for (int y = 0; y < array[x].length; y++) {
        //        array[x][y] = a;//rng.nextInt(0,99);
        //        a++;
        //    }
        //}

        // display 2d array
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                System.out.print(array[x][y]+"\t");
            }
            System.out.println();
        }

        // summing array elements
        int sum = 0;
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                sum += array[x][y];
            }
        }
        System.out.println("SUM: "+sum);

        // summing array elements by column
        int col_sum[] = new int[array[0].length];
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                col_sum[y] += array[x][y];
            }
        }
        System.out.print("COLUMN SUM: ");
        for (int i = 0; i < col_sum.length; i++) {
            System.out.print(col_sum[i]+" ");
        }
        System.out.println();

        // summing array elements by rows
        int row_sum[] = new int[array[0].length];
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                row_sum[x] += array[x][y];
            }
        }
        System.out.print("ROW SUM: ");
        for (int i = 0; i < row_sum.length; i++) {
            System.out.print(row_sum[i]+" ");
        }
        System.out.println();

        // find largest row
        int largestRow = 0;
        int largestRowValue = 0;
        for (int i = 0; i < row_sum.length; i++) {
            if (row_sum[i] > largestRowValue) {
                largestRow = i; 
                largestRowValue = row_sum[i];
            }
        }
        System.out.print("\nLARGEST ROW: #"+(largestRow+1)+" WITH A SUM OF "+largestRowValue);
    }
}
