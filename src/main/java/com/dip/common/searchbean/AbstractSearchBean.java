package com.dip.common.searchbean;


import com.dip.common.constant.OrderConstants;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSearchBean<KeyType> implements SearchBean<KeyType>{
    private Set<KeyType> keySet;
    private OrderConstants orderBy = OrderConstants.ASC;
    private String sortBy;

    public OrderConstants getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderConstants orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setKey(final KeyType key) {
        if(keySet == null) {
            keySet = new HashSet<KeyType>();
        }
        keySet.add(key);
    }

    public Set<KeyType> getKeys() {
        return keySet;
    }

    public void addKey(final KeyType key) {
        if(this.keySet == null) {
            this.keySet = new HashSet<KeyType>();
        }
        this.keySet.add(key);
    }

    public boolean hasMultipleKeys() {
        return (keySet != null && keySet.size() > 1);
    }

    public void setKeys(final Set<KeyType> keySet) {
        this.keySet = keySet;
    }

    public KeyType getKey() {
        return (CollectionUtils.isNotEmpty(keySet)) ? keySet.iterator().next() : null;
    }

    protected AbstractSearchBean(final KeyType key) {
        if(this.keySet == null) {
            this.keySet = new HashSet<KeyType>();
        }
        this.keySet.add(key);
    }

    protected AbstractSearchBean() {
    }
}
