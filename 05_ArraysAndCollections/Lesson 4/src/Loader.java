import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Loader {
    public static void main(String[] args) throws IOException {


        TreeMap<String, String> handbook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        boolean keyOrV;

        System.out.println("HANDBOOK");

        for (;;) {
            System.out.print("search: ");
            String record = scanner.nextLine().trim();
            keyOrV = record.matches("(.*)[A-Za-zА-Яа-я](.*)");

            if (record.equals("LIST")) {
                printMap(handbook);
                continue;
            }

            if (handbook.containsKey(record)) {
                System.out.println(" name: " + handbook.get(record));
                continue;
            }

            if (handbook.containsValue(record)) {
                findName(handbook, record);
                continue;
            }

            if (keyOrV) {
                System.out.print("Type the phone number for this name: ");
                String phoneNumber = scanner.nextLine().trim();
                handbook.put(phoneNumber, record);
            } else {
                System.out.print("Type a name for this phone number: ");
                String name = scanner.nextLine().trim();
                handbook.put(record, name);
            }
        }
    }

    private static void printMap(Map<String, String> map) {

        for (String key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }

    private static void findName(Map<String, String> map, String record) {

        for (String key : map.keySet()) {
            if (map.get(key).equals(record)) {
                System.out.println(" phone: " + key);
            }
        }
    }
}
