import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String pathZip = "F:\\Game\\savegames\\archive_save.zip";
        String pathUnZip = "F:\\Game\\savegames\\save_1.dat";

        openZip(pathZip, pathUnZip);
        openProgress(pathUnZip);

    }
    public static void openZip(String pathZip, String pathUnZip){
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathZip))){
            ZipEntry entry;
            String name2;
            while ((entry = zin.getNextEntry()) != null) {
                name2 = entry.getName();
                if (name2.equals(pathUnZip)) {
                    FileOutputStream fout = new FileOutputStream(name2);
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    zin.closeEntry();
                    fout.close();
                }
            }

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void openProgress(String pathFile){
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathFile);
             ObjectInputStream ois = new ObjectInputStream(fis)){
             gameProgress = (GameProgress) ois.readObject();
    } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}