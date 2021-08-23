package com.example.appraiserbase.model.conclusions.enums;

public enum TypeOfValue {
    MARKET_VALUE ("Рыночная стоимость"),
    LIQUIDATION_VALUE ("Ликвидационная стоимость"),
    RESIDUAL_VALUE ("Остаточная стоимость"),
    INVESTMENT_VALUE ("Инвестиционная стоимость"),
    REPLACEMENT_VALUE ("Восстановительная стоимость"),
    SYNERGISTIC_VALUE ("Синергетическая стоимость");

    private final String TYPE_OF_VALUE;

    TypeOfValue(String TYPE_OF_VALUE) {
        this.TYPE_OF_VALUE = TYPE_OF_VALUE;
    }

    public String getTypeOfValue() {
        return TYPE_OF_VALUE;
    }
}
