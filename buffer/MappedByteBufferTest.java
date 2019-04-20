import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        File file = new File("D://data.txt");

        if(!file.exists()){
            file.createNewFile();
        }

        long len = file.length();
        byte[] ds = new byte[(int) len];
 
        MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                .getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, len);
        for (int offset = 0; offset < len; offset++) {
            byte b = mappedByteBuffer.get();
            ds[offset] = b;
        }

        Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scan.hasNext()) {
            System.out.print(scan.next() + " ");
        }
    }
}
