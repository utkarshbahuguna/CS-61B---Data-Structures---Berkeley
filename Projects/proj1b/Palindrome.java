public class Palindrome {

    /** Returns a deque of characters in the same order as they appear in word. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        if (word == null) {
            return wordDeque;
        }
        for (int i = 0; i < word.length(); i++ ) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Returns true if the given word is a palindrome, false otherwise. */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if (!(cc.equalChars(first, last) || first == last)) {
                return false;
            }
        }
        return true;
    }
}
