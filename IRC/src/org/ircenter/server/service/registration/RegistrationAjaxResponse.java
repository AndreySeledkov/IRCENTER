package org.ircenter.server.service.registration;

public class RegistrationAjaxResponse {

    private Boolean complete = true;
    private String nameError = "";
    private String userLoginError = "";
    private String passwordError = "";
    private String passwordAgainError = "";
    private String agreementError = "";
    private String cityIdError = "";
    private String licenseError = "";
    private String captchaError = "";


    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getUserLoginError() {
        return userLoginError;
    }

    public void setUserLoginError(String userLoginError) {
        this.userLoginError = userLoginError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordAgainError() {
        return passwordAgainError;
    }

    public void setPasswordAgainError(String passwordAgainError) {
        this.passwordAgainError = passwordAgainError;
    }

    public String getAgreementError() {
        return agreementError;
    }

    public void setAgreementError(String agreementError) {
        this.agreementError = agreementError;
    }

    public String getCityIdError() {
        return cityIdError;
    }

    public void setCityIdError(String cityIdError) {
        this.cityIdError = cityIdError;
    }

    public String getLicenseError() {
        return licenseError;
    }

    public void setLicenseError(String licenseError) {
        this.licenseError = licenseError;
    }

    public String getCaptchaError() {
        return captchaError;
    }

    public void setCaptchaError(String captchaError) {
        this.captchaError = captchaError;
    }
}
