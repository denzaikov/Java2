package Exceptions;

public class MyArrayDataException extends NumberFormatException{

    MyArrayDataException(int i, int j) {super("Неправильное значение массива! \nОшибка в ячейке: " + i + "x" + j);}
}