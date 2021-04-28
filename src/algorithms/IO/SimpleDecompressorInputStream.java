package algorithms.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream fileInputStream) {
        this.in = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] byteArray) throws IOException {//read the meta data of the maze and remove the data from the compression
        byte[] contents = in.readAllBytes();
        int i;
        int currentByte = 1;
        for (i = 0; i < 24; i++) { // copy all the 24 chars (meta data) to new byte array as is.
            byteArray[i] = contents[i];
        }
        for (int j = 24; j < contents.length; j++) { // open the compressor of the content maze
            currentByte = 1 - currentByte; // We will reverse the current char 0/1
            for (int k = 0; k < contents[j]; k++) {
                byteArray[i] =(byte) currentByte;
                i++;
            }
        }
        //System.out.println(Arrays.toString(byteArray));
        return 0;
    }


}
