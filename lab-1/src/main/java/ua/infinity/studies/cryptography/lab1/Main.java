package ua.infinity.studies.cryptography.lab1;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 23.02.2014 Time: 14:26:04
 * 
 * @author infinity
 * 
 */
public class Main {

    /**
     * <p>
     * The Logger.
     * 
     * */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final Random RANDOM = new SecureRandom();

    private static final int KEY_LENGTH = 1024;

    /***/
    private static BigInteger calculateE(BigInteger fi) {
        for (;;) {
            BigInteger result = BigInteger.probablePrime(KEY_LENGTH, RANDOM);
            if (result.gcd(fi).equals(BigInteger.ONE)) {
                return result;
            }
        }
    }

    /**
     * @param args
     * 
     * */
    public static void main(String[] args) {
        BigInteger p = BigInteger.probablePrime(KEY_LENGTH, RANDOM);
        logger.info("p={}", p);
        BigInteger q = BigInteger.probablePrime(KEY_LENGTH, RANDOM);
        logger.info("q={}", q);
        BigInteger n = p.multiply(q);
        logger.info("n={}", n);
        BigInteger fi = p.add(BigInteger.ONE.negate()).multiply(q.add(BigInteger.ONE.negate()));
        logger.info("fi={}", fi);
        BigInteger e = calculateE(fi);
        logger.info("e={}", e);
        BigInteger d = e.modInverse(fi);
        logger.info("d={}", d);
        Charset charset = Charset.forName("UTF-8");
        Cryptor cryptor = new Cryptor(n, e, charset);
        Decryptor decryptor = new Decryptor(n, d, charset);
        List<String> messages = new LinkedList<>();
        messages.add("GENERATOR");
        messages.add("Hello, World!");
        messages.add("Привет, мир!");
        messages.add("Привіт, світ!");
        messages.add("你好世界");
        for (String message : messages) {
            String cipher = cryptor.encrypt(message);
            String decryptMessage = decryptor.decrypt(cipher);
            logger.info("Message={}", message);
            logger.info("Decrypted message={}\n", decryptMessage);
        }
    }
}
