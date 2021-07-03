package service;

import lombok.Getter;
import model.Beverage;
import model.Machine;

@Getter
public abstract class CoffeeMachine {

    protected Machine machineDetails;

    public abstract void serveBeverage(Beverage beverage);

    public abstract void refillIngredient(String ingredient, Integer quantity);

    public abstract void startServing();

    public abstract void stopServing();
}
