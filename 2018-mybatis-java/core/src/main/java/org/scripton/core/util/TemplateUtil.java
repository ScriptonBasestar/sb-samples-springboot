package org.scripton.core.util;

import lombok.experimental.UtilityClass;
import org.stringtemplate.v4.ST;

import java.util.Map;

/**
 * @author archmagece
 * @since 2017-08-30
 */
@UtilityClass
public class TemplateUtil {

	public static String text(String tempalte, Map<String,String> param){
		ST format = new ST(tempalte);
		for(String key : param.keySet()){
			format.add(key, param.get(key));
		}
		return format.render();
	}

}
