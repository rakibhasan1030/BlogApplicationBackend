package org.rakibhasan.blog.services.impl;

import org.rakibhasan.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // GET FILE NAME
        String name = file.getOriginalFilename();

        // CREATE FULL PATH
        String randomId = UUID.randomUUID().toString();
        String updatedName = null;
        if (name != null) {
            updatedName = randomId.concat(name.substring(name.lastIndexOf(".")));
        }
        String filePath = path + File.separator + updatedName;

        // CREATE FOLDER IF NOT CREATED
        File newFile = new File(path);
        if (!newFile.exists()) {
            // Check if mkdirs() is successful
            if (!newFile.mkdirs()) {
                throw new IOException("Failed to create directory: " + path);
            }
        }

        // UPLOAD OR COPY IMAGE FROM FILE
        Files.copy(file.getInputStream(), Paths.get(filePath));


        return filePath;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
