package com.example.appraiserbase.model.conclusions.enums;

public enum AssessmentPurpose {
    SALE("Продажа без проведения аукциона"),
    AUCTION("Продажа на торгах"),
    PLEDGE("Передача в залог"),
    REVALUATION("Переоценка основных средств"),
    BALANCE_SHEET("Постановка на баланс"),
    MANAGEMENT_DECISION("Реализация управленческого решения");

    private final String ASSESSMENT_PURPOSE;

    AssessmentPurpose(String ASSESSMENT_PURPOSE) {
        this.ASSESSMENT_PURPOSE = ASSESSMENT_PURPOSE;
    }

    public String getAssessmentPurpose() {
        return ASSESSMENT_PURPOSE;
    }
}
