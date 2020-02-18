package com.recruitment.statistics.RecruitmentStatisticsService;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Candidate;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Panel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@SpringBootTest
class RecruitmentStatisticsServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddEmployeeMissingHeader() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8085/hiringoperation/updateorg";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Organization> requestUpdate = new HttpEntity<>(getData(), headers);



		/*try
		{
			restTemplate.exchange(baseUrl, HttpMethod.PUT, requestUpdate,Void.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}*/
	}

	public Organization getData(){
		Panel p1 = new Panel("p1 desc", "YES_FOR_NEXT_ROUND","SELECT", 1);
		Panel p2 = new Panel("p2 desc", "REJECT","REJECTED", 2);
		Panel p3 = new Panel("p3 desc", "YES_FOR_NEXT_ROUND","REJECTED", 3);

		Candidate c1 = new Candidate("C1", Arrays.asList(p1,p2,p3));
		Organization o1 = new Organization("O1","O1 Desc", Arrays.asList(c1));
		return o1;
	}

}
