FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-upms

WORKDIR /micro-upms

EXPOSE 4000

ADD ./micro-upms/upms-biz/target/upms-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar upms-biz.jar
