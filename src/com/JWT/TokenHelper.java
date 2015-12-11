package com.JWT;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.List;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.junit.Test;

/**
 * Provides static methods for creating and verifying access tokens and such.
 *
 * @author davidm
 */
public class TokenHelper {
    //NotReallyImportant
    private static final String AUDIENCE = "ppfix";
    //YourCompanyOrAppNameHere
    private static final String ISSUER = "yunyi:ppfix";
    //LongAndHardToGuessValueWithSpecialCharacters,like:@^($%*$%"
    private static final String SIGNING_KEY = "zjc-ppfix";

    /**
     * Creates a json web token which is a digitally signed token that contains a payload (e.g. userId to identify
     * the user). The signing key is secret. That ensures that the token is authentic and has not been modified.
     * Using a jwt eliminates the need to store authentication session information in a database.
     *
     * @param userId
     * @param durationDays
     * @return
     */
    public static String createJsonWebToken(String userId, Long durationDays) {
        //Current time and signing algorithm
        Calendar cal = Calendar.getInstance();
        HmacSHA256Signer signer;
        try {
            signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        //Configure JSON token
        JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
        //Expiration days
        token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + 1000L * 60L * 60L * 24L * durationDays));
        //Configure request object, which provides information of the item
        JsonObject request = new JsonObject();
        /**
         * parameters inneed,add them as below
         */
        request.addProperty("userId", userId);
        JsonObject payload = token.getPayloadAsJsonObject();
        payload.add("info", request);
        try {
            return token.serializeAndSign();
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies a json web token's validity and extracts the user id and other information from it.
     *
     * @param token
     * @return
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public static TokenInfo verifyToken(String token) {
        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());
            VerifierProvider hmacLocator = new VerifierProvider() {
                @Override
                public List<Verifier> findVerifier(String id, String key) {
                    return Lists.newArrayList(hmacVerifier);
                }
            };
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
            net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {
                @Override
                public void check(JsonObject payload) throws SignatureException {
                    // don't throw - allow anything
                }
            };
            //Ignore Audience does not mean that the Signature is ignored
            JsonTokenParser parser = new JsonTokenParser(locators,
                    checker);
            JsonToken jt;
            try {
                jt = parser.verifyAndDeserialize(token);
            } catch (SignatureException e) {
                throw new RuntimeException(e);
            }
            JsonObject payload = jt.getPayloadAsJsonObject();
            TokenInfo t = new TokenInfo();
            String issuer = payload.getAsJsonPrimitive("iss").getAsString();
            String userIdString = payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
            if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString)) {
                t.setUserId(new Long(userIdString));
                t.setIssued(new Long(payload.getAsJsonPrimitive("iat").getAsLong()));
                t.setExpires(new Long(payload.getAsJsonPrimitive("exp").getAsLong()));
                return t;
            } else {
                return null;
            }
        } catch (InvalidKeyException e1) {
            throw new RuntimeException(e1);
        }
    }

    @Test
    public void TestToken() {
        String token = createJsonWebToken("10086", 7L);
        System.out.println(token);
//        TokenInfo tokenInfo = verifyToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5dW55aTpwcGZpeCIsImF1ZCI6InBwZml4IiwiaWF0IjoxNDQ5MjAxNDY1LCJleHAiOjE0NDk4MDYyNjUsImluZm8iOnsidXNlcklkIjoiMTAwODYifX0.fHYU7HTWcF174BKgYwhjXjuYETNPjkydY86h1KPBK-Q");
//        System.out.println("tokenInfo"+tokenInfo.getUserId() + tokenInfo.getIssued() +tokenInfo.getExpires() );
    }
}