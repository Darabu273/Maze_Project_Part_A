package algorithms.IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * compress maze in our method (described in comments) -
 * each 8 cells (bits of 0 and 1) in the maze will be hold in one byte in the byte array of the maze
 */
public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    //empty - must override the method
    public void write(int b) {
    }
    @Override
    //maze content compress method: compress blocks of 8-bits of 1&0 values (one byte)
    //save the decimal values of those 8-bits in one byte (the max value will be 255), and than convert again to 8-bits when decompressing
    public void write(byte[] b) {
        byte[] bytes = new  byte[13 + ((b.length-12)/8)]; //the result array needs 12 cells for meta data + maze size divided by 8 cells for the maze content (8 cells turn to 1 byte) +1 for reminder -> ((b.length-12)/8
        int i;
        for (i = 0; i < 12; i++) { //copy the values of - rows, columns, start position and goal position as is to new byte array.
            bytes[i] =(b[i]);
        }
        try{
            out.write(bytes,0,12); //write the meta data of the maze to the file

            //iterate over all bytes in b array, convert each 8-bits to one byte by using these 8-bits decimal-value
            String temp = ""; //will hold 8 bits each time
            int counter =0;
            for (int j = 12; j < b.length; j++) {
                temp += b[j];
                counter++;
                if(j == b.length-1){
                    int tempInt = Integer.parseInt(temp, 2); //Parses the string argument as a signed integer in the radix specified by the second argument.
                    bytes[i]= (byte)tempInt;
                    out.write(bytes,i,1); //write the meta data of the maze to the file
                }
                else if (counter==8){
                    int tempInt = Integer.parseInt(temp, 2); //Parses the string argument as a signed integer in the radix specified by the second argument.
                    bytes[i]= (byte)tempInt;
                    out.write(bytes,i,1); //write the meta data of the maze to the file
                    i++;
                    temp = "";
                    counter=0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
