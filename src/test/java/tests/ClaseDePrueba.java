package tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReader;
import utils.LoggerUtil;

public class ClaseDePrueba extends BaseTest {
	
	private static final Logger log = LoggerUtil.getLogger(ClaseDePrueba.class);
	
	@Test
	public void prueba() {
		log.info("este es un log de info");
		log.error("este es un log de error");
		log.warn("este es un lo de advertencia");
		
		System.out.println(ConfigReader.obtenerPropiedad("timeout"));
	}
}
