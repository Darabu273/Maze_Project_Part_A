package algorithms.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream fileInputStream) {
        //todo: change for 2 bytes instead of four for meta data?
        this.in = fileInputStream;
    }

    @Override
    public int read() {
        return 0;
    }

    public int read(byte[] byteArray) throws IOException {//read the meta data of the maze and remove the data from the compression
        byte[] contents = in.readAllBytes();
        System.out.println(contents.length-24); //check the compressor
        int i;
        int currentByte = 1;
        for (i = 0; i < 24; i++) { // copy all the 24 chars (meta data) to new byte array as is.
            byteArray[i] = contents[i];
        }
        for (int j = 24; j < contents.length; j++) { // open the compressor of the content maze
            currentByte = 1 - currentByte; // We will reverse the current char 0/1
            int counter = contents[j];
            if (contents[j] < 0) {//the meaning that the counter in contents[j] between 128-255
                counter = counter + 256;
            }
            for (int k = 0; k < counter; k++) {
                byteArray[i] = (byte) currentByte;
                i++;
            }

        }
        //System.out.println(i);
        return 0;
    }


}
