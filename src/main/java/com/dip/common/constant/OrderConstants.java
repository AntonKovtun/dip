package com.sulin.common.constant;

public enum OrderConstants {

    ASC("asc"),
    DESC("desc");

    private String value;

    OrderConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String val) {
        value = val;
    }

    public static OrderConstants getFromString(final String val) {
        for(final OrderConstants e : OrderConstants.values()) {
            if(e.getValue().equalsIgnoreCase(val)) {
                return e;
            }
        }
        return null;
    }

}
