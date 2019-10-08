FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-tx-manager

WORKDIR /micro-tx-manager

EXPOSE 5004
EXPOSE 9998

ADD ./micro-visual/micro-tx-manager/target/micro-tx-manager.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-tx-manager.jar
