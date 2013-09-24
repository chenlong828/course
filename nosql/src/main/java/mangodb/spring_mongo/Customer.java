package mangodb.spring_mongo;

import org.springframework.data.annotation.Id;

/**
 * User: ChenLong
 * Created Date: 9/23/13 7:32 下午
 * Description:
 */
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Customer recommender;

    public Customer() {}

    public Customer(String firstName, String lastName, Customer recommender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.recommender = recommender;
    }

    public Customer getRecommender()
    {
        return recommender;
    }

    @Override
    public String toString() {
        if (recommender != null){
            return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', recommender='%s']",
                id, firstName, lastName, recommender.firstName);
        }
        else {
            return String.format(
                    "Customer[id=%s, firstName='%s', lastName='%s']",
                    id, firstName, lastName);
        }
    }

}
