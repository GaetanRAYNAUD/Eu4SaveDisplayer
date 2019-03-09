package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.util.stream.Stream;

public enum DLC {
    CONQUEST_OF_PARADISE("Conquest of Paradise"),
    WEALTH_OF_NATIONS("Wealth of Nations"),
    RES_PUBLICA("Res Publica"),
    ART_OF_WAR("Art of War"),
    EL_DORADO("El Dorado"),
    COMMON_SENSE("Common Sense"),
    THE_COSSACKS("The Cossacks"),
    MARE_NOSTRUM("Mare Nostrum"),
    RIGHTS_OF_MAN("Rights of Man"),
    MANDATE_OF_HEAVEN("Mandate of Heaven"),
    THIRD_ROME("Third Rome"),
    CRADLE_OF_CIVILIZATION("Cradle of Civilization"),
    RULE_BRITANNIA("Rule Britannia"),
    DHARMA("Dharma"),
    DHARMA_INDIAN_SULTANATE_PACK("Dharma - Indian Sultanate Pack"),
    GOLDEN_CENTURY("Golden Century");

    private String name;

    DLC (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public static DLC getByName(String name) {
        return Stream.of(DLC.values()).filter(dlc -> dlc.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
