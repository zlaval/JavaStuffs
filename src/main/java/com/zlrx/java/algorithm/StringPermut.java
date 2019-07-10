package com.zlrx.java.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class StringPermut {

    private Set<String> results;
    private String word;

    public static void main(String[] args) {
        var sp = new StringPermut("table");
        sp.generatePermutation();
        sp.printResult();
    }

    public StringPermut(String word) {
        this.results = new HashSet<>();
        this.word = word;
    }

    public void generatePermutation() {
        permutation("", word);
    }

    private void permutation(String perm, String remain) {
        if (StringUtils.isBlank(remain)) {
            results.add(perm);
        } else {
            for (int i = 0; i < remain.length(); i++) {
                String actualPerm = perm + remain.charAt(i);
                String actualRemain = remain.substring(0, i) + remain.substring(i + 1);
                permutation(actualPerm, actualRemain);
            }
        }
    }

    public void printResult() {
        System.out.println("Result count: " + results.size());
        System.out.println();
        results.forEach(System.out::println);
    }
}
