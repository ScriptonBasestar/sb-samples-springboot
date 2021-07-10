package org.scripton.core.util

import com.google.gson.Gson
import org.junit.Test

/**
 * @author archmagece
 * @since 2017-08-30
 */
class TemplateUtilTest {

	@Test
	void '템플릿 TEXT 테스트'(){
		Map<String,String> param = new HashMap<>()
		param.put("username", "사용자")
		param.put("email", "!!!.gmail.com")
		System.out.println(TemplateUtil.text("<username>님 \\<\\<환영환영>> 환영합니다", param))
	}

	@Test
	void '템플릿 TEXT 테스트 - 파라미터가 json'(){
		String json = """ {"username":"사용자", "email":"hello@gmail.com"}  """
		Gson gson = new Gson()
		Map<String,String> param = gson.fromJson(json,HashMap.class)
		System.out.println(TemplateUtil.text("<username>님 \\<\\<환영환영>> 환영합니다", param))
	}

}
