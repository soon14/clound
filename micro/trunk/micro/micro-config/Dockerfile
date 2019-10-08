FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-config

WORKDIR /micro-config

EXPOSE 8888

ADD ./micro-config/target/micro-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-config.jar
