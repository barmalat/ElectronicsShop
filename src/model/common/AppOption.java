package model.common;

import exceptions.NoSuchOptionException;

public enum AppOption {
    EXIT(0, "Wyjście z programu"),
    SHOW_ALL_PRODUCTS(1, "Wyświetl dostępne produkty"),
    ADD_TO_CART(2, "Dodaj do koszyka"),
    SHOW_CART(3, "Wyświetl koszyk"),
    MAKE_ORDER(4, "Złóż zamówienie");

    private final int value;
    private final String description;

    AppOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    public static AppOption createFromInt(int option) throws NoSuchOptionException {
        try {
            return AppOption.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id " + option);
        }
    }
}