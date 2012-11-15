/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.io.*;
import java.util.Vector;

public class ListOfNumbers {
    private Vector<Integer> victor;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        victor = new Vector<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
            victor.addElement(new Integer(i));
    }
    public void writeList() {
        PrintWriter out = null;

        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("OutFile.txt"));
        
            for (int i = 0; i < SIZE; i++)
                out.println("Value at: " + i + " = " + victor.elementAt(i));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Caught ArrayIndexOutOfBoundsException: " +
                                 e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
    public void readList(String fileName) {
       String line = null;
       try {
           RandomAccessFile raf = new RandomAccessFile(fileName, "r");
           int k = 0;
           while ((line = raf.readLine()) != null) {
               //Integer i = new Integer(Integer.parseInt(line));
               /*
                * the format of line is not that of an Integer, so the above doesn't work
                * to make it work, the integer part of the string must be removed from the rest of the string
                * this is done below:
                */
               int index = line.length() - (line.length() - 13)/2;
               Integer i = new Integer(Integer.parseInt(line.substring(index)));
               
               System.out.println(i);
               victor.addElement(i);
               k++;
           }
       } catch(FileNotFoundException fnf) {
           System.err.println("File: " + fileName + " not found.");
       } catch (IOException io) {
           System.err.println(io.toString());
       }
   }
    
    public static void main(String[] args)
    {
      ListOfNumbers list = new ListOfNumbers();
      list.writeList();
      list.readList("OutFile.txt");
    }
}