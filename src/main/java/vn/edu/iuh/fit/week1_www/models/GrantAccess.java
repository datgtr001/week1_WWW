package vn.edu.iuh.fit.week1_www.models;

import java.io.Serializable;

public class GrantAccess implements Serializable {
    private String accountId;
    private String roleId;
    private boolean is_grant;
    private String note;

    public GrantAccess(String accountId, String roleId, boolean is_grant, String note) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.is_grant = is_grant;
        this.note = note;
    }

    public GrantAccess() {
    }

    public String getAccountId() {
        return accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public boolean isIs_grant() {
        return is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setIs_grant(boolean is_grant) {
        this.is_grant = is_grant;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "accountId='" + accountId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}
