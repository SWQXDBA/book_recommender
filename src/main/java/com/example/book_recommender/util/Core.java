package com.example.book_recommender.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Core {
    public static double computeSimilarity(Map<String, Double> va, Map<String, Double> vb) {
        Set<String> compared = new HashSet<>();

        double num = 0;
        double den = 1;  //分母不能为0
        double powa_sum = 0;
        double powb_sum = 0;
        for (Map.Entry<String, Double> entry : va.entrySet()) {
            final String interestPoint = entry.getKey();
            if (compared.contains(interestPoint)) {
                continue;
            }
            final Double interestValue = entry.getValue();
            final Double otherInterestValue = vb.getOrDefault(interestPoint, 0d);
            num = num + interestValue * otherInterestValue;  //分子为用户评分之和
            powa_sum = powa_sum + Math.pow(interestValue, 2);  //求出va所有值平方之和
            powb_sum = powb_sum + Math.pow(otherInterestValue, 2);  //求出vb所有值平方之和
            compared.add(interestPoint);
        }
        for (Map.Entry<String, Double> entry : vb.entrySet()) {
            final String interestPoint = entry.getKey();
            if (compared.contains(interestPoint)) {
                continue;
            }
            final Double interestValue = entry.getValue();
            final Double otherInterestValue = va.getOrDefault(interestPoint, 0d);
            num = num + interestValue * otherInterestValue;  //分子为用户评分之和
            powa_sum = powa_sum + Math.pow(otherInterestValue, 2);  //求出va所有值平方之和
            powb_sum = powb_sum + Math.pow(interestValue, 2);  //求出vb所有值平方之和
            compared.add(interestPoint);
        }
        double sqrta =  Math.sqrt(powa_sum);  //对平方和开平方
        double sqrtb =  Math.sqrt(powb_sum);  //对平方和开平方
        den = sqrta * sqrtb;  //对开平方的两个值相乘

        return num / den;//使用余弦公式计算相似度
    }
}
