package com.core.app.model;

import javax.inject.Inject;

/**
 * Created by Ting on 17/5/31.
 */

public class UserName {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Inject
    public UserName() {

    }
}
