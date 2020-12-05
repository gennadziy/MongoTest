package com.gennadziy.dao;

import com.gennadziy.test.util.BaseIntegrationTest;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gennadziy.domain.JusticeLeagueMemberDetail;

/**
 * <p>
 * This class will test the functionality exposed by
 * {@link JusticeLeagueRepository}. Since we are using
 * {@link BaseIntegrationTest}, it will boot up an embedded mongodb.
 * </p>
 * 
 * @author dinuka
 *
 */
public class JusticeLeagueRepositoryTest extends BaseIntegrationTest {

	@Autowired
	private JusticeLeagueRepository justiceLeagueRepo;

	/**
	 * This method will test if we can successfully retrieve the justice league
	 * members using there name.
	 */
	@Test
	public void testFindByName() {
		JusticeLeagueMemberDetail expected = new JusticeLeagueMemberDetail("Barry Allen", "Super speed",
				"Central City");
		mongoTemplate.save(expected);
		JusticeLeagueMemberDetail actual = justiceLeagueRepo.findBySuperHeroName("barry allen");

		Assert.assertThat(actual, SamePropertyValuesAs.samePropertyValuesAs(expected));
	}

}
