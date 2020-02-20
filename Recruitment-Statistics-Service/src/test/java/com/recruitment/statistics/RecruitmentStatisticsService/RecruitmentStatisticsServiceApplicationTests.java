package com.recruitment.statistics.RecruitmentStatisticsService;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Candidate;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Panel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.*;

@SpringBootTest
class RecruitmentStatisticsServiceApplicationTests {

	@Test
	@Ignore
	void contextLoads() {
	}

	@Test
	@Ignore
	void getAllOrganizationTest() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> organizations = restTemplate.getForObject("http://localhost:8085/hiringoperation/results", List.class);
		organizations.forEach(m-> m.forEach((k,v)->{System.out.println(k +" : "+v);}));
	}

	@Test
	void concurrentUpdateTest(){
		RestTemplate restTemplate = new RestTemplate();
		insertTestData(restTemplate);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Callable<Organization>> callables = Lists.newArrayList();
		callables.add(firstRestCall(restTemplate));
		callables.add(secondRestCallWithOutDelay(restTemplate));
		try {
			List<Future<Organization>> futures = executorService.invokeAll(callables);
			futures.forEach(f -> {
				if(f.isDone()) {
					try {
						System.out.println("Apply assert for exception "+ f.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			});
			executorService.shutdown();
			executorService.awaitTermination(5,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(!executorService.isTerminated()){
				executorService.shutdownNow();
			}
		}
	}

	Callable<Organization> firstRestCall(RestTemplate restTemplate) {
		Callable<Organization> task = () -> {
			Organization organization = restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class);
			System.out.println("Before Update user1 : "+ organization);
			organization.setDescription("333");
			Thread.sleep(1000);
			try {
				restTemplate.put("http://localhost:8085/hiringoperation/updateorg", organization);
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println("User 1 : "+ e.getMessage());
				System.out.println("User 1 update failed !! Since version changed "+ restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class));
			}
			Organization updatedOrgnization = restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class);
			return updatedOrgnization;
		};
		return task;
	}

	Callable<Organization> secondRestCallWithOutDelay(RestTemplate restTemplate) {
		Callable<Organization> task = () -> {
			Organization organization = restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class);
			System.out.println("Before Update user2 : "+ organization);
			organization.setDescription("444");
			try {
				restTemplate.put("http://localhost:8085/hiringoperation/updateorg", organization);
			}catch (Exception e) {
				System.err.println("User 2 : "+ e.getMessage());
				System.out.println("User 2 update failed !! Since version changed "+ restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class));
			}
			Organization updatedOrgnization = restTemplate.getForObject("http://localhost:8085/hiringoperation/results/2",Organization.class);
			return updatedOrgnization;
		};
		return task;
	}

	public void insertTestData(RestTemplate restTemplate) {
		Panel p1 = new Panel("p4 desc", "YES_FOR_NEXT_ROUND","SELECT", 1);
		Panel p2 = new Panel("p5 desc", "REJECT","REJECTED", 2);
		Panel p3 = new Panel("p6 desc", "YES_FOR_NEXT_ROUND","REJECTED", 3);

		Candidate c1 = new Candidate("C2", Arrays.asList(p1,p2,p3));
		Organization o1 = new Organization("O2","O2 Desc", Arrays.asList(c1));

		Organization testOrganization = restTemplate.postForObject("http://localhost:8085/hiringoperation/createorg", o1, Organization.class);
		System.out.println("Test data inserted "+testOrganization);
	}

}
