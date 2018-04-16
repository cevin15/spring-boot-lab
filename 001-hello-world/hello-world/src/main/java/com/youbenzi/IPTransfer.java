package com.youbenzi;

public class IPTransfer {

	private final long PART1 = 0xff000000;
	private final long PART2 = 0xff0000;
	private final long PART3 = 0xff00;
	private final long PART4 = 0xff;

	/**
	 * ip长整数转字符串
	 * @param ip 一个长整数
	 * @return
	 */
	public String ip2Str(long ip) {
		/**
		 * ip 跟 0xff000000 按位与（与1按位与，1还是1，0还是0。与0按位与，都为0），此时1-24位都为0，右位移24位，拿出地址的第一部分数据 
		 */
		String ipStr = String.valueOf((ip & PART1) >> 24);
		/**
		 * ip 跟 0xff0000 按位与，此时1-16、25-32位都为0，右位移16位，拿出地址的第二部分数据，然后拼接上第一部分的数据。
		 */
		ipStr += "." + ((ip & PART2) >> 16);
		ipStr += "." + ((ip & PART3) >> 8);
		ipStr += "." + (ip & PART4);
		return ipStr;
	}

	/**
	 * ip字符串地址转长整数
	 * @param ip 类似10.12.12.21 之类的ip地址
	 * @return
	 */
	public long ip2Long(String ip) {
		/**
		 * 字符串地址用小数点分割
		 */
		String[] p4 = ip.split("\\.");
		long ipInt = 0;
		long part = Long.valueOf(p4[0]);
		/**
		 * 第一部分：把这部分数据左位移24位，即第一部分存在32位二进制的32-25位。（此时1-24的位数都为0）
		 * 第二部分：把这部分数据左位移16位，即第一部分存在32位二进制的24-17位。然后和上面的结果按位或（与0按位或，1还是1，0还是0）。（此时1-16的位数都为0）
		 * 第三部分、第四部分：同第二部分的逻辑
		 */
		ipInt = ipInt | (part << 24);
		part = Long.valueOf(p4[1]);
		ipInt = ipInt | (part << 16);
		part = Long.valueOf(p4[2]);
		ipInt = ipInt | (part << 8);
		part = Long.valueOf(p4[3]);
		ipInt = ipInt | (part);
		return ipInt;
	}

}