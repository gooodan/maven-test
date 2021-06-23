package trie;

import java.util.ArrayList;
import java.util.List;

public class TrieTree {

    private static TrieTreeNode root;
    private List<String> searchResult;
    private StringBuilder tempWord = new StringBuilder();
    private int start = 0;

    public TrieTree() {
        root = new TrieTreeNode(null);
        searchResult = new ArrayList<>();
    }

    public void insert(String keyword) {
        TrieTreeNode currentNode = root;
        TrieTreeNode tempNode;
        String ch;
        boolean contains;

        for (int i = 0; i < keyword.length(); i++) {
            ch = String.valueOf(keyword.charAt(i));
            // true - 当前节点的子节点里value跟当前keyword.charAt(i)相等
            contains = false;
            for (TrieTreeNode child : currentNode.childs) {
                if (child.value.equals(ch)) {
                    currentNode = child;
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                tempNode = new TrieTreeNode(ch);
                currentNode.childs.add(tempNode);
                currentNode = tempNode;
            }
        }
    }

    public List<String> search(String keyword) {
        TrieTreeNode currentNode = root;
        boolean contains = false;
        String temp;
        for (int i = 0; i < keyword.length(); i++) {
            temp = String.valueOf(keyword.charAt(i));
            contains = false;
            for (TrieTreeNode child : currentNode.childs) {
                if (child.value.equals(temp)) {
                    currentNode = child;
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                break;
            }
        }
        if (contains && !(currentNode.childs.isEmpty())) {
            searchResult.clear();
            tempWord.delete(0, tempWord.length());
            tempWord.append(keyword);
            start = keyword.length();
            traverseTree(currentNode);
        } else {
            return new ArrayList<>(1);
        }
        return searchResult;
    }

    private void traverseTree(TrieTreeNode currentNode) {
        if (!(currentNode.childs.isEmpty())) {
            for (TrieTreeNode child : currentNode.childs) {
                tempWord.append(child.value);
                start++;
                traverseTree(child);
                start--;
                tempWord.delete(start, tempWord.length());
            }
        } else {
            searchResult.add(tempWord.toString());
        }
    }

    class TrieTreeNode {
        private String value;
        private List<TrieTreeNode> childs;

        TrieTreeNode(String value) {
            this.value = value;
            childs = new ArrayList<>();
        }
    }
}
