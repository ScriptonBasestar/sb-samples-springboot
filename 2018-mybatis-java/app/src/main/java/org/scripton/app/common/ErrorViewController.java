package org.scripton.app.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author archmagece
 * @since 2015-02-04-18
 */
@Controller
@Slf4j
public class ErrorViewController {

	public static final String name = "error";
	public static final String root = "/error";

	public static final String p401 = root+"/401";
	public static final String p404 = root+"/404";
	public static final String p500 = root+"/500";

	@RequestMapping(value = p401)
	public String p401(){
		return p401;
	}

	@RequestMapping(value = p404)
	public String p404(){
		return p404;
	}

	@RequestMapping(value = p500)
	public String p500(){
		return p500;
	}

}
