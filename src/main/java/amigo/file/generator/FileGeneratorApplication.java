package amigo.file.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileGeneratorApplication {
    static Logger logger = LoggerFactory.getLogger(FileGeneratorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FileGeneratorApplication.class, args);
        logger.info("FileGeneratorApplication is up and running well.");
    }
}
