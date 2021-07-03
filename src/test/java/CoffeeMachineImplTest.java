import org.junit.Test;
import service.CoffeeMachine;
import service.CoffeeMachineImpl;
import utils.JSONUtil;


public class CoffeeMachineImplTest {


    @Test
    public void testSampleInput() throws Exception {
        System.out.println("Running testSampleInput\n");
        String jsonInput = JSONUtil.readJSONFromFilePath("input1.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(jsonInput);
        System.out.println("CoffeeMachine Before Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        coffeeMachine.startServing();
        coffeeMachine.stopServing();
        System.out.println("\nCoffeeMachine After Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        System.out.println("Exiting testSampleInput\n\n");
    }


    @Test
    public void testForEmptyIngredients() throws Exception {
        System.out.println("Running testForEmptyIngredients\n");
        String jsonInput = JSONUtil.readJSONFromFilePath("input2.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(jsonInput);
        System.out.println("CoffeeMachine Before Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        coffeeMachine.startServing();
        coffeeMachine.stopServing();
        System.out.println("\nCoffeeMachine After Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        System.out.println("Exiting testForEmptyIngredients\n\n");
    }


    @Test
    public void testFor1Outlet() throws Exception {
        System.out.println("Running testFor1Outlet\n");
        String jsonInput = JSONUtil.readJSONFromFilePath("input3.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(jsonInput);
        System.out.println("CoffeeMachine Before Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        coffeeMachine.startServing();
        coffeeMachine.stopServing();
        System.out.println("\nCoffeeMachine After Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        System.out.println("Exiting testFor1Outlets\n\n");
    }

    @Test
    public void testForInsufficientIngredientsForAllDrinks() throws Exception {
        System.out.println("Running testForInsufficientIngredientsForAllDrinks\n");
        String jsonInput = JSONUtil.readJSONFromFilePath("input4.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(jsonInput);
        System.out.println("CoffeeMachine Before Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        coffeeMachine.startServing();
        coffeeMachine.stopServing();
        System.out.println("\nCoffeeMachine After Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        System.out.println("Exiting testForInsufficientIngredientsForAllDrinks\n\n");
    }

    @Test
    public void testForRefillIngredients() throws Exception {
        System.out.println("Running testForRefillIngredients\n");
        String jsonInput = JSONUtil.readJSONFromFilePath("input1.json");
        CoffeeMachine coffeeMachine = new CoffeeMachineImpl(jsonInput);
        System.out.println("CoffeeMachine Before Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        coffeeMachine.startServing();
        coffeeMachine.refillIngredient("hot_water", 1000);
        coffeeMachine.refillIngredient("sugar_syrup", 1000);
        coffeeMachine.stopServing();
        System.out.println("\nCoffeeMachine After Serving\n" + coffeeMachine.getMachineDetails()+"\n");
        System.out.println("Exiting testForRefillIngredients\n\n");
    }


}
