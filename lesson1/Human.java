package HomeWorkJava2lesson1;

public class Human implements Traffic {

    public Human(String name, int maxDistance, int maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;

    }

    private String name;
    private int maxDistance;
    private int maxHeight;

    @Override
    public boolean run(int distance) {

        if (maxDistance < distance) {
            System.out.println("Человек " + name + " не смог прыгнуть");
            return false;

        }
        System.out.println("Человек " + name + " бегает");

        return true;
    }

    @Override
    public boolean jump(int height) {

        if (maxHeight < height)
        {
            System.out.println("Человек " + name + " не смог прыгнуть");

            return false;

        }
        System.out.println("Человек " + name+" прыгает");

        return true;
    }
}