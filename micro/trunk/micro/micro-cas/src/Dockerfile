FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-cas

WORKDIR /micro-cas

EXPOSE 8083

ADD ./micro-api/target/micro-cas.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-cas.jar
