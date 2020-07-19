package ru.strict.utils;

import ru.strict.validate.BaseValidate;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class WinRegistry {

    public static String readRegKey(String location, String key){
        if(BaseValidate.isEmptyOrNull(location)){
            throw new IllegalArgumentException("location is NULL");
        }
        if(BaseValidate.isEmptyOrNull(key)){
            throw new IllegalArgumentException("key is NULL");
        }

        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec(String.format("reg query \"%s\" /v %s", location, key));

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            // Output has the following format:
            // \n<Version information>\n\n<key>\t<registry type>\t<value>
            if(BaseValidate.isEmptySpaceOrNull(output)){
                return null;
            }

            // Parse out the value
            String[] parsed = output.split(" ");
            return parsed[parsed.length-1].trim();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw= new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }
}
