package com.dip.frontend.web.security;

import com.dip.frontend.constant.DipURL;


public class Privilege implements Cloneable {
    private String URL;
    private boolean isOpen = false;
    private boolean canWrite = false;

    private Privilege(String URL, boolean canWrite, boolean isOpen) {
        this.URL = URL;
        this.isOpen = isOpen;
        this.canWrite = canWrite;
    }

    private static Privilege getPublicPrivilege(String URL) {
        return new Privilege(URL, true, true);
    }

    private static Privilege getReadOnlyPrivilege(String URL) {
        return new Privilege(URL, false, false);
    }

    private static Privilege getFullPrivilege(String URL) {
        return new Privilege(URL, true, false);
    }

    public static final Privilege COUNTRY_LIST = Privilege.getReadOnlyPrivilege(DipURL.COUNTRY_LIST);
    public static final Privilege COUNTRY_VIEW = Privilege.getReadOnlyPrivilege(DipURL.COUNTRY);
    public static final Privilege COUNTRY_EDIT = Privilege.getFullPrivilege(DipURL.COUNTRY);

    public static final Privilege USER_LIST = Privilege.getReadOnlyPrivilege(DipURL.USER_LIST);
    public static final Privilege USER_VIEW = Privilege.getReadOnlyPrivilege(DipURL.USER);
    public static final Privilege USER_EDIT = Privilege.getFullPrivilege(DipURL.USER);
    public static final Privilege USER_SEARCH = Privilege.getFullPrivilege(DipURL.USER_SEARCH);
    public static final Privilege USER_PASSWORD = Privilege.getFullPrivilege(DipURL.USER_PASSWORD);
    // TODO: REFACTOR - NEED TO MAKE AJAX CALL FOR SEARCH INSREAD OF SEPARATE

    public static final Privilege LOGIN = Privilege.getPublicPrivilege(DipURL.LOGIN);
    public static final Privilege LOGOUT = Privilege.getPublicPrivilege(DipURL.LOGOUT);
    public static final Privilege TERMS_LIST = Privilege.getPublicPrivilege(DipURL.TERMS_LIST);
    public static final Privilege INSTRUCTIONS_LIST = Privilege.getPublicPrivilege(DipURL.INSTRUCTIONS_LIST);
    public static final Privilege CONTACTS = Privilege.getPublicPrivilege(DipURL.CONTACTS);
    public static final Privilege SERVICES_AMOUNT = Privilege.getPublicPrivilege(DipURL.SERVICES_AMOUNT);

    public static final Privilege START = Privilege.getReadOnlyPrivilege(DipURL.START);
    public static final Privilege INDEX = Privilege.getReadOnlyPrivilege(DipURL.INDEX);

    public static final Privilege MARKS_SEARCH = Privilege.getReadOnlyPrivilege(DipURL.MARKS_SEARCH);

    public String getURL() {
        return URL;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    @Override
    public Privilege clone() throws CloneNotSupportedException {
        return new Privilege(this.URL, this.canWrite, this.isOpen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Privilege privilege = (Privilege) o;

        if (canWrite != privilege.canWrite)
            return false;
        if (isOpen != privilege.isOpen)
            return false;
        if (URL != null ? !URL.equals(privilege.URL) : privilege.URL != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = URL != null ? URL.hashCode() : 0;
        result = 31 * result + (isOpen ? 1 : 0);
        result = 31 * result + (canWrite ? 1 : 0);
        return result;
    }
}
