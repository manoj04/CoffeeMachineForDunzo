package service;


import model.Machine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
Acts as a DB(repository) for Ingredients, can only create one instance of this class(Singleton)
 */
public class IngredientCatalogueManager {

    // stores Ingredients for each and every machine
    // Machine Id is the key and Ingredients is the value
    private static final Map<String, Map<String, Integer>> catalogueRepositoryByMachine = new HashMap<>();

    private static class IngredientCatalogueManagerHolder {
        static final IngredientCatalogueManager instance = new IngredientCatalogueManager();
    }

    public static IngredientCatalogueManager getInstance() {
        return IngredientCatalogueManagerHolder.instance;
    }

    private IngredientCatalogueManager() {
    }

    public void refillCoffeeMachineIngredient(Machine machine, String ingredient, Integer quantity) {
        Map<String, Integer> ingredientCatalogueByMachine = getIngredientCatalogueByMachine(machine.getId());
        Integer existingQuantity = ingredientCatalogueByMachine.getOrDefault(ingredient, 0);
        Integer refilledQuantity = existingQuantity + quantity;
        machine.getInventoryCatalogue().put(ingredient, refilledQuantity);
        ingredientCatalogueByMachine.put(ingredient, refilledQuantity);
        putIngredientCatalogue(machine, ingredientCatalogueByMachine);
    }

    public Map<String, Integer> getIngredientCatalogueByMachine(String coffeeMachineId) {
        return catalogueRepositoryByMachine.getOrDefault(coffeeMachineId, Collections.emptyMap());
    }

    public void putIngredientCatalogue(Machine machine, Map<String, Integer> ingredientCatalogueByMachine) {
        catalogueRepositoryByMachine.put(machine.getId(), ingredientCatalogueByMachine);
    }

}
