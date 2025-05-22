//package com.mad.lifeapp.service.impl;
//
//import com.mad.lifeapp.component.JwtUtils;
//import com.mad.lifeapp.dto.response.DailyMenuFoodRes;
//import com.mad.lifeapp.dto.response.DailyRes;
//import com.mad.lifeapp.dto.response.FoodResponse;
//import com.mad.lifeapp.dto.response.IngredientRes;
//import com.mad.lifeapp.entity.*;
//import com.mad.lifeapp.exception.NotFoundException;
//import com.mad.lifeapp.mapper.FoodMapper;
//import com.mad.lifeapp.mapper.IngredientMapper;
//import com.mad.lifeapp.repository.*;
//import com.mad.lifeapp.service.DailyService;
//import com.mad.lifeapp.service.NoteFoodService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//
//@Service
//@RequiredArgsConstructor
//public class NoteFoodServiceImpl implements NoteFoodService {
//
//    public final NoteFoodRepository noteFoodRepository;
//    public final FoodReponsitory foodReponsitory;
//    public final UserRepository userRepository;
//    @Override
//    public Boolean createNoteFood(Long idUser, Long idNote, String note) {
//
//        try {
//            NoteFoodEntity noteFoodEntity = NoteFoodEntity.builder()
//                    .food(foodReponsitory.findById(idUser).get())
//                    .user(userRepository.findById(idUser).get())
//                    .note(note)
//                    .build();
//        }
//        catch (Exception e){
//            throw new RuntimeException("Them note cho mon an that bai");
//        }
//        return true;
//    }
//}