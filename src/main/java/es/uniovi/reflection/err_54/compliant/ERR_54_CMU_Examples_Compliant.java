package es.uniovi.reflection.err_54.compliant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

public class ERR_54_CMU_Examples_Compliant {

    public void processFileWithTryCatchFinally(String inPath, String outPath)
            throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(inPath));
            bw = new BufferedWriter(new FileWriter(outPath));
            // Process the input and produce the output
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException x) {
                    // Handle error
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException x) {
                            // Handle error
                        }
                    }
                }
            }
        }
    }


    public void processFileWithTryWithResources(String inPath, String outPath)
            throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(inPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outPath))) {
            // Process the input and produce the output
        } catch (IOException ex) {
            System.err.println("thrown exception: " + ex.toString());
            Throwable[] suppressed = ex.getSuppressed();
            for (int i = 0; i < suppressed.length; i++) {
                System.err.println("suppressed exception: " + suppressed[i].toString());
            }
        }
    }
}
