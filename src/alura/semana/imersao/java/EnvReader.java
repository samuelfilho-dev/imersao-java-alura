package alura.semana.imersao.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvReader {

    // This Method reader the .env file responsibly for environment variables

    public static Properties getEnv() {
        Properties props = new Properties();
        FileInputStream envFile = null;
        try {
            envFile = new FileInputStream(".env");
            props.load(envFile);
            envFile.close();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

        return props;
    }

}
