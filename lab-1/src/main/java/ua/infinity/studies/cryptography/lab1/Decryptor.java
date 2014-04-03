package ua.infinity.studies.cryptography.lab1;

import java.math.BigInteger;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 25 февр. 2014 г. Time: 8:23:12
 * 
 * @author infinity
 * 
 */
public class Decryptor {

    /**
     * <p>
     * The Logger.
     * 
     * */
    private static final Logger logger = LoggerFactory.getLogger(Decryptor.class);

    /***/
    private final BigInteger n;

    /***/
    private final BigInteger d;

    /***/
    private Charset charset = Charset.forName("utf-8");

    /**
     * @param n
     * @param d
     * 
     * */
    public Decryptor(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    /**
     * @param n
     * @param d
     * @param charset
     * 
     * */
    public Decryptor(BigInteger n, BigInteger d, Charset charset) {
        this.n = n;
        this.d = d;
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
    public String decrypt(String cipher) {
        logger.debug("Try to decrypt cipher {}", cipher);
        BigInteger c = new BigInteger(cipher);
        c = c.modPow(d, n);
        String message = new String(Base64.decodeBase64(c.toByteArray()), charset);
        logger.debug("Message of cipher {} = {}", cipher, message);
        return message;
    }
}
