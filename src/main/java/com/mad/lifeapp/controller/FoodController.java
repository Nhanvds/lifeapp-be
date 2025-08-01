package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodCategory;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.enums.CategoryEnum;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.service.FileService;
import com.mad.lifeapp.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/food-category")
    public ResponseEntity<FoodCategory> getFoodCategory(@RequestParam(name = "category") String category, Pageable pageable){

        CategoryEnum categoryEnum = CategoryEnum.valueOf(category);
        return ResponseEntity.ok().body(foodService.getFoodCategorys(categoryEnum,pageable));
    }

    @GetMapping("/find-food")
    public ResponseEntity<FoodCategory> findFood(@RequestParam(name = "nameFood") String nameFood,
                                                 @RequestParam(name = "nameCategory") String nameCategory,
                                                 @RequestParam(name = "checkOptionTime") Integer checkOptionTime,
                                                 @RequestParam(name = "time") Integer time,
                                                 @RequestParam(name = "calo") Integer typeCalo,
                                                 Pageable pageable
                                                       ){
        CategoryEnum categoryEnum = CategoryEnum.valueOf(nameCategory);
        return ResponseEntity.ok().body(foodService.findFood(nameFood, categoryEnum, checkOptionTime, time, typeCalo, pageable ));

    }

    @PostMapping("/add-food")
    public ResponseEntity<Boolean> addFodd (@RequestBody FoodRequest foodRequest) throws InvalidException {
////        log.info("Name : ", foodRequest.getName());
//        log.info(foodRequest.toString() + null);
        Boolean checkAdd = foodService.addFood(foodRequest);
        return ResponseEntity.ok().body(checkAdd);
    }

    @GetMapping("/foods")
    public ResponseEntity<?> getFoods(){
        return ResponseEntity.ok().body(foodService.getFoods());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("files") List<MultipartFile> images) {

//        List<MultipartFile> multipartFiles = new ArrayList<>();
//        multipartFiles.add(image);
        List<String> urls = fileService.uploadFiles(images);
        System.out.println(urls.get(0));
        return ResponseEntity.ok().body("Thanh cong");
    }


}
