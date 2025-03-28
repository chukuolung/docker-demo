裡分享一個簡單的Docker應用案例，旨在展示如何使用Docker來查詢商品項目。這個案例將利用Java語言的Spring Boot框架來提供API，讓使用者能夠查詢商品的詳細資訊。
### 步驟一：建立Spring Boot應用

首先，我們需要創建一個Spring Boot應用。可以使用Spring Initializr（[https://start.spring.io/）來生成一個新的Spring](https://start.spring.io/%EF%BC%89%E4%BE%86%E7%94%9F%E6%88%90%E4%B8%80%E5%80%8B%E6%96%B0%E7%9A%84Spring) Boot項目。
#### 方法一：使用Spring Initializr生成新項目

1. 訪問 [Spring Initializr](https://start.spring.io/)。
2. 在項目設置中，選擇以下選項：
    - **Project**: Maven Project
    - **Language**: Java
    - **Spring Boot**: 選擇最新的穩定版本（例如3.4.3）。
    - **Project Metadata**: 填寫Group、Artifact、Name等信息。
3. 在**Dependencies**部分，添加以下依賴：
    - **Spring Web**
    - **Thymeleaf**
4. 點擊**Generate**按鈕，下載生成的項目壓縮包，然後解壓縮到你的工作目錄。
#### 方法二：從現有專案直接複製

如果你已經有一個Spring Boot專案，並希望直接使用該專案中的配置，可以按照以下步驟操作：

1. **複製現有專案**：將現有的Spring Boot專案複製到新的工作目錄中。
2. **更新`pom.xml`**：確保`pom.xml`中包含以下依賴：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

3. **確保模板引擎配置**：在`src/main/resources/templates`目錄下創建HTML模板文件，例如`productTable.html`，並使用Thymeleaf語法來顯示資訊。
### 步驟二：創建Dockerfile

在項目的根目錄下創建一個名為`Dockerfile`的文件，內容如下：

```dockerfile
# 使用OpenJDK作為基礎鏡像  
FROM openjdk:17-slim  
  
# 設定工作目錄  
WORKDIR /app  
  
# 複製構建的JAR文件到容器中  
COPY target/docker-demo-0.0.1-SNAPSHOT.jar app.jar  
  
# 開放8080端口  
EXPOSE 8080  
  
# 設定容器啟動時執行的命令  
CMD ["java", "-jar", "app.jar"]
```

### 步驟三：構建Spring Boot應用

在項目根目錄下，使用Maven構建Spring Boot應用：

```bash
$ mvn clean package
```

這將生成一個名為`docker-demo-0.0.1-SNAPSHOT.jar`的JAR文件，位於`target`目錄中。

### 步驟四：構建Docker鏡像

在包含`Dockerfile`的目錄中，運行以下命令來構建Docker鏡像：

```bash
$ docker build -t docker-demo .
```

### 步驟五：運行Docker容器

構建完成後，可以使用以下命令運行Docker容器：

```bash
$ docker run -d -p 8080:8080 --name docker-demo-app docker-demo
```

這樣，Spring Boot應用就會在容器中運行，並且映射到主機的8090端口。

### 步驟六：查詢商品項目

現在，使用者可以通過瀏覽器或API客戶端（如Postman）訪問以下URL來查詢商品項目：

```
http://localhost:8080
```

這將返回一個表格形式的商品列表，例如：

網路費率牌價

| 最高速率 (下載/上傳) | 上網費率牌價 (元/月) |
| ------------ | ------------ |
| 2G/1G 進階型    | $1,789       |
| 2G/1G 商用型    | $1,446       |
| 2G/1G 家用型    | $1,111       |
| 1G/1G 商用型    | $1,025       |

這個案例展示了如何將Spring Boot應用容器化，並提供API查詢商品信息的功能。
