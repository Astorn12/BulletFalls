package com.example.user.bulletfalls.GlobalUsage.Enums;

public enum HE  {

    MABELPINES("Mabel Pines"),
    DIPPERPINES("Dipper Pines"),
    SOOSRAMIREZ("Soos Ramirez"),
    STANFORDPINNES("Stanford Pines"),
    WANDYCOULDRON("Wendy Corduroy"),
    WADDLES("Waddles"),
    GRENDA("Grenda"),
    LOGLANDGIRL("Log Land Girl"),
    QUENTINTREBLEY("Quentin Trembley"),
    CANDYCHIU("Candy Chiu"),
    OLDMANMCGUCKET("Old Man McGucket"),
    MABLEWITHGRAPPLINGHOOK("Mabel with Gramppling Hook"),
    ROBIE("Robie"),
    SOOSSGRANDMA("Soos's Grandma"),
    PRESTONNORTHWEST("Preston Northwest"),
    PRESTONPREMIUM("Preston Premium "),
    PRISCILLANORTHWEST("Priscilla Northwest"),
    PRISCILLAPREMIUM("Priscilla Premium "),
    MRSGLEEFUL("Mrs. Gleeful"),
    BUDGLEEFUL("Bud Gleeful"),
    GIDEONGLEEFUL("Gideon Gleeful"),
    PACIFIC("Pacific Northwest");




    private final String value;

    HE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
