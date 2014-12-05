package cams7.siscom.backing;

import javax.inject.Named;
import javax.inject.Singleton;

@Named("sobreMB")
@Singleton
public class SobreBean {

	private String[] values;

	public SobreBean() {
		values = new String[] { "sobre.title", "sobre.built", "sobre.site",
				"sobre.build" };
	}

	public String[] getValues() {
		return values;
	}
}
