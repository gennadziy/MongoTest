package com.gennadziy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennadziy.constants.MessageConstants.ErrorMessages;
import com.gennadziy.dao.JusticeLeagueRepository;
import com.gennadziy.domain.JusticeLeagueMemberDetail;
import com.gennadziy.exception.JusticeLeagueManagementException;
import com.gennadziy.service.JusticeLeagueMemberService;
import com.gennadziy.web.dto.JusticeLeagueMemberDTO;
import com.gennadziy.web.transformer.DTOToDomainTransformer;

/**
 * This service class implements the {@link JusticeLeagueMemberService} to
 * provide the functionality required for the justice league system.
 * 
 * @author dinuka
 *
 */
@Service
public class JusticeLeagueMemberServiceImpl implements JusticeLeagueMemberService {

	@Autowired
	private JusticeLeagueRepository justiceLeagueRepo;

	/**
	 * {@inheritDoc}
	 */
	public void addMember(JusticeLeagueMemberDTO justiceLeagueMember) {
		JusticeLeagueMemberDetail dbMember = justiceLeagueRepo.findBySuperHeroName(justiceLeagueMember.getName());

		if (dbMember != null) {
			throw new JusticeLeagueManagementException(ErrorMessages.MEMBER_ALREDY_EXISTS);
		}
		JusticeLeagueMemberDetail memberToPersist = DTOToDomainTransformer.transform(justiceLeagueMember);
		justiceLeagueRepo.insert(memberToPersist);
	}

}
