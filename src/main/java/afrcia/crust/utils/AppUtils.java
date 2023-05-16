package afrcia.crust.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

    public static boolean isValidToken(LocalDateTime expiryDate) {
        long minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), expiryDate);
        return minutes >= 0;
    }

    public static String generateTestSecretKey(){
        String secretKey = "sk_test_";
        return secretKey + UUID.randomUUID() + UUID.randomUUID();
    }

    public static String generateTestPublicKey(){
        String secretKey = "pk_test_";
        return secretKey + UUID.randomUUID() + UUID.randomUUID();
    }

    public static String generateLiveSecretKey(){
        String secretKey = "sk_live_";
        return secretKey + UUID.randomUUID() + UUID.randomUUID();
    }

    public static String generateLivePublicKey(){
        String secretKey = "pk_live_";
        return secretKey + UUID.randomUUID() + UUID.randomUUID();
    }
}
