/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author b
 */
public class Utility {
	public static  StringBuilder getStringBuilder(HttpServletRequest req) throws IOException {
		final BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder("");
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb;
	}	
}
