package hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServerJPAIntegrationTest {
	 @Autowired
	    private HelloRepository helloRepository;
	 
	    @Test
	    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
	    	HelloEntity entity=new HelloEntity();
	    	entity.setId(1);
	    	entity.setMessage("Salut Sergiu!");
	    	HelloEntity genericEntity = helloRepository
	          .save(entity);
	    	Optional<HelloEntity> foundEntity = helloRepository.findById(genericEntity.getId());
	        assertNotNull(foundEntity);
	        assertEquals(genericEntity.getMessage(), foundEntity.get().getMessage());
	    }
}
