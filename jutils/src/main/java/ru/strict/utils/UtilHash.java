package ru.strict.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilHash {

    /**
     * Выполнить хеширование текста методом MD5
     * @param str Строка, которую требуется захешировать
     * @return
     */
    public static String hashMd5(String str){
        UtilLogger.info(UtilHash.class, "hashMd5 - started");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            UtilLogger.error(UtilHash.class, ex.getClass().toString(), ex.getMessage());
        }
        md.update(str.getBytes());
        byte byteData[] = md.digest();

        // Конвертируем байт в шестнадцатеричный формат
        StringBuffer hexString = new StringBuffer();
        for (byte aByteData : byteData) {
            String hex = Integer.toHexString(0xff & aByteData);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        UtilLogger.info(UtilHash.class, "hashMd5 - finished");
        return hexString.toString();
    }

    /**
     * Выполнить шифрование текста методом MD5 от Apache
     * @param str Строка, которую требуется зашифровать
     * @return
     */
    public static String hashMd5Apache(String str){
        return DigestUtils.md5Hex(str);
    }
    
    /**
     * Хэширование sha1
     *
     * @param Param строка для хеширования
     * @return hex-представление хэш-строки
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hashSha1(String Param) {
        UtilLogger.info(UtilHash.class, "hashSha1 - started");
        MessageDigest SHA = null;
        try {
            SHA = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            UtilLogger.error(UtilHash.class, ex.getClass().toString(), ex.getMessage());
        }
        SHA.reset();
        try {
            SHA.update(Param.getBytes("UTF-8"), 0, Param.length());
        } catch (UnsupportedEncodingException ex) {
            UtilLogger.error(UtilHash.class, ex.getClass().toString(), ex.getMessage());
        }
        byte[] sha1hash = SHA.digest();
        UtilLogger.info(UtilHash.class, "hashSha1 - finished");
        return bytesToHexStr(sha1hash);
    }

    /**
     * Преобразование байтового массива в hex-строку
     *
     * @param raw
     * @return String
     */
    private static String bytesToHexStr(byte[] raw) {
        UtilLogger.info(UtilHash.class, "bytesToHexStr - started");
        char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int length = raw.length;
        char[] hex = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int value = (raw[i] + 256) % 256;
            int highIndex = value >> 4;
            int lowIndex = value & 0x0f;
            hex[i * 2 + 0] = kDigits[highIndex];
            hex[i * 2 + 1] = kDigits[lowIndex];
        }
        UtilLogger.info(UtilHash.class, "bytesToHexStr - finished");
        return new String(hex);
    }
}
