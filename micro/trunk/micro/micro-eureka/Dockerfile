FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-eureka

WORKDIR /micro-eureka

EXPOSE 8761

ADD ./micro-eureka/target/micro-eureka.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-eureka.jar
