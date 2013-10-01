package mapreduce.topn;

import java.lang.String;

/**
 * User: ChenLong
 * Created Date: 9/26/13 12:01 上午
 * Description:
 */
public class WordPair implements Comparable {

    private String word;
    private int count;

    public WordPair(String word, int count)
    {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString()
    {
        return String.format("%s\t%s", word,count);
    }


    @Override
    public int compareTo(Object o) {

        if (count > ((WordPair) o).getCount())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
