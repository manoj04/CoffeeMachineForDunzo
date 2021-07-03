package service;

import model.Beverage;
import model.Machine;

import java.util.Map;
/*
Worker Thread that tries to serve a Beverage from a Coffee Machine
 */
public class BeverageWorker implements Runnable {
    final Beverage beverageToPrepare;
    final Machine machine;

    public BeverageWorker(Machine machine, Beverage beverageToPrepare) {
        this.beverageToPrepare = beverageToPrepare;
        this.machine = machine;
    }

    @Override
    public void run() {
        synchronized (machine) {
            String machineId = machine.getId();
            Map<String, Integer> ingredientCatalogueByMachine = IngredientCatalogueManager.getInstance().getIngredientCatalogueByMachine(machineId);

            boolean canPrepareBeverage = true;
            String beverageName = beverageToPrepare.getBeverageName();

            for (Map.Entry<String, Integer> beverageIngredient : beverageToPrepare.getRequiredIngredients().entrySet()) {
                String currentIngredientName = beverageIngredient.getKey();
                Integer requiredQuantity = beverageIngredient.getValue();
                if (!ingredientCatalogueByMachine.containsKey(beverageIngredient.getKey())) {
                    System.out.printf("Beverage %s cannot be prepared as Ingredient %s is not available in CoffeeMachine Id %s", beverageName, currentIngredientName, machineId);
                    System.out.println();
                    canPrepareBeverage = false;
                    break;
                } else if (ingredientCatalogueByMachine.get(beverageIngredient.getKey()) < requiredQuantity) {
                    System.out.printf("Beverage %s cannot be prepared as Ingredient %s is not sufficient in CoffeeMachine Id %s", beverageName, currentIngredientName, machineId);
                    System.out.println();
                    canPrepareBeverage = false;
                    break;
                }
            }

            if (canPrepareBeverage) {
                for (Map.Entry<String, Integer> beverageIngredient : beverageToPrepare.getRequiredIngredients().entrySet()) {
                    String ingredientName = beverageIngredient.getKey();
                    Integer ingredientRequiredQuantity = beverageIngredient.getValue();
                    Integer catalogueQuantity = ingredientCatalogueByMachine.get(beverageIngredient.getKey());
                    Integer updatedCatalogueQuantity = catalogueQuantity - ingredientRequiredQuantity;
                    IngredientCatalogueManager.getInstance().refillCoffeeMachineIngredient(machine, beverageIngredient.getKey(), updatedCatalogueQuantity);
                    machine.getInventoryCatalogue().put(ingredientName, updatedCatalogueQuantity);
                }
                System.out.printf("Beverage %s has been served by CoffeeMachine Id %s", beverageName, machineId);
                System.out.println();
            }
        }

    }
}
