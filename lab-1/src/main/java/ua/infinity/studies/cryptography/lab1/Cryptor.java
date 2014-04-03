package ua.infinity.studies.cryptography.lab1;

import java.math.BigInteger;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 25 февр. 2014 г. Time: 8:22:53
 * 
 * @author infinity
 * 
 */
public class Cryptor {

    /**
     * <p>
     * The Logger.
     * 
     * */
    private static final Logger logger = LoggerFactory.getLogger(Cryptor.class);

    /***/
    private final BigInteger n;

    /***/
    private final BigInteger e;

    /***/
    private Charset charset = Charset.forName("utf-8");

    /**
     * @param n
     * @param e
     * 
     * */
    public Cryptor(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    /**
     * @param n
     * @param e
     * @param charset
     * 
     * */
    public Cryptor(BigInteger n, BigInteger e, Charset charset) {
        this.n = n;
        this.e = e;
        this.charset = charset;
    }

    /**
     * @return The charset.
     * 
     * */
    public Charset getCharset() {
        return charset;
    }

    /**
     * @param charset
     *            - the charset to set
     * 
     * */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /***/
    public String encrypt(String message) {
        logger.debug("Try to encode message: {}", message);
        byte[] bytes = Base64.encodeBase64(message.getBytes(charset));
        BigInteger c = new BigInteger(bytes);
        c = c.modPow(e, n);
        logger.debug("Cipher of message {} = {}", message, c);
        return c.toString();
    }
}
