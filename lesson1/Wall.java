package HomeWorkJava2lesson1;

public class Wall implements Lets{
@Override
    public boolean run(Traffic traffic, int height){

       System.out.println("Стена");

      return   traffic.jump(height);

    }
}
