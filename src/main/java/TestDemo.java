import org.springframework.util.Base64Utils;

import java.util.Arrays;

public class TestDemo {
    public static void main(String[] args) {

//        byte[] decode = Base64Utils.decode("emhhbmd6cHR5eUBmb3htYWlsLmNvbQ==".getBytes());
//        System.out.println(new String(decode));

        generateMatrix(3);

    }

    public static int[][] generateMatrix(int n) {

        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int[][] matrix = new int[n][n];
        int num = 1, tar = n * n;

        while (num <= tar) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;

            // 从上到下
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // 从右到左
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            bottom--;

            // 从下到上
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            left++;
        }

        return matrix;
    }
}
