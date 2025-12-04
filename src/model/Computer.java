package model;

import model.common.ConfigKey;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Computer extends Product {
    private final Map<ConfigKey, String> config;

    public Computer(String id, String name, BigDecimal price, int stock) {
        super(id, name, price, stock);
        this.config = new HashMap<>(Map.of(ConfigKey.CPU, "CPU do konfiguracji własnej", ConfigKey.RAM, "RAM do konfiguracji własnej"));
    }

    public Map<ConfigKey, String> getConfig() {
        return config;
    }

    public void getConfigInfo() {
        System.out.print(" (konfiguracja: " + config.get(ConfigKey.CPU) +
                ", " + config.get(ConfigKey.RAM) + ")");
    }

    @Override
    public String toString() {
        return "Computer: " +
                "name = " + name +
                ", id = " + id +
                ", processor = " + config.get(ConfigKey.CPU) +
                ", ramMemory = " + config.get(ConfigKey.RAM) +
                ", price = " + price +
                ", stock = " + stock;
    }
}