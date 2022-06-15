package com.example.euihyeon.hira;

public class AppraisalData {
    private String yadmName;
    private String addr;
    private String asmGrd2;
    private String asmGrd3;
    private String asmGrd5;
    private String asmGrd6;
    private String grade;

    AppraisalData() {
        yadmName = "";
        addr = "";
        asmGrd2 = "";
        asmGrd3 = "";
        asmGrd5 = "";
        asmGrd6 = "";
        grade = "";
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setYadmName(String yadmName) {
        this.yadmName = yadmName;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setAsmGrd2(String asmGrd2) {
        this.asmGrd2 = asmGrd2;
    }

    public void setAsmGrd3(String asmGrd3) {
        this.asmGrd3 = asmGrd3;
    }

    public void setAsmGrd5(String asmGrd5) {
        this.asmGrd5 = asmGrd5;
    }

    public void setAsmGrd6(String asmGrd6) {
        this.asmGrd6 = asmGrd6;
    }

    public String getYadmName() {
        return yadmName;
    }

    public String getAddr() {
        return addr;
    }

    public String getAsmGrd2() {
        return asmGrd2;
    }

    public String getAsmGrd3() {
        return asmGrd3;
    }

    public String getAsmGrd5() {
        return asmGrd5;
    }

    public String getAsmGrd6() {
        return asmGrd6;
    }

    public String getGrade() {
        return grade;
    }

    public void clear() {
        yadmName = "";
        addr = "";
        asmGrd2 = "";
        asmGrd3 = "";
        asmGrd5 = "";
        asmGrd6 = "";
        grade = "";
    }


}