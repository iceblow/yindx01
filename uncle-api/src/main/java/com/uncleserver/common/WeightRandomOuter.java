package com.uncleserver.common;

import java.util.HashMap;
import java.util.Map;

public class WeightRandomOuter {

	private static double[] weightArrays = { 8.0, 2.0, 11.0, 79.0 }; // 数组下标是要返回的值，数组值为数组下标的权重
	java.util.Random r = new java.util.Random();

	public static void main(String[] args) {  
    	
        Map<Double, Integer> stat = new HashMap<Double, Integer>();  
        for (int i = 0; i < 10; i++) {  
            int weightValue = getWeightRandom(weightArrays);  
            if (weightValue < 0) {  
                continue;  
            }  
            System.out.println("按权重返回的随机数：" + weightValue);  
            if (stat.get(weightArrays[weightValue]) == null) {  
                stat.put(weightArrays[weightValue], 1);  
            } else {  
                stat.put(weightArrays[weightValue], stat.get(weightArrays[weightValue])+1);  
            }  
        }  
        System.out.println(stat);  
    }

	private static double weightArraySum(double[] weightArrays) {
		double weightSum = 0;
		for (double weightValue : weightArrays) {
			weightSum += weightValue;
		}
		return weightSum;
	}

	public static int getWeightRandom(double[] weightArrays) {
		double weightSum = weightArraySum(weightArrays);
		double stepWeightSum = 0;
		for (int i = 0; i < weightArrays.length; i++) {
			stepWeightSum += weightArrays[i];
			if (Math.random() <= stepWeightSum / weightSum) {
				return i;
			}
		}
		System.out.println("出错误了");
		return -1;
	}

}
