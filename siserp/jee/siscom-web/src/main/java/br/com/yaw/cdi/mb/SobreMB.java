package br.com.yaw.cdi.mb;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class SobreMB {

	private String[] values;
	
	public SobreMB() {
		values = new String[]{"sobre.title", "sobre.built", 
				"sobre.site", "sobre.build"};
	}
	
	public String[] getValues() {
		return values;
	}
}
