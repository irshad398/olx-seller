package com.alacriti.olx_seller.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


public class ExceptionUtil {


	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		System.out.println("Throwable Message: " + aThrowable.getMessage()
				+ "\n Stacktrace: ");
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

}