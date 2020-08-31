<!--
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-08-31 15:44:40
 * @LastEditors: nanoseeds
 * @LastEditTime: 2020-08-31 16:33:29
 * @License: CC-BY-NC-SA_V4_0 or any later version 
 -->
## <div>CS209A_Java2</div>
**SID**:  \*\*\*\*\*\*\*\*   
**Name**:  nanoseeds  
PS:不看答案直接做的版本.

### Question 1

Output of following Program:
``` java 
public class ZeroDiv{
    public static void main(String args[]){
        int x = 0;
        int y = 10;
        try{
            y /= x;
        }
        System.out.print("/ by 0");
        catch (Exception e){
            System.out.print("error);
        }
    }
}
``` 
虽然看上去似乎很正常,但是try catch之间夹杂了语句,应该直接编译不通过.
选择C.


### Question 2
Which of the following statements about exception handling in Java are true?
1. “throw” can be used to declare an exception in a method, and the exception will
be thrown in this method
2. “throws” is used to throw the exception objects
3. “try” is used to detect if there is an exception in its block and if so it intercepts
the exception and execute the code in the “catch” block
4. No matter if there is an exception or not, the code in the “finally” block will be
executed
5. You cannot throw an exception in a “try” block
倾向于选择1,2,3,4.

### Question 3
finally 为什么会在try里面,应该会编译不通过.
否则就是eE.

### Question 4
输出1

### Question 5
Which is the correct statement about exception handling in Java?
  + a) If you have defined a possible exception in a method with “throw” you definitely have this exception when you use the method
  + b) If there is no exception thrown in the “try” block then the code in the “finally” block won’t be executed
  + c) If your program throws an exception then there must be an error in your program – you need to debug and fix the error
  + d) An unchecked exception in Java derives from RuntimeException or its children
先排除B,再排除c,随后排除d,所以选择A?.

### Question 6

使用try()管理的资源需要实现一个特殊的接口.
False

### Question 7
c,怎么样都跳不过

### Question 8
先排除BC,再往上看其他题目,发现需要new,排除D,选A.

### Question 9
a,b作为运行时分配的量,会被分配到堆上,c则会因为final的修饰,被分配到栈上的静态变量区.
heap,heap,stack, 选择d.

### Question 10
选择a.

### Question 11
前两个都是借口,Exception的基类市MainException,选择D

### Question 12
/0,%0都会throw exception,选择A.

### Question 13
BCD都挑不出错来,选择A.

### Question 14
c,田间杀出还是linkedList快.

### Question 15
选择BC.

### Question 16
看上面的题面,选择C.

### Question 17
3,2. 选择c.

### Question 18
第一次remove之后剩余4,1,3,5 size == 4.
之后结束.
输出4 1 3 5. 选择C.

### Question 19
True.

### Question 20
选择D.

### Question 21
选择C

### Question 22
b

### Question 23
False
选择B.

### Question 24
False.

### Question 25
c

### Question 26
True


<div>
  <img src="Path_Of_Picture"><br />
  <div>Some details</div>
</div>

<style type="text/css">
div{
  text-align: center;
}
div>div {
  text-align: center;
  border-bottom: 1px solid #d9d9d9;
  display: inline-block;
  padding: 2px;
}
div>img{
  border-radius: 0.3125em;
  box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);
}
</style>