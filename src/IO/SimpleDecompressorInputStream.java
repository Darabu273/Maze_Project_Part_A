package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * decompress maze by the given method of this task
 */

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() {
        return 0;
    }

    public int read(byte[] byteArray){//read the meta data of the maze and remove the data from the compression
        try{
            byte[] contents = in.readAllBytes();
            int i;
            int currentByte = 1;
            for (i = 0; i < 12; i++) { // copy all the 12 chars (meta data) to new byte array as is.
                byteArray[i] = contents[i];
            }
            for (int j = 12; j < contents.length; j++) { // open the compressor of the content maze
                currentByte = 1 - currentByte; // We will reverse the current char 0/1
                int counter = contents[j];
                if (contents[j] < 0) {//the meaning that the counter in contents[j] between 128-255
                    counter = counter + 256;
                }
                for (int k = 0; k < counter; k++) {
                    byteArray[i] = (byte) currentByte;
                    i++;
                }

            }} catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


}