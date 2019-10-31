package io.ashimjk.pitest.mutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeTest {

    @Test
    void whenEmptyString_thanAccept() {
        Palindrome palindromeTester = new Palindrome();
        assertTrue(palindromeTester.isPalindrome("noon"));
    }

//    @Test
//    void whenPalindrom_thanAccept() {
//        Palindrome palindromeTester = new Palindrome();
//        assertTrue(palindromeTester.isPalindrome("noon"));
//    }
//
//    @Test
//    void whenNotPalindrome_thanReject() {
//        Palindrome palindromeTester = new Palindrome();
//        assertFalse(palindromeTester.isPalindrome("box"));
//    }
//
//    @Test
//    void whenNearPalindrome_thanReject() {
//        Palindrome palindromeTester = new Palindrome();
//        assertFalse(palindromeTester.isPalindrome("neon"));
//    }

}