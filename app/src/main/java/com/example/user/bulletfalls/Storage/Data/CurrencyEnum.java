package com.example.user.bulletfalls.Storage.Data;

public enum CurrencyEnum {
    MysteryCoin("Mystery Coin"),Connifer("Conifer symbol");


    private final String text;

    /**
     * @param text
     */
    CurrencyEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
