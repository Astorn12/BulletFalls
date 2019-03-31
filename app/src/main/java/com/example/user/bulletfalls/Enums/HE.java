package com.example.user.bulletfalls.Enums;

public enum HE {
    EATER("Eater"),
    RINOR("Rinor"),
    PANSYK("Pansyk"),
    MABELPINES("Mabel Pines"),
    DIPPERPINES("Dipper Pines"),
    SOOSRAMIREZ("Soos Ramirez"),
    STANFORDPINNES("Stanford Pines"),
    WANDYCOULDRON("Wendy Couldron"),
    WADDLES("Waddles"),
    GRENDA("Grenda"),
    LOGLANDGIRL("Log Land Girl"),
    QUENTINTREBLEY("Quentin Trembley"),
    CANDYCHIU("Candy Chiu"),
    OLDMANMCGUCKET("Old Man McGucket"),
    MABLEWITHGRAPPLINGHOOK("Mabel with Gramppling Hook");




    private final String value;

    HE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
