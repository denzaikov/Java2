package Exceptions;


public class Main {

    public static void main(String[] args) {

        String[][] arr = new String[][]{{"5", "5", "5", "5"}, {"2", "2", "2", "2"}, {"1", "1", "1", "1"}, {"3", "s", "3", "3"}};
        try {
                int result = GiveArray(arr);
                System.out.println(result);
        }
        catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int GiveArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}