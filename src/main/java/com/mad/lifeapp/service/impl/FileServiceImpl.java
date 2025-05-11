package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.configuration.CloudinaryConfig;
import com.mad.lifeapp.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final CloudinaryConfig cloudinaryConfig;

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        if (files != null && files.size() > 5) {
            throw new RuntimeException("Tối đa 5 ảnh");
        }
        files.forEach(file -> {
            if ((file.isEmpty()) || file.getSize() >= 10 * 1024 * 1024) {
                throw new RuntimeException("File ảnh phải nhỏ hơn 10MB!");
            }
            Map data = null;
            try {
                data = cloudinaryConfig.cloudinary().uploader().upload(
                        file.getBytes(), Map.of());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            urls.add(data.get("secure_url").toString());
        });

        return urls;
    }
}
