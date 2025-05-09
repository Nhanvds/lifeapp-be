package com.mad.lifeapp.dto;

import com.mad.lifeapp.entity.IngredientsEntity;
import com.mad.lifeapp.enums.CategoryEnum;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@Builder
public class IngredientDTO {
    private Long id;
    private String name;
    private MultipartFile imageUrl;
    private Float gram;
}
