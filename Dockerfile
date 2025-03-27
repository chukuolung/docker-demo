# 使用OpenJDK作為基礎鏡像
FROM openjdk:17-slim

# 設定工作目錄
WORKDIR /app

# 複製構建的JAR文件到容器中
COPY target/docker-demo-0.0.1-SNAPSHOT.jar app.jar

# 開放8090端口
EXPOSE 8090

# 設定容器啟動時執行的命令
CMD ["java", "-jar", "app.jar"]