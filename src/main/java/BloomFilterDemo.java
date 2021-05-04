import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

public class BloomFilterDemo {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 1000, 0.001);

        if (bloomFilter.put("haha")) {
            System.out.println("haha suc");
        } else {
            System.out.println("haha not suc");
        }

        if (bloomFilter.put("haha")) {
            System.out.println("haha suc");
        } else {
            System.out.println("haha not suc");
        }
    }
}
