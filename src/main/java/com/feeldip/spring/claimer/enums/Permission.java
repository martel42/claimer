package com.feeldip.spring.claimer.enums;

public enum Permission {
    MODERATE("user: moderate, answer"),
    ADMINISTER("user: administer");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
