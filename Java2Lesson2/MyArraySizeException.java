package Exceptions;

public class MyArraySizeException extends IndexOutOfBoundsException{

    public MyArraySizeException() {
        super("Размер массива должен быть 4x4");
    }
}
