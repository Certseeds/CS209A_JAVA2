<!--
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-02-27 21:41:31
 * @LastEditors: nanoseeds
 * @LastEditTime: 2020-02-27 22:54:43
 -->
# Analysis of six kind of sorting Algorithm

## first of all, those are the datas.

#### increasing array
![Picture 01](./picture_01.png)
#### decreasing array
![Picture 02](./picture_02.png)
#### absolutely random
![Picture 03](./picture_03.png)
#### one tenth random
![Picture 04](./picture_04.png)
then we can get results
### 1. analysis based on sort Algorithm:
  + the insert sort is faster in increaing array(part of array is less to more also helperful), it even faster than the O(log(n))'s Algorithm. but in the decreaing array it have more badder performance, in the random case, it have the same performance with others.
  + Bubblesort and selection sort have the same bad perfermance in all arrays, which is actually tell us that they are O(N^2) Algorithm
  + The HeapSort is Special becuase it have a few badder than th other Algorithm in servel times.however, it is stable in different kind of arrays.
  + The Merge sort is both stable in time of different array and faster than heapsort, it is allmost the fastest.
  + The quick sort sometimes is as faster as merge sort, but sometimes it is slow as the heapsort. it is unstable for different kinds of array.
### 2. Analysis base on different array.
1. In  O(N^2) Algorithm
  + if part of the array is increaing. insert sort is faster.
  + If part of the array is decreaing, bubble sort or selection sort is faster,
  insert sort is slower than them.
  + other random array, all of them are the same.
2. In O(log(N)) Algorithm: ~~Actually they dont care about that~~
  + Merge sort is faster and stable time cost for all array.(choose that is always ok)
  + Heae sort is stable, too, but it is slower than merge sort.
  + quicksort sometimes is faster,sometimes is slow, it seems that unordered array will make it slow,(I randomly choose the pivot).