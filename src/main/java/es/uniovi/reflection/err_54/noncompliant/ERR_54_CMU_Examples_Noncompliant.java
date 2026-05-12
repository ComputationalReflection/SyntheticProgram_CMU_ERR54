package es.uniovi.reflection.err_54.noncompliant;

import java.io.*;
import java.util.function.Function;

public class ERR_54_CMU_Examples_Noncompliant {

    public void processFile(String inPath, String outPath)
            throws IOException{
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(inPath));
            bw = new BufferedWriter(new FileWriter(outPath));
            // Process the input and produce the output
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException x) {
                // Handle error
            }
        }
    }
}
