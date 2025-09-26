# Java第三周学习笔记：File类详解

## 一、File类概述

`File`类是Java IO体系中用于操作文件和目录的基础类，它**并不直接操作文件内容**，而是用于处理文件系统中的路径、文件和目录的元数据。

> **核心特点**：
> - File对象既可以表示文件，也可以表示文件夹
> - 路径可以是实际存在的，也可以是不存在的
> - 主要用于文件系统的管理（创建、删除、查询等）

## 二、File类的构造方法

File类提供了三种常用的构造方法，用于创建File对象：

| 构造方法 | 说明 |
|---------|------|
| `public File(String pathname)` | 将字符串表示的路径转换为File对象<br>示例：`new File("D:/test.txt")` |
| `public File(String parent, String child)` | 将父级路径和子级路径拼接后创建File对象<br>示例：`new File("D:/data", "report.csv")` |
| `public File(File parent, String child)` | 以File对象作为父路径，与子路径拼接后创建新的File对象<br>示例：`new File(new File("D:/data"), "log")` |

> **注意**：构造方法仅创建对象，不会实际创建文件或目录。

## 三、File类的常见成员方法

### 3.1 获取与遍历方法

| 方法 | 说明 |
|------|------|
| `public static File[] listRoots()` | 列出系统中所有可用的根目录（如Windows的C:、D:等） |
| `public String[] list()` | 获取当前路径下所有文件和目录的名称数组 |
| `public String[] list(FilenameFilter filter)` | 使用文件名过滤器筛选当前路径下的内容 |
| `public File[] listFiles()` | 获取当前路径下所有文件和目录的File对象数组 |

**示例代码片段**：
```java
// 列出所有根目录
File[] roots = File.listRoots();
for (File root : roots) {
    System.out.println(root);
}

// 遍历目录内容
File dir = new File("D:/workspace");
if (dir.isDirectory()) {
    String[] files = dir.list();
    for (String file : files) {
        System.out.println(file);
    }
}
```

### 3.2 判断与获取属性方法

| 方法 | 说明 |
|------|------|
| `public boolean isDirectory()` | 判断当前File对象是否为目录 |
| `public boolean isFile()` | 判断当前File对象是否为文件 |
| `public boolean exists()` | 判断当前路径所表示的文件或目录是否存在 |
| `public String getPath()` | 返回创建File对象时使用的路径字符串 |
| `public String getName()` | 返回文件或目录的名称（包含文件后缀） |
| `public String getAbsolutePath()` | 返回绝对路径字符串 |
| `public long length()` | 返回文件的长度（字节数，仅对文件有效） |
| `public long lastModified()` | 返回最后修改时间（毫秒时间戳） |

### 3.3 创建与删除方法

| 方法 | 说明 |
|------|------|
| `public boolean createNewFile()` | 创建新的空文件（成功返回true，文件已存在返回false） |
| `public boolean mkdir()` | 创建单级目录（父目录不存在则失败） |
| `public boolean mkdirs()` | 创建多级目录（父目录不存在会自动创建） |
| `public boolean delete()` | 删除文件或空目录<br>⚠️ 注意：删除操作直接生效，不会放入回收站 |

**示例代码片段**：
```java
// 创建文件
File file = new File("D:/test/newFile.txt");
try {
    if (file.createNewFile()) {
        System.out.println("文件创建成功");
    } else {
        System.out.println("文件已存在");
    }
} catch (IOException e) {
    e.printStackTrace();
}

// 创建多级目录
File dirs = new File("D:/a/b/c/d");
if (dirs.mkdirs()) {
    System.out.println("多级目录创建成功");
}

// 删除操作
if (file.delete()) {
    System.out.println("文件删除成功");
}
```

## 四、注意事项

1. **跨平台路径问题**：
   - Windows使用`\`作为路径分隔符
   - Linux/macOS使用`/`作为路径分隔符
   - 推荐使用`File.separator`常量以保证跨平台兼容性

2. **权限问题**：文件操作可能受系统权限限制，需处理可能的异常

3. **delete()方法限制**：
   - 只能删除空目录
   - 删除非空目录需要先删除其中的所有文件和子目录
   - 操作不可逆，使用时需谨慎

4. **路径表示**：
   - 绝对路径：从根目录开始的完整路径
   - 相对路径：相对于当前工作目录的路径

通过File类，我们可以方便地管理文件系统中的文件和目录，为后续的文件内容读写操作奠定基础。