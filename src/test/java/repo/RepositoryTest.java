package repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.mySpringBootDatabaseApp.MySpringBootDatabaseAppApplication;
import com.qa.mySpringBootDatabaseApp.model.MySpringBootDataModel;
import com.qa.mySpringBootDatabaseApp.repository.MySpringBootRepository;


@RunWith(SpringRunner.class)
// Used to provide a bridge between Spring Boot test features and JUnit
@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
@DataJpaTest

public class RepositoryTest {

	@Autowired
	private TestEntityManager entityManager; 
	
	@Autowired
	private MySpringBootRepository myRepo; 
	
	@Test
	public void retrieveByIdTest()
	{
		MySpringBootDataModel model1 = new MySpringBootDataModel("Cooper", "Glasgow", 46);
		entityManager.persist(model1);
		entityManager.flush();
		System.out.println(model1.getId());
		assertTrue(myRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByNameTest()
	{
		MySpringBootDataModel model1 = new MySpringBootDataModel("Cooper", "Glasgow", 46);
		entityManager.persist(model1);
		MySpringBootDataModel model2 = new MySpringBootDataModel("Kyle", "Bath", 46);
		entityManager.persist(model2);
		entityManager.flush();
		assertEquals("It's not Cooper", "Cooper", myRepo.findByName("Cooper").getName());
	}
	
	@Test
	public void retrieveByAddressTest()
	{
		MySpringBootDataModel model1 = new MySpringBootDataModel("Cooper", "Glasgow", 46);
		entityManager.persist(model1);
		MySpringBootDataModel model2 = new MySpringBootDataModel("Kyle", "Bath", 46);
		entityManager.persist(model2);
		entityManager.flush();
		assertEquals("There is no Bristol", "Bath", myRepo.findByAddress("Bath").getAddress());
	}
	
	@Test
	public void retrieveByAgeTest()
	{
		MySpringBootDataModel model1 = new MySpringBootDataModel("Cooper", "Glasgow", 46);
		entityManager.persist(model1);
		entityManager.flush();
		Integer A = 46;
		assertEquals(A , myRepo.findByAge(46).getAge());
	}
}
