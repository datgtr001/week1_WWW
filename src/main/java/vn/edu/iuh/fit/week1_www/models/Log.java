package vn.edu.iuh.fit.week1_www.models;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private String logId;
    private Account account;
    private Date loginDate;
    private Date logoutDate;
    private String note;

    public Log() {
    }

    public Log(String logId, Account account, Date loginDate, Date logoutDate, String note) {
        this.logId = logId;
        this.account = account;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
        this.note = note;
    }

    public String getLogId() {
        return logId;
    }

    public Account getAccount() {
        return account;
    }

    public java.sql.Date getLoginDate() {
        return loginDate;
    }

    public java.sql.Date getLogoutDate() {
        return logoutDate;
    }

    public String getNote() {
        return note;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setAccount(Account account) {
        this.account = account;
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
                ", account=" + account +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                ", note='" + note + '\'' +
                '}';
    }
}
