package twitterexample.model;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Description：
 * Author: ChenLong
 * Date: 13-9-25
 * Time: 下午6:45
 */
public class User extends AbstractUser {

    public User(Result r) {
        this(r.getValue(INFO_FAM, USER_COL),
                r.getValue(INFO_FAM, NAME_COL),
                r.getValue(INFO_FAM, EMAIL_COL),
                r.getValue(INFO_FAM, PASS_COL),
                r.getValue(INFO_FAM, TWEETS_COL) == null
                        ? Bytes.toBytes(0L)
                        : r.getValue(INFO_FAM, TWEETS_COL));
    }

    public User(byte[] user,
                 byte[] name,
                 byte[] email,
                 byte[] password,
                 byte[] tweetCount) {
        this(Bytes.toString(user),
                Bytes.toString(name),
                Bytes.toString(email),
                Bytes.toString(password));
        this.tweetCount = Bytes.toLong(tweetCount);
    }

    public User(String user,
                 String name,
                 String email,
                 String password) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
