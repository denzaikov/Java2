package HomeWorkJava2lesson1;

import java.util.Arrays;
import java.util.Random;

/**
 * 1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
 * Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
 * 2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
 * участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
 * 3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
 * * У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
 * Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 */

public class Main {
    public static void main(String[] args) {

        Traffic[] traffic = {
                new Human("Василий", 3, 2),
                new Robot(),
                new Cat()
        };

        Lets[] lets = {
                new RunningTrack(),
                new Wall()
        };

        Random random = new Random();
        int min = 1;
        int max = 3;

        for (Traffic traffic1 : traffic) {
            boolean result = true;

            for (Lets let : lets) {
                int rand = random.nextInt(max - min + 1) + min;
                if (result) {
                    result = let.run(traffic1, rand);

                }
            }
        }
    }
}