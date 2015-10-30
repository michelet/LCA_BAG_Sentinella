/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micheletc
 */
public class Patient {
    private List activities;
    private String patID;
    private String PatNumber;
    private String patBirthdate;
    private String patLetterSalutation;
    private String patSalutation;
    private String patTitle;
    private String patPoBox;
    private String patZip;
    private String patCity;
    private String patCountry;
    private String patDiagnosis;
    private String patLanguage;
    private String patConsStatus;
    private String patRxNumber;
    private String patRecall1;
    private String patRecall2;
    private String patInsuranceAbbrev1;
    private String patInsuranceAbbrev2;
    private String patInsuranceAbbrev3;
    private String patInsuranceAbbrev4;
    private String patEmplAbbrev;
    private String patGarantInfo;
    private String patGarantID;
    private String patLastBehNumber;
    private String patLastBehDate;
    private String patShowInfo1;
    private String patShowInfo2;
    private String patInfo1;
    private String patInfo2;
    private String patSex;
    private String patHeight;
    private String patWeight;
    private String patRace;
    private String patEmail;
    private String patFax;
    private String patUeberweis;
    private String patRechCopyFlag;
    private String creationDate;
    private String modificationDate;
    private String creationUserID;
    private String modificationUserID;
    private String patInsNumber1;
    private String patInsNumber2;
    private String patInsNumber3;
    private String patInsNumber4;
    private String patInsClass1;
    private String patInsClass2;
    private String patInsClass3;
    private String patInsClass4;
    private String patNatel;
    private String patNatelPriv;
    private String patEmailPriv;
    private String adrNumKasse;
    private String adrNumSUVA;
    private String adrNumIns1;
    private String adrNumIns2;
    private String adrNumEmpl;
    private String billTypeKasse;
    private String billTypeSUVA;
    private String billTypeVers1;
    private String billTypeVers2;
    private String defaultBilling;
    private String adrNumReferrer;
    private String EAN;
    private String reserve;
    private String adrNumStammGarant;
    private String flag;
    private String strReserve1;
    private String strReserve2;
    private String longReserve1;
    private String longReserve2;
    private String patSaldo;
    private String patStatus;
    private String defaultProvider;

    public Patient() {
        this.activities = new ArrayList();
    }
    
    public List getActivities() {
        return activities;
    }

    public void setActivities(List activities) {
        this.activities = activities;
    }
    
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
    
    public String getPatID() {
        return patID;
    }

    public void setPatID(String patID) {
        this.patID = patID;
    }

    public String getPatNumber() {
        return PatNumber;
    }

    public void setPatNumber(String PatNumber) {
        this.PatNumber = PatNumber;
    }

    public String getPatBirthdate() {
        return patBirthdate;
    }

    public void setPatBirthdate(String patBirthdate) {
        this.patBirthdate = patBirthdate;
    }

    public String getPatLetterSalutation() {
        return patLetterSalutation;
    }

    public void setPatLetterSalutation(String patLetterSalutation) {
        this.patLetterSalutation = patLetterSalutation;
    }

    public String getPatSalutation() {
        return patSalutation;
    }

    public void setPatSalutation(String patSalutation) {
        this.patSalutation = patSalutation;
    }

    public String getPatTitle() {
        return patTitle;
    }

    public void setPatTitle(String patTitle) {
        this.patTitle = patTitle;
    }

    public String getPatPoBox() {
        return patPoBox;
    }

    public void setPatPoBox(String patPoBox) {
        this.patPoBox = patPoBox;
    }

    public String getPatZip() {
        return patZip;
    }

    public void setPatZip(String patZip) {
        this.patZip = patZip;
    }

    public String getPatCity() {
        return patCity;
    }

    public void setPatCity(String patCity) {
        this.patCity = patCity;
    }

    public String getPatCountry() {
        return patCountry;
    }

    public void setPatCountry(String patCountry) {
        this.patCountry = patCountry;
    }

    public String getPatDiagnosis() {
        return patDiagnosis;
    }

    public void setPatDiagnosis(String patDiagnosis) {
        this.patDiagnosis = patDiagnosis;
    }

    public String getPatLanguage() {
        return patLanguage;
    }

    public void setPatLanguage(String patLanguage) {
        this.patLanguage = patLanguage;
    }

    public String getPatConsStatus() {
        return patConsStatus;
    }

    public void setPatConsStatus(String patConsStatus) {
        this.patConsStatus = patConsStatus;
    }

    public String getPatRxNumber() {
        return patRxNumber;
    }

    public void setPatRxNumber(String patRxNumber) {
        this.patRxNumber = patRxNumber;
    }

    public String getPatRecall1() {
        return patRecall1;
    }

    public void setPatRecall1(String patRecall1) {
        this.patRecall1 = patRecall1;
    }

    public String getPatRecall2() {
        return patRecall2;
    }

    public void setPatRecall2(String patRecall2) {
        this.patRecall2 = patRecall2;
    }

    public String getPatInsuranceAbbrev1() {
        return patInsuranceAbbrev1;
    }

    public void setPatInsuranceAbbrev1(String patInsuranceAbbrev1) {
        this.patInsuranceAbbrev1 = patInsuranceAbbrev1;
    }

    public String getPatInsuranceAbbrev2() {
        return patInsuranceAbbrev2;
    }

    public void setPatInsuranceAbbrev2(String patInsuranceAbbrev2) {
        this.patInsuranceAbbrev2 = patInsuranceAbbrev2;
    }

    public String getPatInsuranceAbbrev3() {
        return patInsuranceAbbrev3;
    }

    public void setPatInsuranceAbbrev3(String patInsuranceAbbrev3) {
        this.patInsuranceAbbrev3 = patInsuranceAbbrev3;
    }

    public String getPatInsuranceAbbrev4() {
        return patInsuranceAbbrev4;
    }

    public void setPatInsuranceAbbrev4(String patInsuranceAbbrev4) {
        this.patInsuranceAbbrev4 = patInsuranceAbbrev4;
    }

    public String getPatEmplAbbrev() {
        return patEmplAbbrev;
    }

    public void setPatEmplAbbrev(String patEmplAbbrev) {
        this.patEmplAbbrev = patEmplAbbrev;
    }

    public String getPatGarantInfo() {
        return patGarantInfo;
    }

    public void setPatGarantInfo(String patGarantInfo) {
        this.patGarantInfo = patGarantInfo;
    }

    public String getPatGarantID() {
        return patGarantID;
    }

    public void setPatGarantID(String patGarantID) {
        this.patGarantID = patGarantID;
    }

    public String getPatLastBehNumber() {
        return patLastBehNumber;
    }

    public void setPatLastBehNumber(String patLastBehNumber) {
        this.patLastBehNumber = patLastBehNumber;
    }

    public String getPatLastBehDate() {
        return patLastBehDate;
    }

    public void setPatLastBehDate(String patLastBehDate) {
        this.patLastBehDate = patLastBehDate;
    }

    public String getPatShowInfo1() {
        return patShowInfo1;
    }

    public void setPatShowInfo1(String patShowInfo1) {
        this.patShowInfo1 = patShowInfo1;
    }

    public String getPatShowInfo2() {
        return patShowInfo2;
    }

    public void setPatShowInfo2(String patShowInfo2) {
        this.patShowInfo2 = patShowInfo2;
    }

    public String getPatInfo1() {
        return patInfo1;
    }

    public void setPatInfo1(String patInfo1) {
        this.patInfo1 = patInfo1;
    }

    public String getPatInfo2() {
        return patInfo2;
    }

    public void setPatInfo2(String patInfo2) {
        this.patInfo2 = patInfo2;
    }

    public String getPatSex() {
        return patSex;
    }

    public void setPatSex(String patSex) {
        this.patSex = patSex;
    }

    public String getPatHeight() {
        return patHeight;
    }

    public void setPatHeight(String patHeight) {
        this.patHeight = patHeight;
    }

    public String getPatWeight() {
        return patWeight;
    }

    public void setPatWeight(String patWeight) {
        this.patWeight = patWeight;
    }

    public String getPatRace() {
        return patRace;
    }

    public void setPatRace(String patRace) {
        this.patRace = patRace;
    }

    public String getPatEmail() {
        return patEmail;
    }

    public void setPatEmail(String patEmail) {
        this.patEmail = patEmail;
    }

    public String getPatFax() {
        return patFax;
    }

    public void setPatFax(String patFax) {
        this.patFax = patFax;
    }

    public String getPatUeberweis() {
        return patUeberweis;
    }

    public void setPatUeberweis(String patUeberweis) {
        this.patUeberweis = patUeberweis;
    }

    public String getPatRechCopyFlag() {
        return patRechCopyFlag;
    }

    public void setPatRechCopyFlag(String patRechCopyFlag) {
        this.patRechCopyFlag = patRechCopyFlag;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getCreationUserID() {
        return creationUserID;
    }

    public void setCreationUserID(String creationUserID) {
        this.creationUserID = creationUserID;
    }

    public String getModificationUserID() {
        return modificationUserID;
    }

    public void setModificationUserID(String modificationUserID) {
        this.modificationUserID = modificationUserID;
    }

    public String getPatInsNumber1() {
        return patInsNumber1;
    }

    public void setPatInsNumber1(String patInsNumber1) {
        this.patInsNumber1 = patInsNumber1;
    }

    public String getPatInsNumber2() {
        return patInsNumber2;
    }

    public void setPatInsNumber2(String patInsNumber2) {
        this.patInsNumber2 = patInsNumber2;
    }

    public String getPatInsNumber3() {
        return patInsNumber3;
    }

    public void setPatInsNumber3(String patInsNumber3) {
        this.patInsNumber3 = patInsNumber3;
    }

    public String getPatInsNumber4() {
        return patInsNumber4;
    }

    public void setPatInsNumber4(String patInsNumber4) {
        this.patInsNumber4 = patInsNumber4;
    }

    public String getPatInsClass1() {
        return patInsClass1;
    }

    public void setPatInsClass1(String patInsClass1) {
        this.patInsClass1 = patInsClass1;
    }

    public String getPatInsClass2() {
        return patInsClass2;
    }

    public void setPatInsClass2(String patInsClass2) {
        this.patInsClass2 = patInsClass2;
    }

    public String getPatInsClass3() {
        return patInsClass3;
    }

    public void setPatInsClass3(String patInsClass3) {
        this.patInsClass3 = patInsClass3;
    }

    public String getPatInsClass4() {
        return patInsClass4;
    }

    public void setPatInsClass4(String patInsClass4) {
        this.patInsClass4 = patInsClass4;
    }

    public String getPatNatel() {
        return patNatel;
    }

    public void setPatNatel(String patNatel) {
        this.patNatel = patNatel;
    }

    public String getPatNatelPriv() {
        return patNatelPriv;
    }

    public void setPatNatelPriv(String patNatelPriv) {
        this.patNatelPriv = patNatelPriv;
    }

    public String getPatEmailPriv() {
        return patEmailPriv;
    }

    public void setPatEmailPriv(String patEmailPriv) {
        this.patEmailPriv = patEmailPriv;
    }

    public String getAdrNumKasse() {
        return adrNumKasse;
    }

    public void setAdrNumKasse(String adrNumKasse) {
        this.adrNumKasse = adrNumKasse;
    }

    public String getAdrNumSUVA() {
        return adrNumSUVA;
    }

    public void setAdrNumSUVA(String adrNumSUVA) {
        this.adrNumSUVA = adrNumSUVA;
    }

    public String getAdrNumIns1() {
        return adrNumIns1;
    }

    public void setAdrNumIns1(String adrNumIns1) {
        this.adrNumIns1 = adrNumIns1;
    }

    public String getAdrNumIns2() {
        return adrNumIns2;
    }

    public void setAdrNumIns2(String adrNumIns2) {
        this.adrNumIns2 = adrNumIns2;
    }

    public String getAdrNumEmpl() {
        return adrNumEmpl;
    }

    public void setAdrNumEmpl(String adrNumEmpl) {
        this.adrNumEmpl = adrNumEmpl;
    }

    public String getBillTypeKasse() {
        return billTypeKasse;
    }

    public void setBillTypeKasse(String billTypeKasse) {
        this.billTypeKasse = billTypeKasse;
    }

    public String getBillTypeSUVA() {
        return billTypeSUVA;
    }

    public void setBillTypeSUVA(String billTypeSUVA) {
        this.billTypeSUVA = billTypeSUVA;
    }

    public String getBillTypeVers1() {
        return billTypeVers1;
    }

    public void setBillTypeVers1(String billTypeVers1) {
        this.billTypeVers1 = billTypeVers1;
    }

    public String getBillTypeVers2() {
        return billTypeVers2;
    }

    public void setBillTypeVers2(String billTypeVers2) {
        this.billTypeVers2 = billTypeVers2;
    }

    public String getDefaultBilling() {
        return defaultBilling;
    }

    public void setDefaultBilling(String defaultBilling) {
        this.defaultBilling = defaultBilling;
    }

    public String getAdrNumReferrer() {
        return adrNumReferrer;
    }

    public void setAdrNumReferrer(String adrNumReferrer) {
        this.adrNumReferrer = adrNumReferrer;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String ean) {
        this.EAN = ean;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getAdrNumStammGarant() {
        return adrNumStammGarant;
    }

    public void setAdrNumStammGarant(String adrNumStammGarant) {
        this.adrNumStammGarant = adrNumStammGarant;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStrReserve1() {
        return strReserve1;
    }

    public void setStrReserve1(String strReserve1) {
        this.strReserve1 = strReserve1;
    }

    public String getStrReserve2() {
        return strReserve2;
    }

    public void setStrReserve2(String strReserve2) {
        this.strReserve2 = strReserve2;
    }

    public String getLongReserve1() {
        return longReserve1;
    }

    public void setLongReserve1(String longReserve1) {
        this.longReserve1 = longReserve1;
    }

    public String getLongReserve2() {
        return longReserve2;
    }

    public void setLongReserve2(String longReserve2) {
        this.longReserve2 = longReserve2;
    }

    public String getPatSaldo() {
        return patSaldo;
    }

    public void setPatSaldo(String patSaldo) {
        this.patSaldo = patSaldo;
    }

    public String getPatStatus() {
        return patStatus;
    }

    public void setPatStatus(String patStatus) {
        this.patStatus = patStatus;
    }

    public String getDefaultProvider() {
        return defaultProvider;
    }

    public void setDefaultProvider(String defaultProvider) {
        this.defaultProvider = defaultProvider;
    }
    
    
}
