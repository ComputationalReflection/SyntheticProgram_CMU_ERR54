package es.uniovi.reflection.err_54.compliant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class ERR_54_Compliant {

    public void noExceptionBetweenNewAndClose() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));  //<---- DEC 80
        br.close();
        if (8 == "Foo".length())
            FILE_NAME.toCharArray();
        Function<Object, Boolean> c = (br = null)::equals;
        for (int i = 0; i < 6; i++)
            System.out.println(i);
//        br.close();

    }

    public void noExceptionBetweenNewAndClose2() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));  //<---- DEC 80
//        br.close();
        if (8 == "Foo".length())
            FILE_NAME.toCharArray();
        Function<Object, Boolean> c = (br = null)::equals;
        for (int i = 0; i < 6; i++)
            System.out.println(i);
        br.close();

    }


    private static final String FILE_NAME = "Test";

    public void tryWithResources() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
             BufferedReader br2 = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
            br.close();
            if (8 == "Foo".length())
                FILE_NAME.toCharArray();
            for (int i = 0; i < 6; i++)
                System.out.println(i);
        }
    }

    public void handledWithCatches() throws IOException{
        BufferedReader br=null, br2=null;    //<-----DEC 617, 621

        try  {
             br = new BufferedReader(new FileReader(new File(FILE_NAME)));
             br2 = new BufferedReader(new FileReader(new File(FILE_NAME)));
            br.close();
            if (8 == "Foo".length())
                FILE_NAME.toCharArray();
            for (int i = 0; i < 6; i++)
                System.out.println(i);
            br2.close();
            br.close();
        }catch(IOException ioExc){
          try {
              br.close();
              br2.close();
          }catch(IOException ioExc2){
              br2.close();
          }
        }
    }

    public void handledWithFinallies() throws IOException, InterruptedException {
        BufferedReader br=null, br2=null;  //<-----DEC 720, 724

        try  {
            br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            br2 = new BufferedReader(new FileReader(new File(FILE_NAME)));
            Thread.sleep(5);
        }finally {
            try {
                br.close();
            }finally
            {
                br2.close();
            }
        }
    }

    public void oneResourceWithClose() throws IOException {
        if("qwe".length() == 3) {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            br.close();
        }
    }

    public void oneResourceWithCloseInTry() throws IOException {
        int times = 3;
        BufferedReader br = null;
        while(times > 0) {
            times--;
            try{
                br = new BufferedReader(new FileReader(new File(FILE_NAME)));
                int data = br.read();
                br.close();
            }finally{
                br.close();
            }
        }
    }
}
