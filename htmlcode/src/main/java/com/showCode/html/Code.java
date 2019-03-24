package com.showCode.html;

public class Code {
    public enum  __ProgrammingLanguage{
        AUTO;
        private __ProgrammingLanguage(){
        }
    }

    String code;
    __ProgrammingLanguage programmingLanguage;

    public Code(String code) {
        this(code, __ProgrammingLanguage.AUTO);
    }

    public Code(String code, __ProgrammingLanguage programmingLanguage){
        this.code = code;
        this.programmingLanguage = programmingLanguage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public __ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(__ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
