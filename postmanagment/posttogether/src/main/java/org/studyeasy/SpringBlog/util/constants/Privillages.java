package org.studyeasy.SpringBlog.util.constants;

public enum Privillages {
    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l, "ACCESS_ADMIN_PANEL");

    private Long id;
    private String privillage;
    private Privillages(Long id, String privillage) {
        this.id = id;
        this.privillage = privillage;
    }
    public Long getId() {
        return id;
    }
    public String getPrivillage() {
        return privillage;
    }
    
}
