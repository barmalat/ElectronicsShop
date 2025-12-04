package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Smartphone extends Product {
    private final Map<ConfigKey, String> config;

    public Smartphone(String id, String name, BigDecimal price, int stock) {
        super(id, name, price, stock);
        this.config = new HashMap<>(Map.of(ConfigKey.COLOR,"Kolor do konfiguracji własnej", ConfigKey.BATTERY, "Bateria do konfiguracji własnej", ConfigKey.ACCESSORIES,"Akcesoria do konfiguracji własnej"));
    }

    public Map<ConfigKey, String> getConfig() {
        return config;
    }

    @Override
    public String toString() {
        return "Smartphone: " +
                "name = " + name +
                ", id = " + id +
                ", color = " + config.get(ConfigKey.COLOR) +
                ", batteryCapacity = " + config.get(ConfigKey.BATTERY) +
                ", accessories = " + config.get(ConfigKey.ACCESSORIES) +
                ", price = " + price +
                ", stock = " + stock;
    }

    public enum ConfigKey {
        COLOR,
        BATTERY,
        ACCESSORIES
    }
}