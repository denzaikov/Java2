package Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Information {

    private Map<String, List<String>> users = new HashMap<>();
    private List<String> phoneNumber;


    public void add(String surname, String phone_number) {

              if (users.containsKey(surname))
              {
            phoneNumber = users.get(surname);
            phoneNumber.add(phone_number);
            users.put(surname, phoneNumber);
              }

              else
              {
            phoneNumber = new ArrayList<>();
            phoneNumber.add(phone_number);
            users.put(surname, phoneNumber);
              }
    }

    public List<String> get(String surname) {
        return users.get(surname);

         }
    }