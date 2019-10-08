FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-quartz

WORKDIR /micro-quartz

EXPOSE 5007

ADD ./micro-visual/micro-quartz/target/micro-quartz.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar micro-quartz.jar
