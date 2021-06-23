package trie;

import java.util.List;

public class DemoTest {
    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("你好123");
        trieTree.insert("你好456");
        trieTree.insert("你好789");
        trieTree.insert("你好abc");
        trieTree.insert("你好iop");
        trieTree.insert("你好wbc");
        trieTree.insert("你好fgh");
        trieTree.insert("你好bcv");
        trieTree.insert("张三好abc");
        List<String> list = trieTree.search("你好");
        System.out.println(list.toString());

    }
}
