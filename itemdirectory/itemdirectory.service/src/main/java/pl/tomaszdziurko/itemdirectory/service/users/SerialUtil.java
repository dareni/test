package pl.tomaszdziurko.itemdirectory.service.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author daren
 */
public class SerialUtil {

   public static void doSerialTestIn() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("~/data.in");
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

        Object obj = null;
        while ((obj = in.readObject()) != null) {
            System.out.println(obj.getClass().getCanonicalName());
        }
    }

    public static void doSerialTestOut(Object data) throws IOException {
                System.out.println( "Hello World!" );
//        Data data = new Data();
//        MoreData md = new MoreData();
//        data.md = md;
//        data.md.a = 1l;
//        char key;
        /*
        while ((key = (char)System.in.read()) != 'a') {
            if (key == 'b' ) {
                md.str = "ABCDEFGHIJ";
            }
            if (key == 'c' ) {
                md.str = "ABCDEFGHIJKLMNOPQRST";
            }

            Thread.sleep(1000);
        }
        */
//        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        File file = new File("/home/daren/data.out");
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(data);
        System.out.println(file.length());
        data.toString();
    }
    
}
