package com.gennadziy.web.rest.controller;

import com.gennadziy.constants.MessageConstants;
import com.gennadziy.domain.JusticeLeagueMemberDetail;
import com.gennadziy.test.util.BaseIntegrationTest;
import com.gennadziy.web.dto.JusticeLeagueMemberDTO;
import com.gennadziy.web.dto.ResponseDTO;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * This class will test out the REST controller layer implemented by
 * {@link JusticeLeagueManagementController}
 * 
 * @author dinuka
 *
 */
public class JusticeLeagueManagementControllerTest extends BaseIntegrationTest {

	/**
	 * This method will test if the justice league member is added successfully
	 * when valid details are passed in.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddJusticeLeagueMember() throws Exception {

		JusticeLeagueMemberDTO flash = new JusticeLeagueMemberDTO("Barry Allen", "super speed", "Central City");
		String jsonContent = mapper.writeValueAsString(flash);
		String response = mockMvc
				.perform(MockMvcRequestBuilders.post("/justiceleague").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();

		ResponseDTO expected = new ResponseDTO(ResponseDTO.Status.SUCCESS, MessageConstants.MEMBER_ADDED_SUCCESSFULLY);
		ResponseDTO receivedResponse = mapper.readValue(response, ResponseDTO.class);

		Assert.assertThat(receivedResponse, SamePropertyValuesAs.samePropertyValuesAs(expected));

	}

	/**
	 * This method will test if an appropriate failure response is given when
	 * the member being added already exists within the system.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddJusticeLeagueMemberWhenMemberAlreadyExists() throws Exception {
		JusticeLeagueMemberDetail flashDetail = new JusticeLeagueMemberDetail("Barry Allen", "super speed",
				"Central City");
		mongoTemplate.save(flashDetail);

		JusticeLeagueMemberDTO flash = new JusticeLeagueMemberDTO("Barry Allen", "super speed", "Central City");
		String jsonContent = mapper.writeValueAsString(flash);
		String response = mockMvc
				.perform(MockMvcRequestBuilders.post("/justiceleague").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();

		ResponseDTO expected = new ResponseDTO(ResponseDTO.Status.FAIL, MessageConstants.ErrorMessages.MEMBER_ALREDY_EXISTS);
		ResponseDTO receivedResponse = mapper.readValue(response, ResponseDTO.class);
		Assert.assertThat(receivedResponse, SamePropertyValuesAs.samePropertyValuesAs(expected));
	}

	/**
	 * This method will test if a valid client error is given if the data
	 * required are not passed within the JSON request payload which in this
	 * case is the super hero name.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddJusticeLeagueMemberWhenNameNotPassedIn() throws Exception {
		// The super hero name is passed in as null here to see whether the
		// validation error handling kicks in.
		JusticeLeagueMemberDTO flash = new JusticeLeagueMemberDTO(null, "super speed", "Central City");
		String jsonContent = mapper.writeValueAsString(flash);
		mockMvc.perform(MockMvcRequestBuilders.post("/justiceleague").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}

}
