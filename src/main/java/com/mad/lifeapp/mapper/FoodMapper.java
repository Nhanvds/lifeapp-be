package com.mad.lifeapp.mapper;
import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.entity.FoodEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FoodMapper {

//    public FoodResponse toResponse(FoodEntity foodEntity){
//        return FoodResponse.builder()
//                .id(foodEntity.getId())
//                .name(foodEntity.getName())
//                .description(foodEntity.getDescription())
//                .fat(foodEntity.getFat())
//                .carbs(foodEntity.getCarbs())
//                .category(foodEntity.getCategory())
//                .cookingTime(foodEntity.getCookingTime())
//                .protein(foodEntity.getProtein())
//                .createBy(foodEntity.getCreatedBy())
//                .imageUrl(foodEntity.getImageUrl())
//                .build();
//    }

   FoodResponse toFoodResponse(FoodEntity foodEntity);

   FoodEntity toFood(FoodRequest foodRequest);
}
