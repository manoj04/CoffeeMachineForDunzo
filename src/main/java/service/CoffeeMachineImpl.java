package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Beverage;
import model.Machine;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static utils.MachineBuilder.buildMachineFromInputJson;

public class CoffeeMachineImpl extends CoffeeMachine {

    protected final ExecutorService beverageMaker;

    public CoffeeMachineImpl(String inputJSON) throws JsonProcessingException {
        machineDetails = buildMachineFromInputJson(inputJSON);
        fillIngredientInventory(machineDetails);
        beverageMaker = Executors.newFixedThreadPool(machineDetails.getNumberOfOutlets());
    }

    private void fillIngredientInventory(Machine machineDetails) {
        // persists inventory details in DB (in our case its HashMap)
        IngredientCatalogueManager.getInstance().putIngredientCatalogue(machineDetails, machineDetails.getInventoryCatalogue());
    }


    @Override
    public void serveBeverage(Beverage beverage) {
        BeverageWorker beverageWorker = new BeverageWorker(this.machineDetails, beverage);
        beverageMaker.submit(beverageWorker);
    }

    @Override
    public void refillIngredient(String ingredient, Integer quantity) {
        // updates the quantity of a beverage
        IngredientCatalogueManager.getInstance().refillCoffeeMachineIngredient(machineDetails, ingredient, quantity);
    }

    @Override
    public void startServing() {
        // starts serving beverages
        Set<Beverage> beveragesToBeServed = machineDetails.getBeveragesToBeServed();
        for (Beverage beverage : beveragesToBeServed)
            serveBeverage(beverage);
    }

    @Override
    public void stopServing() {
        //stops serving any beverage
        beverageMaker.shutdown();
        try {
            if (!beverageMaker.awaitTermination(10, TimeUnit.SECONDS))
                beverageMaker.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
