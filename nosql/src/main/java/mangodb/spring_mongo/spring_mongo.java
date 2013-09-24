package mangodb.spring_mongo;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * User: ChenLong
 * Created Date: 9/23/13 7:30 下午
 * Description:
 */
@EnableMongoRepositories
public class spring_mongo {
    @Autowired
    CustomerRepository customerRepository;

    @Bean
    Mongo mongo() throws UnknownHostException {
        return new Mongo("101.227.253.25");
    }

    @Bean
    MongoTemplate mongoTemplate(Mongo mongo) {
        UserCredentials auth = new UserCredentials("ambo", "ambo");
        return new MongoTemplate(mongo, "mydb", auth );
    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(spring_mongo.class);
        CustomerRepository repository = context.getBean(CustomerRepository.class);

        repository.deleteAll();

        Customer alice = new Customer("Alice", "Smith", new Customer());
        Customer bob = new Customer("Bob", "Smith", alice);
        // save a couple of customers
        repository.save(alice);
        repository.save(bob);

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);

        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
            if (customer.getRecommender() != null)
            {
                System.out.println(customer.getRecommender());
            }
        }

        context.close();
    }
}
