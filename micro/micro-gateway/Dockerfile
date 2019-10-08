FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-gateway

WORKDIR /micro-gateway

EXPOSE 9999

ADD ./micro-gateway/target/micro-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-gateway.jar
