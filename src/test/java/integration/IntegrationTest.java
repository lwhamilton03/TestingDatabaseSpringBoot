package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.mySpringBootDatabaseApp.MySpringBootDatabaseAppApplication;
import com.qa.mySpringBootDatabaseApp.model.MySpringBootDataModel;
import com.qa.mySpringBootDatabaseApp.repository.MySpringBootRepository;

import repo.RepositoryTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
//@ContextConfiguration
//@TestPropertySource(locations = "classpath:my-test.properties")
//@TestPropertySource("*/my-test.properties")
@AutoConfigureMockMvc

public class IntegrationTest {

	@Autowired
	private MockMvc mvc; 
	
	@Autowired
	private MySpringBootRepository repository; 
	
	@Before
	public void clearDB()
	{
		repository.deleteAll();
	}
	
	//Testing the Get Requests
	@Test
	public void findingAndRetrievingPersonFromDatabase() throws Exception
		{
			repository.save(new MySpringBootDataModel("Mitch", "Narnia", 201));
			mvc.perform(get("/api/person").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("Mitch")));
		}

	// Testing the Post Request
	@Test 
	public void addAPersonToDatabaseTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders
				.post("/api/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"Vinu\", \"address\" : \"Antartic\", \"age\" : 12}"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Vinu")));
	}
	
	//Testing the Delete Request
	@Test 
	public void deletePersonFromDatabaseTest() throws Exception
	{
		MySpringBootDataModel model1 = new MySpringBootDataModel("Vinu", "Narnia", 201);
		repository.save(model1);
		Long idNum = model1.getId();
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/person/" + idNum).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful());
	}
	
	//Testing the Put Request 
	@Test 
	public void updatePersonToDatabase() throws Exception
	{
//		RepositoryTest findId = new RepositoryTest(); 
//		findId.retrieveByIdTest();
		
		MySpringBootDataModel model1 = new MySpringBootDataModel("Vinu", "Narnia", 201);
		repository.save(model1);
		Long idNum = model1.getId();
		
//		mvc.perform(get("/api/person").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$[0].name", is("Vinu")));
		
		mvc.perform(MockMvcRequestBuilders
				.put("/api/person/" + idNum).contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"Vinu\", \"address\" : \"Antartica\", \"age\" : 201}"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.address", is("Antartica")));
	}
}
