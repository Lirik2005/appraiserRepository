package com.example.appraiserbase.model.conclusions;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.conclusions.enums.AssessmentMethod;
import com.example.appraiserbase.model.conclusions.enums.AssessmentPurpose;
import com.example.appraiserbase.model.conclusions.enums.TypeOfValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transport_conclusions")
public class TransportConclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(length = 100, nullable = false)
    private String conclusionNumber;                                //номер заключения

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;                                       //дата выдачи заключения

    @Column(length = 100, nullable = false)
    private String contractNumber;                                  //номер договора

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractDate;                                     //дата заключения договора

    @Column(length = 100, nullable = false)
    private String subjectOfAssessment;                            //объект оценки

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate evaluationDate;                                   //дата оценки

    @Column(length = 100, nullable = false)
    private String model;                                        //модель

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfProduction;                                   //дата выпуска

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appraisalPrices;                                   //цены оценки

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inspectionDate;                                   //дата осмотра

    @Column(length = 100, nullable = false)
    private String calculationMethod;                               //метод расчета стоимости

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private AssessmentPurpose assessmentPurpose;                    //цель оценки

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private AssessmentMethod assessmentMethod;                      //метод оценки

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private TypeOfValue typeOfValue;                               //предмет стоимости

    @Column(nullable = false)
    private Long evaluationResult;                                 //результат независимой оценки

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "appraiser_pid")
    private Appraiser appraiser;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getConclusionNumber() {
        return conclusionNumber;
    }


    @Transient
    public String dataHelperDateOfIssue() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateOfIssue.format(formatter);                            //дата выдачи заключения
    }

    @Transient
    public String dataHelperContractDate() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return contractDate.format(formatter);                            //дата заключения договора
    }

    @Transient
    public String dataHelperEvaluationDate() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return evaluationDate.format(formatter);                            //дата оценки
    }

    @Transient
    public String dataHelperInspectionDate() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return inspectionDate.format(formatter);                            //дата осмотра
    }

    @Transient
    public String dataHelperAppraisalPrices() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return appraisalPrices.format(formatter);                            //цены оценки
    }

    @Transient
    public String dataHelperDateOfProduction() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateOfProduction.format(formatter);                            //дата выпуска
    }

    @Transient
    public String getEnumAssessmentMethod() {
        return assessmentMethod.getAssessmentMethod();
    }

    @Transient
    public String getEnumAssessmentPurpose() {
        return assessmentPurpose.getAssessmentPurpose();
    }

    @Transient
    public String getEnumTypeOfValue() {
        return typeOfValue.getTypeOfValue();
    }
}
