package com.alacriti.olx_seller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValiadationUtil {
//	private static final AppLogger log = LogUtil.getLogger(ValiadationUtil.class);

	public static boolean isValidEmail(final String email) {
		Pattern pattern;
		Matcher matcher;
		if (StringUtil.isEmpty(email) || email.length() > 100)
			return false;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidIpAddress(String ipAddress) {
		if (StringUtil.isEmpty(ipAddress)) {
			return false;
		}

		final String ipv4Regex = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
		final String ipv6Regex = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
		Pattern ipv4Pattern = Pattern.compile(ipv4Regex, Pattern.CASE_INSENSITIVE);
		Pattern ipv6Pattern = Pattern.compile(ipv6Regex, Pattern.CASE_INSENSITIVE);

		Matcher ipv4Matcher = ipv4Pattern.matcher(ipAddress);

		if (ipv4Matcher.matches()) {
			return true;
		}

		Matcher ipv6Matcher = ipv6Pattern.matcher(ipAddress);
		return ipv6Matcher.matches();

	}

	public static boolean isValidZipUS(String zip) {

		return (isValidInteger(zip, 5));
	}

	public static boolean isValidZipCodeUS(String zip) {
		String regEx = "^\\d{5}(?:[-\\s]\\d{4})?$";
		return Pattern.matches(regEx, zip);
	}

	public static boolean isValidUserAgent(String userAgent) {
		if (StringUtil.isEmpty(userAgent)) {
			return false;
		}
		String regEx = ".+?[/\\s][\\d.]+";
		return Pattern.matches(regEx, userAgent);
	}

	public static boolean isValidInteger(String num, int numOfDigits) {

		String REGEX = "^[0-9]{" + Integer.toString(numOfDigits) + "}$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidQuickbooksPhone(String num) {

		String REGEX = "^[0-9\\s()-]{6,15}$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidFax(String num) {

		String REGEX = "^(\\+)?[0-9-]{10,15}$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidInteger(String num, int numOfDigits, int startsWith) {

		String REGEX = "^(?=" + startsWith + ")[0-9]{" + Integer.toString(numOfDigits) + "}$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidInteger(String num) {
		String REGEX = "^[0-9]{1,}$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidDecimal(String num, int numberOfDecimalPlaces) {
		String REGEX = "^\\d*(\\.\\d{1," + numberOfDecimalPlaces + "})?$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidDecimal(String num) {
		String REGEX = "^\\d*(\\.\\d{1,})?$";
		return Pattern.matches(REGEX, num);
	}

	public static boolean isValidAddressLine(String address, int min, int max) {
		if (StringUtil.isEmpty(address))
			return false;
		address = address.toLowerCase();
		String REGEX = "^[a-z\\s\\d/,#-.:()]{" + min + "," + max + "}$";
		return Pattern.matches(REGEX, address);
	}

	public static boolean isValidAddressLine(String address, int max) {

		return isValidAddressLine(address, 1, max);
	}

	public static boolean isValidAddressLine1(String address) {
		if (StringUtil.isEmpty(address))
			return false;
		return isValidAddressLine(address, 1, 140);
	}

	public static boolean isValidAddressLine2(String address) {
		if (StringUtil.isEmpty(address))
			return true;
		return isValidAddressLine(address, 1, 140);
	}

	public static boolean isValidMobileNumber(String num) {
		return isValidInteger(num, 10);
	}

	public static boolean isValidUserName(String name, int min, int max) {
		name = name.toLowerCase();
		String REGEX = "^\\d*[a-zA-Z](?!\\s)[a-z\\s\\d;'&,\\.]{" + min + "," + max + "}$";
		return Pattern.matches(REGEX, name);
	}

	public static boolean isValidUserNames(String name, int min, int max) {
		name = name.toLowerCase();
		String REGEX = "^(?!\\s)[a-z\\s]{" + min + "," + max + "}$";
		return Pattern.matches(REGEX, name);
	}

	public static boolean isValidItemName(String name, int min, int max) {
		name = name.toLowerCase();
		String REGEX = "^(?!\\s)[a-z\\s\\d,#-.:()&_]{" + min + "," + max + "}$";
		return Pattern.matches(REGEX, name);
	}

	public static boolean isValidSuffix(String name, int min, int max) {
		name = name.toLowerCase();
		String REGEX = "^[a-z\\s\\d\\W]{" + min + "," + max + "}$";
		return Pattern.matches(REGEX, name);
	}

	public static boolean isValidUserName(String name, int max) {

		return isValidUserName(name, 1, max);
	}

	public static boolean isValidItemName(String name, int max) {

		return isValidItemName(name, 1, max);
	}

	public static boolean isName(String name) {

		return isValidUserName(name, 1, 50);
	}

	public static boolean isValidUserNames(String name, int max) {

		return isValidUserNames(name, 2, max);
	}

	public static boolean isValidSuffix(String name, int max) {

		return isValidSuffix(name, 0, max);
	}

	public static boolean isValidURL(String url) {

		return isValidURL(url, false);
	}

	public static boolean isValidURL(String url, boolean isSecure) {
		String secure = isSecure ? "s" : "s?";
		String REGEX = "^(http" + secure
				+ "://)?(www.)?[-a-zA-Z0-9@:%._+~#=]{2,256}.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$";

		return Pattern.matches(REGEX, url);
	}

	public static boolean isValidFirstName(String c) {
		c = c.trim();
		String REGEX = "^[a-zA-Z]+(([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)*$";
		return Pattern.matches(REGEX, c);
	}

	public static boolean isValidAccountHolderName(String accountHolderName) {
		String REGEX = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
		if (StringUtil.isEmpty(accountHolderName))
			return true;
		return Pattern.matches(REGEX, accountHolderName);
	}

	public static boolean isValidText(int allowedLength, String regex, String text) {
		text = text.trim();
		return Pattern.matches(regex, text) && text.length() <= allowedLength;
	}

	public static boolean isValidPrefix(String namePrefix) {
		if (namePrefix.equalsIgnoreCase("Mr") || namePrefix.equalsIgnoreCase("Mrs")
				|| namePrefix.equalsIgnoreCase("Ms"))
			return true;
		else
			return false;
	}

	public static boolean isValidPassword(String password) {
		String REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!,%,&,@,#,$,^,*,?,_,~,-]).{8,20}$";
		if (StringUtil.isEmpty(password))
			return false;
		return Pattern.matches(REGEX, password);
	}

	public static boolean isValidOrgName(String name) {
		String regex = "^[A-Z]([a-zA-Z0-9]|[- @\\.#&!])*$";
		if (StringUtil.isEmpty(name))
			return false;
		return Pattern.matches(regex, name);
	}

	public static boolean isValidCity(String name) {
		String regex = "^(?:[a-zA-Z]+(?:[.'\\-,])?\\s?)+$";
		if (StringUtil.isEmpty(name))
			return false;
		return Pattern.matches(regex, name);
	}

	public static boolean isValidState(String state) {
//		log.logInfo("state: " + state);
		String regex = "\\w{2}";
		if (StringUtil.isEmpty(state))
			return false;
		return Pattern.matches(regex, state);
	}

	public static boolean isValidCountry(String country) {
//		log.logInfo("country: " + country);
		String regex = "\\w{3}";
		if (StringUtil.isEmpty(country))
			return false;
		return Pattern.matches(regex, country);
	}

	public static boolean isValidZip1(String zip) {

		if (StringUtil.isEmpty(zip))
			return false;
		return (isValidInteger(zip, 5));
	}

	public static boolean isValidZip2(String zip) {

		if (StringUtil.isEmpty(zip))
			return true;

		return (isValidInteger(zip, 4));
	}

	public static boolean isValidClientId(String clientId) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(clientId))
			return false;
		return Pattern.matches(regex, clientId);
	}

	public static boolean isValidPaymentId(String paymentId) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(paymentId))
			return false;
		return Pattern.matches(regex, paymentId);
	}

	public static boolean isValidToken(String token) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(token))
			return false;
		return Pattern.matches(regex, token);
	}

	public static boolean isvalidCustomerId(String customerId) {

		String regex = "[A-Za-z0-9-_@.]{0,100}";
		if (StringUtil.isEmpty(customerId))
			return true;
		return Pattern.matches(regex, customerId);
	}

	public static boolean isvalidFundingAccountId(String fundingAccountId) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(fundingAccountId))
			return true;
		return Pattern.matches(regex, fundingAccountId);
	}

	public static boolean isValidOrderReferneceNumber(String chargeTraceReferneceNumber) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(chargeTraceReferneceNumber))
			return false;
		return Pattern.matches(regex, chargeTraceReferneceNumber);
	}

	public static boolean isValidChargeAmount(String chargeAmount) {

		String regex = "^\\d{0,16}+(\\.\\d{1,2})?$";

		if (StringUtil.isEmpty(chargeAmount))
			return false;
		return Pattern.matches(regex, chargeAmount);
	}

	public static boolean isValidAccountIssuingInstitutionName(String accountIssuingInstitutionName) {

		String regex = "\\w{0,50}";
		if (StringUtil.isEmpty(accountIssuingInstitutionName))
			return false;
		return Pattern.matches(regex, accountIssuingInstitutionName);
	}

	public static boolean isValidAccountNumber(String accountNumber) {

		String regex = "\\w{0,34}";
		if (StringUtil.isEmpty(accountNumber))
			return false;
		return Pattern.matches(regex, accountNumber);
	}

	public static boolean isValidCurrencyCode3d(String currencyCode3d) {

		String regex = "\\w{3}";
		if (StringUtil.isEmpty(currencyCode3d))
			return false;
		return Pattern.matches(regex, currencyCode3d);
	}

	public static boolean isValidExpiryDate(String expiryDate) {

		String regex = "^((0?[1-9])|(1[0-2]))\\/((\\d{4})|(\\d{2}))$";
		if (StringUtil.isEmpty(expiryDate))
			return false;
		return Pattern.matches(regex, expiryDate);
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {

		String regex = "^(\\+)?[0-9\\s()-]{6,16}$";
		if (StringUtil.isEmpty(phoneNumber))
			return true;
		return Pattern.matches(regex, phoneNumber);
	}

	public static boolean isValidChargeAmountCurrency(String chargeAmountCurrency) {

		String regex = "\\w{3}";
		if (StringUtil.isEmpty(chargeAmountCurrency))
			return false;
		return Pattern.matches(regex, chargeAmountCurrency);
	}

	public static boolean isValidId(String id) {

		String regex = "[A-Za-z0-9-_]{0,50}";
		if (StringUtil.isEmpty(id))
			return false;
		return Pattern.matches(regex, id);
	}

	public static boolean isValidAmount(String amount) {

		String regex = "^\\$?\\d+(\\.(\\d{2}))?$";
		if (StringUtil.isEmpty(amount))
			return false;
		return Pattern.matches(regex, amount);
	}

	public static boolean isValidCardCVV(String cardCVV) {

		String regex = "^([0-9]{3,4})$";
		if (StringUtil.isEmpty(cardCVV))
			return false;
		return Pattern.matches(regex, cardCVV);
	}

	public static boolean isValidRoutingNumber(String routingNumber) {
		if (routingNumber == null) {
			return false;
		}
		int checksum = 0, len = 0, sum = 0, mod = 0;
		len = routingNumber.length();

		if (len != 9) {
			return false;
		} else {
			String newString = routingNumber.substring(routingNumber.length() - 1);
			checksum = Integer.parseInt(newString);

			sum = (7 * (Integer.parseInt("" + routingNumber.charAt(0)) + Integer.parseInt("" + routingNumber.charAt(3))
					+ Integer.parseInt("" + routingNumber.charAt(6))))
					+ (3 * (Integer.parseInt("" + routingNumber.charAt(1))
							+ Integer.parseInt("" + routingNumber.charAt(4))
							+ Integer.parseInt("" + routingNumber.charAt(7))))
					+ (9 * (Integer.parseInt("" + routingNumber.charAt(2))
							+ Integer.parseInt("" + routingNumber.charAt(5))));

			mod = sum % 10;

			if (mod == checksum)
				return true;
			else
				return false;

		}
	}

	/*public static boolean isCompleteAddress(SampleVO address) {
		boolean isCompleteAddress = true;
		if (address == null) {
			isCompleteAddress = false;
		} else {
			if (StringUtil.isEmpty(address.getAddressLine1())) {
				isCompleteAddress = false;
			}

			if (StringUtil.isEmpty(address.getAddressCity())) {
				isCompleteAddress = false;
			}

			if (StringUtil.isEmpty(address.getAddressState())) {
				isCompleteAddress = false;
			}

			if (StringUtil.isEmpty(address.getAddressCountry())) {
				isCompleteAddress = false;
			}

			if (StringUtil.isEmpty(address.getAddressZip1())) {
				isCompleteAddress = false;
			}
		}

		return isCompleteAddress;
	}*/
	
	public static boolean isvalidInteger(String initialRecords) {
		if (StringUtil.isEmpty(initialRecords))
			return false;
		String regex = "[1-9][0-9]*{1,50}";
		return Pattern.matches(regex, initialRecords);
	}

	public static boolean isvalidInitailRecords(String initialRecords) {
		if (StringUtil.isEmpty(initialRecords))
			return true;
		String regex = "[1-9][0-9]*{1,50}";
		return Pattern.matches(regex, initialRecords);
	}

	/*public static boolean isValidSourceType(String sourceType) {
		if (StringUtil.isEmpty(sourceType)) {
			return false;
		} else if (Constants.SOURCE_TYPE_WITH_OUT_TOKEN.equals(sourceType)
				|| Constants.SOURCE_TYPE_WITH_TOKEN.equals(sourceType)) {
			return true;
		} else {
			log.logInfo("Invalid source type");
			return false;
		}
	}*/

	public static boolean isValidDate(String date) {

		String regex = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
		if (StringUtil.isEmpty(date))
			return true;
		return Pattern.matches(regex, date);

	}

}
