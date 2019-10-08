FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-monitor

WORKDIR /micro-monitor

EXPOSE 5001

ADD ./micro-visual/micro-monitor/target/micro-monitor.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-monitor.jar
