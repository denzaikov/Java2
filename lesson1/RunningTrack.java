package HomeWorkJava2lesson1;

public class RunningTrack implements Lets{
@Override
    public boolean run(Traffic traffic, int distance){

       System.out.println("Беговая дорожка");
       return traffic.run(distance);

    }
}
