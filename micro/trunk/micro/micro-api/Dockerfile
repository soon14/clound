FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-api

WORKDIR /micro-api

EXPOSE 9198

ADD ./micro-api/target/micro-api.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-api.jar
