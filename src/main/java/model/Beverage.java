package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class Beverage {
    private final String beverageName;
    private final Map<String, Integer> requiredIngredients;
}
