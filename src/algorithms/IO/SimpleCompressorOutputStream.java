package algorithms.IO;

import java.io.*;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream fileOutputStream) {
        this.out = fileOutputStream;
    }

    @Override
    public void write(int b) throws IOException { }

    @Override
    public void write(byte[] b) throws IOException {
        //todo: check/change to array that contains one byte for content cell !
        //todo: change for 2 bytes instead of four for meta data?

        byte[] bytes = new  byte[b.length];
        int i;
        for (i = 0; i < 24; i++) { //copy the values of - rows, columns, start position and goal position as is to new byte array.
            bytes[i] =(b[i]);
        }
        out.write(bytes,0,24);
        out.flush();
        /*BufferedReader br = new BufferedReader(new FileReader("savedMaze.maze"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }*/
        int counter=0;
        int prev = 0; // prev position = we will start with zero
        for (int j = 24; j <b.length ; j++) { //will do different manipulate of write
            if (prev == b[j]){
                counter++;
            }
            else{
                int target = counter;
                prev = 1-prev;//will change to 1 if prev was 0 and the same action to the opposite option.
                if (counter > 255) // when counter bigger than 255 we will separate the num of the current byte with zero between the values.
                {
                   while (target>255){
                       bytes[i] = (byte)255;
                       out.write(bytes,i,1); // write to out the counter for this byte
                       target= target-255;
                       i++;
                       bytes[i] = 0; //separate the values - because the maximum value can be 255.
                       out.write(bytes,i,1);// write to out the counter for this byte
                       i++;
                   }
                    bytes[i] =(byte)target; //remainder
                }else{ // if the counter less then 255 we can represent in one byte.
                    bytes[i] =(byte)counter;
                    out.write(bytes,i,1);}// write to out the counter for this byte
                i++;
                counter = 0;
            }

        }

    }


}
