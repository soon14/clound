FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-daemon

WORKDIR /micro-daemon

EXPOSE 5002

ADD ./micro-visual/micro-daemon/target/micro-daemon.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-daemon.jar
