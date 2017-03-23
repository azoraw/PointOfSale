package controller;

import view.DisplayInterface;
import view.PrinterInterface;

public interface DeviceProvider {
    DisplayInterface getDisplay();
    PrinterInterface getPrinter();
}
