package model;

class Validator {
    static boolean isBarCodeValid(String barCode) {
       return !barCode.isEmpty();
    }
}
