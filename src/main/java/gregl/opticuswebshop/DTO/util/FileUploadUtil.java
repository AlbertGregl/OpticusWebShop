package gregl.opticuswebshop.DTO.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
    private static final String BASE_UPLOAD_DIR = "src/main/resources/static";
    private static final String IMG_DIR = "/uploads/img/categories/";

    public static String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));
            String filename = UUID.randomUUID() + extension;
            Path uploadPath = Paths.get(BASE_UPLOAD_DIR + IMG_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(filename);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return IMG_DIR + filename;
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + filename, ioe);
            }
        } catch (Exception e) {
            logger.error("Could not save file", e);
            return null;
        }
    }
}