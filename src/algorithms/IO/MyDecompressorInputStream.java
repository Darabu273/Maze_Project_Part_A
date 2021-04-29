package algorithms.IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream fileInputStream) {
        this.in = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] byteArray) throws IOException {//read the meta data of the maze and remove the data from the compression
        byte[] contents = in.readAllBytes();
        int i;
        for (i = 0; i < 12; i++) { // copy all the 12 chars (meta data) to new byte array as is.
            byteArray[i] = contents[i];
        }
        for (int j = 12; j < contents.length-1; j++) { // open the compressor of the content maze
            //convert this curr number to 8-bits (binary value)
            int currVal = contents[j];
            if(currVal<0){ //the meaning that the counter in contents[j] between 128-255
                currVal=currVal+256;
            }
            int [] binaryVal = decimalToBinary(currVal); //convert the int value to "8-bits" (binary number)
            for (int k = 0; k < 8; k++) { //add binary number to  the byteArray
                byteArray[i]=(byte)binaryVal[k];
                i++;
            }
        }

        //add the last byte (last block of cells) to the byte array (might be less than 8, so we have to check the reminder of the size from 8)
        String row ="";
        row+= byteArray[0] + byteArray[1];
        int Row = Integer.parseInt(row, 10);
        String col ="";
        col+= byteArray[2] + byteArray[3];
        int Col = Integer.parseInt(col, 10);
        int remainder = ((Row*Col)%8); //this will be the number of bits of the last block of cells
        int val = contents[contents.length-1];
        if(val < 0){
            val=val+256;
        }
        int [] binaryVal = decimalToBinary(val); //convert the int value to "8-bits" (binary number)
        for (int k = (8-remainder); k < 8; k++) {
            byteArray[i]=(byte) binaryVal[k];
            i++;
        }

        return 0;
    }

    //convert the given decimal number to array of ints of this number binary representation
    static int[] decimalToBinary(int number)
    {
        int[] binary = new int[8]; //store binary number
        int i = 0;
        while (number > 0) {
            binary[i] = number % 2; // store the remainder in the binary result array
            number = number / 2;
            i++;
        }
        int [] binaryReverse = new int[8];
        for (int j = 0; j < 8; j++) {
            binaryReverse[j] = binary[7-j];
        }
        return binaryReverse;
    }
}
