package com.example.appraiserbase.model.conclusions.enums;

public enum AssessmentMethod {
    COSTLY_METHOD("Затратный метод"),
    COMPARATIVE_METHOD("Сравнительный метод"),
    INCOME_METHOD("Доходный метод"),
    INDEX_METHOD ("Индексный метод"),
    BALANCE_ACCUMULATION ("Метод балансового накопления активов"),
    CADASTRAL_VALUATION ("Метод кадастровой оценки");

    private final String ASSESSMENT_METHOD;

    AssessmentMethod(String ASSESSMENT_METHOD) {
        this.ASSESSMENT_METHOD = ASSESSMENT_METHOD;
    }

    public String getAssessmentMethod() {
        return ASSESSMENT_METHOD;
    }
}
