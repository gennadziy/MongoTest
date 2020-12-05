package com.gennadziy.web.transformer;

import com.gennadziy.domain.Test;
import com.gennadziy.web.dto.TestDTO;
import com.gennadziy.domain.JusticeLeagueMemberDetail;
import com.gennadziy.web.dto.JusticeLeagueMemberDTO;

/**
 * This class will have transformers from and to different types we need. A
 * common class is used in this instance due to the simplicity of the
 * application.
 * 
 * @author gennadziy
 *
 */
public class DTOToDomainTransformer {

	public static JusticeLeagueMemberDetail transform(final JusticeLeagueMemberDTO memberDTO) {
		return new JusticeLeagueMemberDetail(memberDTO.getName(), memberDTO.getSuperPower(), memberDTO.getLocation());
	}


	public static Test transformTest(final TestDTO testDTO) {
		return new Test(testDTO.getName());
	}

}
