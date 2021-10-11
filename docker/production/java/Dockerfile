# ビルドコンテナ
FROM openjdk:11.0.12-slim as builder
WORKDIR /app

# プロジェクトの配置とビルド
ADD . ./
RUN ./gradlew war

# プロダクション用のコンテナ
FROM tomcat:jdk11-adoptopenjdk-hotspot
ENV JAVA_ENV production
EXPOSE 8080

COPY --from=builder ./app/build/libs/se00g0.war /usr/local/tomcat/webapps
