package view;

import java.util.Observable;

public class BarCodeScanner extends Observable implements IBarCodeScanner {
    @Override
    public void input(final String input) {
        setChanged();
        notifyObservers(input);
    }
}
