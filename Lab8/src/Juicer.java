import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Tatsiana on 02.12.2016.
 */
public class Juicer {
    private static ArrayList<String> fruits = new ArrayList<String>();
    private static ArrayList<ArrayList<String>> juices = new ArrayList<>();

    public static void main(String[] args) {
        try {
            getData();
            writeFile("juice1.out");
            sort(fruits);
            writeFile("juice2.out");
            writeThird();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("juice.in"));
        String line;
        while (br.ready()) {
            line = br.readLine();
            Scanner sc = new Scanner(line);
            ArrayList<String> newJuice = new ArrayList<>();
            while (sc.hasNext()) {
                String buffer = sc.next();
                if (!fruits.contains(buffer)) {
                    fruits.add(buffer);
                }
                newJuice.add(buffer);
            }
            sort(newJuice);
            juices.add(newJuice);
            sortJuices();
        }
    }

    private static void writeFile(String file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (String item : fruits) {
                fw.write(item + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeThird() {
        try {
            FileWriter fw = new FileWriter("juice3.out");
            Integer result = count();
            fw.write(result.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sort(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
    }

    private static void sortJuices() {
        Collections.sort(juices, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> a1, ArrayList<String> a2) {
                return a1.get(0).compareToIgnoreCase(a2.get(0));
            }
        });
        Collections.sort(juices, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> a1, ArrayList<String> a2) {
                return a1.size() - a2.size();
            }
        });
    }

    public static int count() {
        int count = 0;
        Iterator<ArrayList<String>> it = juices.iterator();
        while (!juices.isEmpty()) {
            ArrayList<String> newJuice = juices.get(0);
            juices.remove(0);
            it = juices.iterator();
            while (it.hasNext()) {
                ArrayList<String> juice = new ArrayList<>();
                juice = it.next();
                boolean contents = true;
                for (String fruit : newJuice) {
                    if (!juice.contains(fruit))
                        contents = false;
                }
                if (contents) {
                    it.remove();
                    newJuice = juice;
                }
            }
            count++;
        }
        return count;
    }
}
