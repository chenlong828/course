package sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * User: ChenLong
 * Created Date: 9/18/13 6:50 下午
 * Description:
 */
public class Map extends Mapper<LongWritable, Text, IntWritable, Text>
{
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private IntWritable count = new IntWritable();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split("\t");
        word.set(tokens[0]);
        int word_count = Integer.parseInt(tokens[1]);
        count.set(word_count);
        context.write(count, word);

//        StringTokenizer tokenizer = new StringTokenizer(line);
//        while (tokenizer.hasMoreTokens())
//        {
//            word.set(tokenizer.nextToken());
//            output.collect(word, one);
//        }
    }
}
