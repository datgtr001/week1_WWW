package vn.edu.iuh.fit.week1_www.models;

import java.io.Serializable;

public class GrantAccess implements Serializable {
    private Account account;
    private Role role;
    private boolean is_grant;
    private String note;

    public GrantAccess(Account account, Role role, boolean is_grant, String note) {
        this.account = account;
        this.role = role;
        this.is_grant = is_grant;
        this.note = note;
    }

    public GrantAccess() {
    }

    public Account getAccount() {
        return account;
    }

    public Role getRole() {
        return role;
    }

    public boolean isIs_grant() {
        return is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setRole(Role role) {
        this.role = role;
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
                "account=" + account +
                ", role=" + role +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}
