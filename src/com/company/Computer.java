package com.company;
import java.math.BigInteger;
import java.util.Arrays;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Computer extends Player {

    public String computerMove;
    public String[] arrayFromUserMove = new String[availableMoves.size()];
    public byte[] hmacSha256 = null;
    public byte[] secretKey = null;

    public Computer(String[] moves) {
        super(moves);
    }

    public String getMove() {
        int randomMove = getRandomMove();
        secretKey = getSecretKey();
        calcHmacSha256(secretKey, availableMoves.get(randomMove));
        return computerMove = availableMoves.get(randomMove);
    }

    public int getRandomMove() {
        SecureRandom random = new SecureRandom();
        int randomMove = random.nextInt(availableMoves.size());
        return randomMove;
    }

    public byte[] getSecretKey() {
        SecureRandom ranGen = new SecureRandom();
        byte[] secretKey = new byte[16];
        ranGen.nextBytes(secretKey);
        return secretKey;
    }

    public byte[] calcHmacSha256(byte[] secretKey, String message) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(key);
            byte[] bytesFromMessage = message.getBytes("UTF-8");
            byte[] bytesFromSecretKey = String.format("%032x", new BigInteger(1, secretKey)).getBytes("UTF-8");
            mac.update(bytesFromMessage);
            mac.update(bytesFromSecretKey);
            hmacSha256 = mac.doFinal();
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }

    public void printHmac() {
        System.out.println(String.format("HMAC: %032x", new BigInteger(1, hmacSha256)));
    }

    public void printHmacKey() {
        System.out.println(String.format("HMAC key: %032x", new BigInteger(1, secretKey)));
    }

    public String[] getArrayFromUserMove(String userMove) {
        int start = availableMoves.indexOf(userMove);
        for (int i = 0; i < availableMoves.size(); i++) {
            arrayFromUserMove[i] = availableMoves.get((start + i) % availableMoves.size());
        }
        return arrayFromUserMove;
    }

    public String getWinner(String userMove) {
        getArrayFromUserMove(userMove);
        int gran = availableMoves.size() / 2;
        if (Arrays.asList(arrayFromUserMove).indexOf(computerMove) == 0){
            return "Draw...";
        }
        return Arrays.asList(arrayFromUserMove).indexOf(computerMove) <= gran ?
               "Computer win \uD83D\uDCBB" : "You win! \uD83D\uDE0A";
    }

}
