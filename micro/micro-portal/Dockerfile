FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-portal

WORKDIR /micro-portal

EXPOSE 5003

ADD ./micro-portal/target/micro-portal.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-portal.jar
