package br.com.cams7.siscom;

import java.util.Locale;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.cams7.apps.util.ApplicationUtil;

/**
 * Ponto de entrada da aplicação.
 * 
 * @author YaW Tecnologia
 */
public class Main {

	public static void main(String[] args) {
		Locale.setDefault(ApplicationUtil.DEFAULT_LOCALE);
		new ClassPathXmlApplicationContext("META-INF/spring-config.xml");

	}

}
