package com.example.appraiserbase.model.conclusions;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.conclusions.enums.AssessmentMethod;
import com.example.appraiserbase.model.conclusions.enums.AssessmentPurpose;
import com.example.appraiserbase.model.conclusions.enums.TypeOfValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "equipment_conclusions")
public class EquipmentConclusion {

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

    @Column(length = 100, nullable = false)
    private String manufacturer;                                        //производитель

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(evaluationDate);                            //дата оценки
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getConclusionNumber() {
        return conclusionNumber;
    }

    public void setConclusionNumber(String conclusionNumber) {
        this.conclusionNumber = conclusionNumber;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getSubjectOfAssessment() {
        return subjectOfAssessment;
    }

    public void setSubjectOfAssessment(String subjectOfAssessment) {
        this.subjectOfAssessment = subjectOfAssessment;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDate dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getAppraisalPrices() {
        return appraisalPrices;
    }

    public void setAppraisalPrices(LocalDate appraisalPrices) {
        this.appraisalPrices = appraisalPrices;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public AssessmentPurpose getAssessmentPurpose() {
        return assessmentPurpose;
    }

    public void setAssessmentPurpose(AssessmentPurpose assessmentPurpose) {
        this.assessmentPurpose = assessmentPurpose;
    }

    public AssessmentMethod getAssessmentMethod() {
        return assessmentMethod;
    }

    public void setAssessmentMethod(AssessmentMethod assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    public TypeOfValue getTypeOfValue() {
        return typeOfValue;
    }

    public void setTypeOfValue(TypeOfValue typeOfValue) {
        this.typeOfValue = typeOfValue;
    }

    public Long getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(Long evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public Appraiser getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(Appraiser appraiser) {
        this.appraiser = appraiser;
    }
}
