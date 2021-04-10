package xyz.kocmut;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.impl.ConcurrentHashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PhoneNumbers {

    private static final int NUMBERS_SIZE = 229038;
    static final int[] data = new int[NUMBERS_SIZE];

    private static final Logger LOGGER = Logger.getLogger(PhoneNumbers.class.getName());

    void onStart(@Observes StartupEvent ev) {
        long startTime = System.nanoTime();
        loadIntoArray();
        long endTime = System.nanoTime();
        LOGGER.log(Level.INFO, String.format("Numbers loading took %dms", (endTime - startTime) / 1000000));
    }

    void loadIntoArray() {
        try (InputStream in = getClass().getResourceAsStream("/numbers.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < NUMBERS_SIZE; i++) {
                String line = reader.readLine();
                int number = Integer.parseInt(line);
                data[i] = number;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
