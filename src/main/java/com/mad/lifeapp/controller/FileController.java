package com.mad.lifeapp.controller;

import com.mad.lifeapp.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(
            @RequestParam("files") List<MultipartFile> files) {
        return ResponseEntity.ok().body(fileService.uploadFiles(files));
    }

}
