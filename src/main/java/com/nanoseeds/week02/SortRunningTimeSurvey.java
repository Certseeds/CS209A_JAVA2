package com.nanoseeds.week02;

import java.util.Random;
import java.io.File;
import java.lang.reflect.Method;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class SortRunningTimeSurvey {
	//                                  Task Name           Function Name      run times upper
	static String[][] taskList = { { "InsertionSortTest", "insertionSortTime", "100000" },
			                       { "BubbleSortTest",    "bubbleSortTime",    "100000" }, 
			                       { "SelectionSortTest", "selectionSortTime", "100000" },
			                       { "QuickSortTest",     "quickSortTime",     "100000" }, 
			                       { "MergeSortTest",     "mergeSortTime",     "100000" },
			                       { "HeapSortTest",      "heapSortTime",      "100000" }};

	static String[] dataList = { "AscendingSequence", "DescendingSequence", "RandomSequence", "OutOfOrderSequence" };
	static int max_length = get_numbers(8)+1;
	static int[] data = new int[max_length];
    public static int get_numbers(int num){
        assert(num >= 0 && num <= 10);
    	int will_return = 1;
    	for(int i =0;i<num;i++){
            will_return *= 10;
		}
    	return will_return;
	}
	public static void main(String[] args) {
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		try {
			File xlsFile = new File("RunningTimeSurvey.xls");
			// Create a workbook
			WritableWorkbook workbook;
			workbook = Workbook.createWorkbook(xlsFile);
			Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());

			for (int s = 0; s < 4; s++) {
				// Create a worksheet
				WritableSheet sheet = workbook.createSheet("RunningTime"+s, s);
				// the first row
				for (int j = 1, n = 1; j <= 6; j++) {
					n = 10 * n;
					sheet.addCell(new Label(j + 1, 0, "n = " + n));
				}
				for (int i = 0; i < taskList.length; i++) {
					// col row data
					sheet.addCell(new Label(0, i + 1, taskList[i][0]));
					sheet.addCell(new Label(1, i + 1, taskList[i][1]));
				}
				for (int i = 1; i <= 6; i++) {
					Method methodGenSeq = me.getMethod(dataList[s], int.class);
					methodGenSeq.invoke(null, (int) Math.pow(10, i));
					for (int j = 0; j < taskList.length; j++) {
						String[] taskInfo = taskList[j];
						Method method = me.getMethod(taskInfo[1], int.class);
						int upper = Integer.parseInt(taskInfo[2]);
						int n = (int) Math.pow(10, i);
						if (n > upper) {
							continue;
						}
						// run a sort program, get the run time
						Long time = (Long) method.invoke(null, n);
						// add the run time to the sheet
						// col row data
						sheet.addCell(new Label(i + 1, j + 1, time.toString()));
					}

				}
				System.out.println(s+" is finished");
			}
			workbook.write();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //用来给数组排序
	public static void AscendingSequence(int n) {
		for (int i = 0; i < n; i++) {
			data[i] = i;
		}
		System.out.println("AscendingSequence "+n+"elements");
	}
	//用来给数组排序
	public static void DescendingSequence(int n) {
		for (int i = 0; i < n; i++) {
			data[i] = n - i;
		}
		System.out.println("DescendingSequence "+n+"elements");
		
	}
	//用来给数组排序
	public static void RandomSequence(int n) {
		for (int i = 0; i < n; i++) {
			data[i] = i;
		}
		// shuffle
		Random rnd = new Random();
		for (int i = n; i > 1; i--) {
			int j = rnd.nextInt(i);
			int temp = data[j];
			data[j] = data[i - 1];
			data[i - 1] = temp;
		}
		System.out.println("RandomSequence "+n+" elements");
		
	}
	//用来给数组排序
	public static void OutOfOrderSequence(int n) {
		for (int i = 0; i < n; i++) {
			data[i] = i;
		}
		// shuffle
		Random rnd = new Random();
		for (int t = 0; t < n * 0.1; t++) {
			int i = rnd.nextInt(n);
			int j = rnd.nextInt(n);
			int temp = data[j];
			data[j] = data[i];
			data[i] = temp;
		}

		System.out.println("OutOfOrderSequence "+n+" elements");
		
	}

	public static long insertionSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		insertionSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void insertionSort(int n, int[] list) {
		// TODO :add your code here
		// reference: http://math.hws.edu/eck/cs124/javanotes8/c7/s4.html 7.4.3
		System.out.println("use insert sort");
	}

	public static long bubbleSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		bubbleSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void bubbleSort(int n, int[] list) {
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (list[j] > list[j + 1]) {
					// swap
					int tmp = list[j + 1];
					list[j + 1] = list[j];
					list[j] = tmp;
				}
			}
		}
		System.out.println("use bubble sort");
	}

	public static long selectionSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		selectionSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void selectionSort(int n, int[] list) {
		// TODO :add your code here
		// reference: http://math.hws.edu/eck/cs124/javanotes8/c7/s4.html 7.4.4
		System.out.println("use selection Sort");

	}

	public static long quickSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		quickSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void quickSort(int n, int[] list) {
		// TODO :add your code here
		// Adam's ppt
		System.out.println("use quicksort Sort");
	}

	public static long mergeSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		mergeSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void mergeSort(int n, int[] list) {
		// TODO :add your code here
		// https://introcs.cs.princeton.edu/java/42sort/ Mergesort
		System.out.println("use merge Sort");
	}

	public static long heapSortTime(int n) {
		int[] list = data;
		long timeStart = System.currentTimeMillis();
		heapSort(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void heapSort(int n, int[] list) {
		// TODO :add your code here
		// optional
		System.out.println("use heap Sort");
	}

}
