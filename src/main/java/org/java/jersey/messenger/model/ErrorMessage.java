
package org.java.jersey.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
    private String documentation;

    public ErrorMessage() {

    }

    public ErrorMessage(final String errorMessage, final int errorCode, final String documentation) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    public void setDocumentation(final String documentation) {
        this.documentation = documentation;
    }

}
