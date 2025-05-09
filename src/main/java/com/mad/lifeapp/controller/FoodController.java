package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.service.FileService;
import com.mad.lifeapp.service.FoodService;
import com.mad.lifeapp.service.UserService;
import com.mad.lifeapp.service.impl.FileServiceImpl;
import com.mad.lifeapp.service.impl.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final FileService fileService;

    @GetMapping("/food")
    public ResponseEntity<?> getFood(@RequestParam(name = "id")Long id) throws NotFoundException {
        FoodResponse food = foodService.getFood(id);
        return ResponseEntity.ok().body(food);
    }

    @PostMapping("/add-food")
    public ResponseEntity<?> addFodd (@RequestBody FoodRequest foodRequest) throws InvalidException {
//        log.info("Name : ", foodRequest.getName());
        log.info(foodRequest.toString());
//        Boolean checkAdd = foodService.addFood(foodRequest);
        return ResponseEntity.ok().body("Thanh cong");
    }

    @GetMapping("/foods")
    public ResponseEntity<?> getFoods(){
        return ResponseEntity.ok().body(foodService.getFoods());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {

        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(image);
        List<String> urls = fileService.uploadFiles(multipartFiles);
        System.out.println(urls.get(0));
        return ResponseEntity.ok().body("Thanh cong");
    }

}
