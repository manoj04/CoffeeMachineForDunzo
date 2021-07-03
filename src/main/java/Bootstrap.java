import org.apache.commons.io.FileUtils;
import service.CoffeeMachine;
import service.CoffeeMachineImpl;
import utils.JSONUtil;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Bootstrap {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String inputAsJSON = JSONUtil.readJSONFromFilePath("input1.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(inputAsJSON);
        coffeeMachine.startServing();
        coffeeMachine.refillIngredient("hot_water", 1000);
        coffeeMachine.refillIngredient("sugar_syrup", 1000);
        coffeeMachine.stopServing();
        Thread.sleep(10000);
        System.out.println(coffeeMachine.getMachineDetails());
    }
}
