import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Deom {
    public static void main(String[] args) {
        User user = new User();
        user.setId("123");
        change(user);
        System.out.println(user.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);

        Set<Integer> set = new HashSet<>();
        System.out.println("Integer.valueOf(map.get(\"a\").toString()):"+Integer.valueOf(map.get("a").toString()) );
        set.add(Integer.valueOf(map.get("a").toString()));
        set.add(Integer.valueOf(map.get("b").toString()));
        set.add(Integer.valueOf(map.get("c").toString()));
        for (int i : set) {
            System.out.println(i);
        }

        System.out.println("test : --- \n"+Integer.valueOf(""));
    }

    private static void change(User user) {
        user = new User();
        user.setId("567");
        System.out.println("change user:" + user.toString());
    }
}
