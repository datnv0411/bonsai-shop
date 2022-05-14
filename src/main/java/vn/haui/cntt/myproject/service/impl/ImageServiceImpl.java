package vn.haui.cntt.myproject.service.impl;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@org.springframework.transaction.annotation.Transactional
public class ImageServiceImpl {
    private final Path storageFolder = Paths.get("src/main/resources/static/images");
    //constructor
    public ImageServiceImpl() {
        try {
            Files.createDirectories(storageFolder);
        }catch (IOException exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }

    @Transactional
    public byte[]  readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + fileName);
            }
        }
        catch (IOException exception) {
            throw new RuntimeException("Could not read file: " + fileName, exception);
        }
    }

    @Transactional
    public void uploadFile(String uploadDir, MultipartFile multipartFile, String fileName) throws IOException {
        Path uploadPath = Paths.get("src/main/resources/static/images/" + uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try{
            if(Files.exists(Paths.get("src/main/resources/static/images/" + uploadDir+"/"+fileName))){
                return;
            }
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Không thể lưu file đã tải lên: " + fileName);
        }
    }
}
