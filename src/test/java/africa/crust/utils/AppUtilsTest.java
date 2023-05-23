package africa.crust.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppUtilsTest {


    @Test
    public void testIsValidTokenWithValidExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusMinutes(10);
        boolean result = AppUtils.isValidToken(expiryDate);
        assertTrue(result);
    }

    @Test
    public void testIsValidTokenWithExpiredExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.minusMinutes(10);
        boolean result = AppUtils.isValidToken(expiryDate);
        assertFalse(result);
    }

    @Test
    public void testIsValidTokenWithCurrentExpiryDate() {
        LocalDateTime now = LocalDateTime.now();
        boolean result = AppUtils.isValidToken(now);
        assertTrue(result);
    }

    @Test
    public void testGenerateTestSecretKey() {
        String secretKey = AppUtils.generateTestSecretKey();
        assertNotNull(secretKey);
        assertTrue(secretKey.startsWith("sk_test_"));
    }

    @Test
    public void testGenerateLiveSecretKey() {
        String secretKey = AppUtils.generateLiveSecretKey();
        assertNotNull(secretKey);
        assertTrue(secretKey.startsWith("sk_live_"));
    }

    @Test
    public void testGenerateLivePublicKey() {
        String secretKey = AppUtils.generateLivePublicKey();
        assertNotNull(secretKey);
        assertTrue(secretKey.startsWith("pk_live_"));
    }

    @Test
    public void testGenerateTestPublicKey() {
        String secretKey = AppUtils.generateTestPublicKey();
        assertNotNull(secretKey);
        assertTrue(secretKey.startsWith("pk_test_"));
    }

}