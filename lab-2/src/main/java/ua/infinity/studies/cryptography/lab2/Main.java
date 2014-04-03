package ua.infinity.studies.cryptography.lab2;

import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 24.03.2014
 * Time: 20:40:04
 *
 * @author infinity
 * 
 */
public class Main {

    /**
     * <p>The Logger.
     * 
     * */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * @param args
     * 
     * */
    public static void main(String[] args) {
	// ####################################################################
	// Part #1
	logger.info("Part #1");
	double P = 1e-4;
	double V = 100;
	double T = 12;
	double Sstar = Math.ceil(V * T / P);
	logger.info("S* = {}", Sstar);
	String A = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	double L = Math.log10(Sstar) / Math.log10(A.length());
	logger.info("L = {}", L);
	int passwordCount = 5;
	int passLength = (int) Math.ceil(L);
	for (int i=0;i<passwordCount;i++) {
	    String password = RandomStringUtils.random(passLength, A);
	    logger.info(" - {}", password);
	}
	// ####################################################################
	
	// ####################################################################
	// Part #2
	logger.info("Part #2");
	logger.info("Введите свой идентификатор: ");
	Scanner scanIn = new Scanner(System.in);
	String userId = scanIn.nextLine();
	scanIn.close();
	int N = userId.length();
	int M = 10;
	StringBuilder password = new StringBuilder(M);
	password.append(RandomStringUtils.random(2, A));
	int Q = N % 6;
	password.append(RandomStringUtils.random(M-Q-3, A.toLowerCase()));
	password.append(RandomStringUtils.randomNumeric(Q+1));
	logger.info("Create password ({}): {}", password.length(), password);
	// ####################################################################
    }
}
