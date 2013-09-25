package sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Reduce extends Reducer<IntWritable, Text, Text, IntWritable>
{
    public void reduce(IntWritable key, Iterator<Text> values, Context context) throws IOException, InterruptedException {
        while(values.hasNext())
        {
            context.write(values.next(), key);
        }
    }
}
