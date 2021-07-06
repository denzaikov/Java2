package HomeWorkJava2lesson1;

public class Cat implements Traffic{

    private int maxDistance = 2;
    private int maxHeight = 2;

    @Override
    public boolean run(int distance) {

        if (maxDistance < distance)
        {
            System.out.println("Кот не смог прыгнуть");
            return false;
        }

        System.out.println("Кот бегает");

        return true;
    }

    @Override
    public boolean jump(int height) {

        if (maxHeight < height)
        {
            System.out.println("Кот не смог прыгнуть");

            return false;
        }

        System.out.println("Кот прыгает");

        return true;
    }
}
