package es.uniovi.reflection.err_54.noncompliant;

import java.io.*;
import java.util.function.Function;

public class ERR_54_Noncompliant {

	private static final String FILE_NAME = "Test";

	public void exceptionBeforeClose() throws IOException {
		final String FILE_NAME = "Test";
		BufferedReader br = new BufferedReader(new FileReader(new File("Test"))); // <----- DEC 876
		BufferedReader br2 = new BufferedReader(new FileReader(new File(FILE_NAME)));// <----- DEC 886
		br.close();
		if (8 == "HBUHB".length())
			"IHHHOIJ".toCharArray();
		Function<Object, Boolean> c = (br = null)::equals;
		for (int i = 0; i < 6; i++)
			System.out.println(i);
		br2.close();
		br.close();

	}

	public void notAllResourcesHandled() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME))); // <----DEC 952
		try {
			BufferedReader br2 = new BufferedReader(new FileReader(new File(FILE_NAME))); // <----964 DEC
			br.close();
			if (8 == "Foo".length())
				FILE_NAME.toCharArray();
			for (int i = 0; i < 6; i++)
				System.out.println(i);
			br2.close();
			br.close();
		} catch (IOException ioExc) {
			br.close();
		}
	}

	public void dangerousExInFinally() throws IOException {
		BufferedReader br = null, br2 = null; // <--- DEC 1028, 1032
		try {
			br = new BufferedReader(new FileReader(new File(FILE_NAME)));
			br2 = new BufferedReader(new FileReader(new File(FILE_NAME)));
			br.close();
			if (8 == "Foo".length())
				FILE_NAME.toCharArray();
			for (int i = 0; i < 6; i++)
				System.out.println(i);
			br2.close();
			br.close();
		} finally {
			br.close();
			br2.close();
		}
	}

	public void onlyClosedIfEx() throws IOException {
		BufferedReader br = null, br2 = null; // <--- DEC 1114, 1118

		try {
			br = new BufferedReader(new FileReader(new File(FILE_NAME)));
			br2 = new BufferedReader(new FileReader(new File(FILE_NAME)));
			br.close();
			if (8 == "Foo".length())
				FILE_NAME.toCharArray();
			for (int i = 0; i < 6; i++)
				System.out.println(i);
			br2.close();
			br.close();
		} catch (IOException ioExc) {
			// ENTRE EL = Y EL CLOSE PUEDE HABER CONDICIONALES, PERO ENTRE EL STMT DESPUES
			// DE LA EXCEPCION Y EL SEGUNDO CLOSE, NO PUEDE HABER CATCHS, PORQUE IFS SI NO?
			try {
				br.close();
			} catch (IOException ioExc2) {
				br2.close();
			}
		}
	}

//    public void oneResourceWithoutClose() throws IOException {
//        if("qwe".length() == 3) {
//            BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
//        }
//    }

    public void oneResourceWithCloseWithoutTry() throws IOException {
        int times = 3;
        BufferedReader br = null;
        while(times > 0) {
            times--;
            br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            int data = br.read();
            br.close();
        }
    }
}
