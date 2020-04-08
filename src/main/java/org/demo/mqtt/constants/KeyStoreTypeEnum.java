package org.demo.mqtt.constants;

/**
 * KeyStoreTypeEnum
 *
 * @Author: taomee
 * @Date: 2020/4/8 0008 14:52
 * @Description:
 */
public enum KeyStoreTypeEnum {

    DEFAULT("DEFAULT"),
    JKS("JKS"),
    JCEKS("JCEKS"),
    PKCS_12("PKCS12"),
    BKS("BKS");
    private final String value;

    KeyStoreTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KeyStoreTypeEnum fromValue(String v) {
        for (KeyStoreTypeEnum c: KeyStoreTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
