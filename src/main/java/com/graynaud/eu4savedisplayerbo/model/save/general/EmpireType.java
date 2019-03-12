package com.graynaud.eu4savedisplayerbo.model.save.general;

public enum EmpireType {
    HRE("\nempire={", true),
    CELESTIAL("\ncelestial_empire={", false);

    private String key;

    private boolean hasElectors;

    EmpireType(String key, boolean hasElectors) {
        this.key = key;
        this.hasElectors = hasElectors;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isHasElectors() {
        return hasElectors;
    }

    public void setHasElectors(boolean hasElectors) {
        this.hasElectors = hasElectors;
    }
}
