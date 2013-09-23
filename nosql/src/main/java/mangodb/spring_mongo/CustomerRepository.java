package mangodb.spring_mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * User: ChenLong
 * Created Date: 9/23/13 7:36 下午
 * Description:
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
}
