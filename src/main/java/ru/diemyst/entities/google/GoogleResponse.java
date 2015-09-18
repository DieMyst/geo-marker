package ru.diemyst.entities.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by diemyst on 18.09.15.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleResponse {
    private Result[] results;
    private String status ;
    public Result[] getResults() {
        return results;
    }
    public void setResults(Result[] results) {
        this.results = results;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
