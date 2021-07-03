package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Beverage;
import model.Machine;
import service.IngredientCatalogueManager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

// Utility class for building a Machine from Json input
public class MachineBuilder {
    public static Machine buildMachineFromInputJson(String inputJSON) throws JsonProcessingException {
        // can use @JsonProperty for mapping and object creation in actual scenarios, Hard coding the creation for now
        System.out.println("Building new Coffee Machine");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputJSON).get("machine");
        int outlets = jsonNode.get("outlets").get("count_n").intValue();
        Map<String, Integer> inventoryCatalogue = objectMapper.convertValue(jsonNode.get("total_items_quantity"), new TypeReference<Map<String, Integer>>() {
        });
        Set<Beverage> beverageSet = new HashSet<>();
        JsonNode beverages = jsonNode.get("beverages");
        beverages
                .fieldNames()
                .forEachRemaining(key -> beverageSet.add(new Beverage(key,
                                objectMapper.convertValue(beverages.get(key), new TypeReference<Map<String, Integer>>() {
                                }))
                        )
                );

        return Machine.builder()
                .id(UUID.randomUUID().toString())
                .numberOfOutlets(outlets)
                .beveragesToBeServed(beverageSet)
                .inventoryCatalogue(inventoryCatalogue)
                .build();
    }

}
