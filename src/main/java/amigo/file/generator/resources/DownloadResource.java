package amigo.file.generator.resources;

import amigo.file.generator.service.FileGeneratorService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/fileGenerator")
public class DownloadResource {
    FileGeneratorService fileGeneratorService;
    Logger logger = LoggerFactory.getLogger(DownloadResource.class);

    public DownloadResource(FileGeneratorService fileGeneratorService) {
        this.fileGeneratorService = fileGeneratorService;
    }

    @PostMapping("/download")
    public ResponseEntity generateFile(@RequestBody Map<String, Object> jsonObject) {
        boolean isCreated = fileGeneratorService.generateFile(jsonObject);
        logger.info("File Created And Saved " + isCreated);
        return ResponseEntity.ok(Map.of("fileCreated", isCreated));
    }
}
