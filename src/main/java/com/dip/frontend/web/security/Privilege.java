package com.sulin.frontend.web.security;

import com.sulin.frontend.constant.SulinURL;

/**
 * Created by: Alexander Duckardt Date: 8/2/14.
 */
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

    public static final Privilege COUNTRY_LIST = Privilege.getReadOnlyPrivilege(SulinURL.COUNTRY_LIST);
    public static final Privilege COUNTRY_VIEW = Privilege.getReadOnlyPrivilege(SulinURL.COUNTRY);
    public static final Privilege COUNTRY_EDIT = Privilege.getFullPrivilege(SulinURL.COUNTRY);

    public static final Privilege USER_LIST = Privilege.getReadOnlyPrivilege(SulinURL.USER_LIST);
    public static final Privilege USER_VIEW = Privilege.getReadOnlyPrivilege(SulinURL.USER);
    public static final Privilege USER_EDIT = Privilege.getFullPrivilege(SulinURL.USER);
    public static final Privilege USER_SEARCH = Privilege.getFullPrivilege(SulinURL.USER_SEARCH);
    public static final Privilege USER_PASSWORD = Privilege.getFullPrivilege(SulinURL.USER_PASSWORD);
    // TODO: REFACTOR - NEED TO MAKE AJAX CALL FOR SEARCH INSREAD OF SEPARATE

    public static final Privilege LOGIN = Privilege.getPublicPrivilege(SulinURL.LOGIN);
    public static final Privilege LOGOUT = Privilege.getPublicPrivilege(SulinURL.LOGOUT);
    public static final Privilege TERMS_LIST = Privilege.getPublicPrivilege(SulinURL.TERMS_LIST);
    public static final Privilege INSTRUCTIONS_LIST = Privilege.getPublicPrivilege(SulinURL.INSTRUCTIONS_LIST);
    public static final Privilege CONTACTS = Privilege.getPublicPrivilege(SulinURL.CONTACTS);
    public static final Privilege SERVICES_AMOUNT = Privilege.getPublicPrivilege(SulinURL.SERVICES_AMOUNT);

    public static final Privilege START = Privilege.getReadOnlyPrivilege(SulinURL.START);
    public static final Privilege INDEX = Privilege.getReadOnlyPrivilege(SulinURL.INDEX);

    public static final Privilege MARKS_SEARCH = Privilege.getReadOnlyPrivilege(SulinURL.MARKS_SEARCH);

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
