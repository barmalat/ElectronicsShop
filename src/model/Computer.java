package model;

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

    public enum ConfigKey {
        CPU,
        RAM
    }
}