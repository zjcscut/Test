package com;

import java.util.Arrays;

public class Test2 {
	public static void main(String[] args) {
		String[] ctxArray = { "jpg", "jpeg", "png", "JPG", "JPEG", "PNG" };

		String ctx = "png";

		if (Arrays.binarySearch(ctxArray, ctx) < 0) {
			System.out.println("不支持该类型");
		} else {
			System.out.println("支持该类型");
		}
	}
}
