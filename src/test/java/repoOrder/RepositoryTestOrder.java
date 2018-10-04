package repoOrder;

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
//Used to provide a bridge between Spring Boot test features and JUnit
@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
@DataJpaTest
public class RepositoryTestOrder {

	@Autowired
	private TestEntityManager entityManager; 
	
	@Autowired
	private MySpringBootRepository myRepo;
	
	@Test
	public void retrieveById()
	{
		
	}
}
