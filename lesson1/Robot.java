package HomeWorkJava2lesson1;

public class Robot implements Traffic{


    private int maxDistance = 1;
    private int maxHeight = 1;


    @Override
    public boolean run(int distance) {

        if (maxDistance < distance)
        {
            System.out.println("Робот не смог прыгнуть");
            return false;

        }
        System.out.println("Робот бегает");

        return true;
    }
    @Override
    public boolean jump(int height) {

        if (maxHeight < height)
        {
            System.out.println("Робот не смог прыгнуть");
            return false;

        }
        System.out.println("Робот прыгает");

        return true;
    }
}
