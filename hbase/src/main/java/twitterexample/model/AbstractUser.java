package twitterexample.model;

import org.apache.hadoop.hbase.util.Bytes;

public abstract class AbstractUser {

    public static final byte[] TABLE_NAME = Bytes.toBytes("users");
    public static final byte[] INFO_FAM   = Bytes.toBytes("info");

    public static final byte[] USER_COL   = Bytes.toBytes("user");
    public static final byte[] NAME_COL   = Bytes.toBytes("name");
    public static final byte[] EMAIL_COL  = Bytes.toBytes("email");
    public static final byte[] PASS_COL   = Bytes.toBytes("password");
    public static final byte[] TWEETS_COL = Bytes.toBytes("tweet_count");

    public static final byte[] HAMLET_COL  = Bytes.toBytes("hamlet_tag");

    public String user;
    public String name;
    public String email;
    public String password;
    public long tweetCount;

  @Override
  public String toString() {
    return String.format(
                         "<AbstractUser: %s, %s, %s, %s>",
                         user, name, email, tweetCount);
  }
}
