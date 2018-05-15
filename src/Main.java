import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String FILE_NAME = "data-1525256115650.csv";

    public static void main(String[] args) throws Exception {

        extractKmlAdded();

//        extractGeoms();
    }

    private static void extractKmlAdded() throws IOException {
        List<KmlAddedObject> objects = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

        String sCurrentLine;

        boolean skipFirstLine = true;
        while ((sCurrentLine = br.readLine()) != null) {
            if (skipFirstLine) {
                skipFirstLine = false;
                continue;
            }
            String [] strings = sCurrentLine.split(",");
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].replace("\"", "");
            }
            objects.add(new KmlAddedObject(Long.valueOf(strings[0]), strings[1]));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"))) {
            for (KmlAddedObject object : objects) {
                String possibleDate = "";
                if (object.getKmlAdded().length() > 14) {
                    possibleDate = object.getKmlAdded().substring(object.getKmlAdded().length()-14, object.getKmlAdded().length()-4);
                } else {
                    continue;
                }
                if (!(possibleDate.charAt(4) == '-' && possibleDate.charAt(7) == '-')) {
                    continue;
                }
                String content = "update parking_area set kml_added = '" + possibleDate + "' where id = " + object.getAreaId() + ";";
                bw.write(content);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractGeoms() throws IOException {
        List<GeomObject> objects = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

        String sCurrentLine;

        boolean skipFirstLine = true;
        while ((sCurrentLine = br.readLine()) != null) {
            if (skipFirstLine) {
                skipFirstLine = false;
                continue;
            }
            String [] strings = sCurrentLine.split(",");
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].replace("\"", "");
            }
            objects.add(new GeomObject(Long.valueOf(strings[0]), "'" + strings[1] + "'", Double.valueOf(strings[2]), "'" + strings[3] + "'"));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"))) {
            for (GeomObject object : objects) {
                String content = "insert into geom (area_id, geom, area_in_meters, point_on_surface) values (";
                content += object.getAreaId() + ",";
                content += object.getGeom() + ",";
                content += object.getAreaInMeters() + ",";
                content += object.getPointOnSurface() + ");";
                bw.write(content.replace("''", "null"));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
