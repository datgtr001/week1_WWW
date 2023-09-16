package vn.edu.iuh.fit.week1_www.models;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private String logId;
    private String accountId;
    private Date loginDate;
    private Date logoutDate;
    private String note;

    public Log() {
    }

    public Log(String logId, String accountId, Date loginDate, Date logoutDate, String note) {
        this.logId = logId;
        this.accountId = accountId;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
        this.note = note;
    }

    public String getLogId() {
        return logId;
    }

    public String getAccountId() {
        return accountId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public String getNote() {
        return note;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                ", note='" + note + '\'' +
                '}';
    }
}
