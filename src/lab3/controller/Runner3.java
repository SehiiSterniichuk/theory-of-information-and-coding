package lab3.controller;

import lab2.controller.Runner;
import lab3.algorithm.HuffmanCoder;

public class Runner3 {
    public static void main(String[] args) {
        String helloMessage = "Lab #3 has started work\nHuffman coding";
        var runner = new Runner();
        runner.work(helloMessage, new HuffmanCoder());
    }
}
