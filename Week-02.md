# 第二周Java学习笔记：集合、Stream流与异常处理
## 一、集合进阶
集合是Java中存储数据的核心容器，本周重点学习集合的高级特性与工具类使用，进一步提升数据处理效率。

### 1. 可变参数
#### 核心作用
在方法形参中灵活接收**任意数量**的同类型数据，避免了手动定义数组形参的繁琐。

#### 语法格式
```java
返回值类型 方法名(参数类型... 可变参数名) {
    // 方法体
}
```
- **底层实现**：可变参数本质是一个数组，在方法内部可直接当作数组操作（如遍历、获取长度等）。

#### 关键细节
- 形参列表中**只能有一个**可变参数，避免参数解析歧义。
- 可变参数必须放在形参列表的**最后一位**，普通参数需在其之前声明。

#### 示例代码
```java
// 计算多个整数的和
public static int sum(int... nums) {
    int total = 0;
    for (int num : nums) {
        total += num;
    }
    return total;
}

// 调用方式（支持1个、多个甚至0个参数）
sum(1);       // 结果：1
sum(1, 2, 3); // 结果：6
sum();        // 结果：0
```


### 2. 集合工具类 Collections
`java.util.Collections` 是集合操作的"瑞士军刀"，提供了大量静态方法用于简化集合处理，**本身不是集合**，而是操作集合的工具类。

#### 常用核心API
| 方法名                | 功能描述                                  | 示例                          |
|-----------------------|-------------------------------------------|-------------------------------|
| `addAll(Collection, T...)` | 批量向集合中添加元素                      | `Collections.addAll(list, 1,2,3)` |
| `shuffle(List)`       | 随机打乱列表中元素的顺序                  | `Collections.shuffle(arrayList)` |
| `sort(List)`          | 对列表元素进行自然排序（需实现Comparable）| `Collections.sort(stringList)` |
| `binarySearch(List, T)` | 二分查找指定元素在有序列表中的索引        | `Collections.binarySearch(sortedList, 5)` |
| `copy(List dest, List src)` | 将源集合元素复制到目标集合（需保证目标集合长度≥源集合） | `Collections.copy(destList, srcList)` |


## 二、Stream流
Stream流是Java 8引入的核心特性，以"流水线"的方式处理集合数据，兼具简洁性与高效性，核心思想是"**筛选-转换-聚合**"。

### 1. 核心概念
Stream流并非存储数据的容器，而是对数据的"处理通道"，操作分为**中间方法**和**终止方法**：
- 中间方法：执行后返回新的Stream流，支持链式调用（如filter、limit）。
- 终止方法：执行后关闭流，返回非流结果（如forEach、count、collect）。


### 2. 常用中间方法
| 方法名                | 功能描述                                  | 示例（以List<Integer> list为例）       |
|-----------------------|-------------------------------------------|----------------------------------------|
| `filter(Predicate)`   | 按条件筛选元素（保留返回true的元素）       | `list.stream().filter(num -> num > 5)` |
| `limit(long n)`       | 获取流中的前n个元素                       | `list.stream().limit(3)`               |
| `skip(long n)`        | 跳过流中的前n个元素                       | `list.stream().skip(2)`                |
| `distinct()`          | 元素去重（依赖equals()方法）              | `list.stream().distinct()`             |
| `concat(Stream a, Stream b)` | 合并两个流为一个新流（静态方法）        | `Stream.concat(stream1, stream2)`      |
| `map(Function)`       | 转换流中元素的类型（一对一映射）          | `list.stream().map(num -> "数字：" + num)` |


### 3. 简单使用示例
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 5, 3, 5, 8, 1, 9);
        
        // 需求：筛选大于3的元素，去重，取前3个，转换为字符串集合
        List<String> result = numbers.stream()
                                    .filter(num -> num > 3)  // 筛选：5,5,8,9
                                    .distinct()              // 去重：5,8,9
                                    .limit(3)                // 取前3个：5,8,9
                                    .map(num -> "数值：" + num) // 转换："数值：5",...
                                    .collect(Collectors.toList()); // 终止：收集为List
        
        System.out.println(result); // 输出：[数值：5, 数值：8, 数值：9]
    }
}
```


## 三、方法引用
方法引用是函数式编程的简化语法，核心作用是"**复用已有的方法**"，将现有方法作为函数式接口的抽象方法实现，避免重复编写Lambda表达式。

### 1. 核心格式
通过 `::` 符号连接方法的持有者与方法名，语法为：`方法持有者::方法名`（无需加括号和参数）。


### 2. 常见引用类型
#### （1）引用成员方法
- **其他类的成员方法**：`其他类对象::方法名`
  ```java
  // 函数式接口：接收String，返回Integer
  @FunctionalInterface
  interface StringToInt {
      Integer convert(String s);
  }

  public class OtherClass {
      // 已有的成员方法
      public Integer stringToLength(String s) {
          return s.length();
      }
  }

  // 方法引用使用
  OtherClass obj = new OtherClass();
  StringToInt sti = obj::stringToLength; // 引用OtherClass的stringToLength方法
  System.out.println(sti.convert("Java")); // 输出：4
  ```

- **本类的成员方法**：`this::方法名`（需在非静态上下文使用）

- **父类的成员方法**：`super::方法名`（需在子类中使用，调用父类重写的方法）


#### （2）其他常见引用类型
- 引用静态方法：`类名::静态方法名`（如 `Integer::parseInt`）
- 引用构造方法：`类名::new`（如 `ArrayList::new`）
- 引用数组构造方法：`数组类型[]::new`（如 `String[]::new`）


## 四、异常处理
异常是程序运行中出现的意外情况（如空指针、数组越界），合理的异常处理能提升程序的健壮性和可维护性。

### 1. 基本知识
#### （1）异常的继承体系
最顶层父类是 `java.lang.Throwable`，其下分为两大分支：
- **Error**：严重错误（如StackOverflowError），程序无法恢复，通常无需捕获。
- **Exception**：可处理的异常，分为两类：
  1. **编译时异常**（受检异常）：编译阶段必须处理（如IOException），否则无法通过编译。
  2. **运行时异常**（非受检异常）：运行时才会出现（如NullPointerException、ArrayIndexOutOfBoundsException），可选择性处理。


#### （2）自定义异常
当JDK内置异常无法满足业务需求时，可自定义异常类：
1. 继承 `Exception`（编译时异常）或 `RuntimeException`（运行时异常）。
2. 提供无参和带消息的构造方法。

示例：
```java
// 自定义编译时异常
public class UserNotFoundException extends Exception {
    // 无参构造
    public UserNotFoundException() {}
    
    // 带异常消息的构造
    public UserNotFoundException(String message) {
        super(message);
    }
}
```


### 2. 异常的核心操作
#### （1）抛出异常：`throw`
手动抛出指定异常对象，将异常交给上层调用者处理。
- 语法：`throw new 异常类名(异常消息);`
- 示例：
  ```java
  public void findUser(String userId) throws UserNotFoundException {
      if (userId == null || userId.isEmpty()) {
          // 手动抛出自定义异常
          throw new UserNotFoundException("用户ID不能为空");
      }
      // 业务逻辑...
  }
  ```


#### （2）捕获异常：`try-catch-finally`
捕获并处理异常，避免程序终止。
- 语法结构：
  ```java
  try {
      // 可能出现异常的代码块
  } catch (异常类型1 异常变量名) {
      // 处理异常类型1的逻辑
  } catch (异常类型2 异常变量名) {
      // 处理异常类型2的逻辑（需注意异常类型从小到大捕获）
  } finally {
      // 无论是否发生异常，都会执行的代码（如释放资源）
  }
  ```

- 示例：
  ```java
  public class ExceptionDemo {
      public static void main(String[] args) {
          try {
              findUser(""); // 调用可能抛出异常的方法
          } catch (UserNotFoundException e) {
              // 处理异常：打印异常消息
              System.out.println("出错了：" + e.getMessage());
          } finally {
              System.out.println("操作结束，释放资源");
          }
      }
  }
  ```


#### （3）声明异常：`throws`
在方法签名上声明该方法可能抛出的异常，告知调用者需处理这些异常。
- 语法：`返回值类型 方法名(参数列表) throws 异常类型1, 异常类型2 {...}`