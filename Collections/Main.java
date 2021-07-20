package Collections;
import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
 * из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 */

public class Main {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>(25);
        arrayList.add("Бобик");
        arrayList.add("Бобик");
        arrayList.add("Васька");
        arrayList.add("Тузик");
        arrayList.add("Кузя");
        arrayList.add("Кокос");
        arrayList.add("Мася");
        arrayList.add("Софа");
        arrayList.add("Рубик");
        arrayList.add("Кубик");
        arrayList.add("Кубик");
        arrayList.add("Васька");
        arrayList.add("Кузя");
        arrayList.add("Леонардо");
        arrayList.add("Сплинтер");

        System.out.println("Элементов в созданном массиве: " + arrayList.size());
//        System.out.println(arrayList);

        HashMap<String, Integer> test = new HashMap<>();

        for (String s : arrayList) {

            if (test.containsKey(s))
            {
                int count = test.get(s) + 1;
                test.put(s, count);
            }

            else {
                test.put(s, 1);
            }
        }
        System.out.println(test);


        Set<String> list = new HashSet<>(arrayList);
        System.out.println("Уникальных слов: " + list.size());
        System.out.println(list);

        System.out.println();
        System.out.println("Задание №2");
        System.out.println();

        Information Information = new Information();

        Information.add("zaikov", "435522");
        Information.add("petrov", "455122");
        Information.add("sidorov", "487744");
        Information.add("kyznecov", "432211");
        Information.add("klimov", "548712");
        Information.add("zaikov", "658741");
        Information.add("vasiliev", "325487");
        Information.add("popov", "986547");
        Information.add("popov", "657482");

        System.out.println("zaikov: = " + Information.get("zaikov"));
        System.out.println("popov: = " + Information.get("popov"));
        System.out.println("klimov: = " + Information.get("klimov"));
        System.out.println("petrov: = " + Information.get("petrov"));

    }
}
