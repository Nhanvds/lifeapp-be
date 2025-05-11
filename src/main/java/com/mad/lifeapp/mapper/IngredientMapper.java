package com.mad.lifeapp.mapper;
import com.mad.lifeapp.dto.request.IngredientReq;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.dto.response.IngredientRes;
import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.entity.IngredientsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {


   IngredientsEntity toIngredient (IngredientReq ingredientReq);
   IngredientRes  toIngredientRes(IngredientsEntity ingredientsEntity);
}
