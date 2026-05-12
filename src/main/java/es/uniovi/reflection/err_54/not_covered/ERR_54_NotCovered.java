package es.uniovi.reflection.err_54.not_covered;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class ERR_54_NotCovered {

    private static final String FILE_NAME = "Test";
/* FALSE POSITIVE
    public void noExceptionBetweenNewAndClose() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));  //<---- DEC 80
        br.close();
        if (8 == "Foo".length())
            FILE_NAME.toCharArray();
        Function<Object, Boolean> c = (br = null)::equals;
        for (int i = 0; i < 6; i++)
            System.out.println(i);
        br.close(); //Technically there is an exception between new and close, but it comes from another close, so it
        // could be ignored by refining the query. But, the scenario is not realistic
    }


Also, it could be a false positive if the variable is closed in an auxiliar method or returned to a method in which is closed
This cases could be prevented discarding cases in which the variable is used as argument or returned
 */
    //We decide to not cover this cases since they have no close, it is very difficult and does not match exactly the rule
}
