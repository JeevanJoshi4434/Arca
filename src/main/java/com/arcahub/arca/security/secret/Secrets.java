package com.arcahub.arca.security.secret;

public class Secrets {

    private Secrets(){}

    private static String getEvnOrDefault(String envVar, String defaultValue){
        String value = System.getenv(envVar);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public static final String JWT_SECRET = getEvnOrDefault("JWT_SECRET", "DEFAULT@123");
    public static final long JWT_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long ACCESS_JWT_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long REFRESH_JWT_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long EMAIL_VERIFICATION_JWT_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long PASSWORD_RESET_JWT_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long JWT_VALIDATOR_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long JWT_MINUTES_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final long JWT_HOURS_EXPIRE_IN = Long.parseLong(getEvnOrDefault("JWT_EXPIRE_IN", "86400000"));
    public static final String DB_PASSWORD = getEvnOrDefault("DB_PASSWORD", "default_password");
    public static final String KAFKA_PASSWORD = getEvnOrDefault("KAFKA_PASSWORD", "default-kafka-password");
}
