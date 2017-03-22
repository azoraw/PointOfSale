
import io.DisplayInterface;
import model.Item;

import java.text.DecimalFormat;

public class Display implements DisplayInterface {

    public String getOutputMsg() {
        return outputMsg;
    }

    private String outputMsg;


    @Override
    public void displayItem(Item item) {
        outputMsg = (item.getName() + ": " + new DecimalFormat("#.00").format(item.getPrice()));
    }

    @Override
    public void displayError(String error) {
        outputMsg = error;
    }

    @Override
    public void displayTotalSum(String sum) {
        outputMsg = "total: " + sum;
    }
}
