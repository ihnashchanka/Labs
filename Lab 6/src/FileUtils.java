import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tatsiana on 07.11.2016.
 */
public class FileUtils {
    private static final String POEM = "poem.txt";
    private static String DirectoryPath = "";
    private static ArrayList<String> files = new ArrayList<String>();
    private static ArrayList<String> poem = new ArrayList<String>();

    public static void main(String[] args) {
        getData();
        createFiles();
        readPoem();
        writePoem();
    }

    private static void getData() {
        try {
            BufferedReader fr = new BufferedReader(new FileReader("root.txt"));
            DirectoryPath = fr.readLine();
            DirectoryPath += File.separator + File.separator + fr.readLine();
            fr = new BufferedReader(new FileReader("files.txt"));

            while (fr.ready()) {
                String buffer = fr.readLine();
                String ch = "/";
                buffer = buffer.replaceAll(ch, File.separator + File.separator + File.separator + File.separator);
                files.add(buffer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (String str : files) {
            System.out.println(str);
        }
    }

    private static void createFiles() {
        for (String file : files) {
            try {
                String buffer = DirectoryPath + file;
                File path = new File(buffer);
                System.out.println(path.getName());
                path.getParentFile().mkdirs();
                if (path.getName().contains("."))
                    path.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void readPoem() {
        try {
            int curChar;
            BufferedReader br = new BufferedReader(new FileReader(POEM));
            while ((curChar = br.read()) != -1) {
                poem.add((char) curChar + br.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writePoem() {
        int rootLevel = 0;
        Path dir = Paths.get(DirectoryPath);
        for (Path name : dir) rootLevel++;
        for (String file : files) {
            try {
                FileWriter fw = new FileWriter(DirectoryPath + file);
                int line = 0;
                Path path = Paths.get(DirectoryPath + file);
                for (Path name : path) line++;
                fw.write("Level: " + (line - rootLevel) + "\n");
                fw.write("Directory: " + DirectoryPath + file + "\n");
                fw.write(poem.get(line - rootLevel - 1)+ "\n");
                fw.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
