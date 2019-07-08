package com.example.user.bulletfalls.Database.Data;

public enum CurrencyEnum {
    MysteryCoin("Mystery Coin"),Connifer("Conifer symbol");


    private final String text;

    /**
     * @param text
     */
    CurrencyEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
