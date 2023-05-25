package ru.strict.test;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RandomUtil {

    private final int DEFAULT_MIN_NUMBER = 1000000;
    private final int DEFAULT_MAX_NUMBER = 9999999;

    private static final String LETTERS_FOR_RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateUrl() {
        return "https://host-stub.ru/random-url/" + generateDefaultStr();
    }

    public String generateDefaultStr() {
        return generateStr(15);
    }

    public String generateStr(int length) {
        var stringBuilder = new StringBuilder();
        for (var i = 0; i < length; i++) {
            var randomLetterIndex = ThreadLocalRandom.current().nextInt(0, LETTERS_FOR_RANDOM.length()-1);
            var randomChar = LETTERS_FOR_RANDOM.charAt(randomLetterIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public int generateDefaultInt(int minNumber, int maxNumber) {
        return generateInt(DEFAULT_MIN_NUMBER, DEFAULT_MAX_NUMBER);
    }

    public int generateInt(int minNumber, int maxNumber) {
        return ThreadLocalRandom.current().nextInt(minNumber, maxNumber+1);
    }

    public float generateDefaultFloat(int minNumber , int maxNumber) {
        return generateFloat(DEFAULT_MIN_NUMBER, DEFAULT_MAX_NUMBER);
    }

    public float generateFloat(float minNumber, float maxNumber) {
        return ThreadLocalRandom.current().nextFloat(minNumber, maxNumber+1);
    }

    public byte[] generateBytes() {
        var bytesCount = ThreadLocalRandom.current().nextInt(5, 25);

        var bytes = new byte[bytesCount];
        for (var i = 0; i < bytesCount; i++) {
            var randomByte = ThreadLocalRandom.current().nextInt(1, 100) % 2;
            bytes[i] = (byte) randomByte;
        }

        return bytes;
    }
}
