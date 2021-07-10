package org.scripton.core.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author archmagece
 * @since 2018-09-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SBPageable {
	private int pageNo = 0;
	private int pageSize = 10;
}
