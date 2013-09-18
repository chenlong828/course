package memcached;

/**
 * User: ChenLong
 * Created Date: 9/18/13 8:04 下午
 * Description:
 */
public class memcached_launcher {

    public static void main(String[] args) {
        MemcachedUtil.put("hello", "world", 60);
        String hello = (String) MemcachedUtil.get("hello");
        System.out.println(hello);

    }
}
