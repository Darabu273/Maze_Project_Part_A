package IO;

import java.io.*;


/**
 * compress maze by the given method of this task
 */
public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) { }

    @Override
    public void write(byte[] b){
        try{
            byte[] bytes = new  byte[b.length];
            int i;
            for (i = 0; i < 12; i++) { //copy the values of - rows, columns, start position and goal position as is to new byte array.
                bytes[i] =(b[i]);
            }
            out.write(bytes,0,12); //write the meta data of the maze to the file
            int counter=0; //count how many chars of the current checked char we have seen
            int prev = 0; // prev position = we will start the counting with zero-char
            for (int j = 12; j <b.length ; j++) { //will do different manipulate of write
                if (prev == b[j]){
                    counter++;
                }
                else{
                    int target = counter;
                    prev = 1-prev;//will change to 1 if prev was 0 and the same action to the opposite option.
                    if(counter > 255) // when counter bigger than 255 we will separate the num of the current byte with zero between the values.
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
                        out.write(bytes,i,1);// write to out the counter for this byte
                    }
                    else{ // if the counter less then 255 we can represent in one byte.
                        bytes[i] =(byte)counter;
                        out.write(bytes,i,1);}// write to out the counter for this byte
                    i++;
                    counter = 1;

                }
            }
            //add the last counter-chars to the array
            int targetEnd = counter;
            if(counter > 255) // when counter bigger than 255 we will separate the num of the current byte with zero between the values.
            {
                while (targetEnd>255){
                    bytes[i] = (byte)255;
                    out.write(bytes,i,1); // write to out the counter for this byte
                    targetEnd= targetEnd-255;
                    i++;
                    bytes[i] = 0; //separate the values - because the maximum value can be 255.
                    out.write(bytes,i,1);// write to out the counter for this byte
                    i++;
                }}
            bytes[i] =(byte)targetEnd; //remainder
            out.write(bytes,i,1);// write to out the counter for this byte
        } catch (IOException e) {
            e.printStackTrace();
        }}
}
