FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-codegen

WORKDIR /micro-codegen

EXPOSE 5003

ADD ./micro-visual/micro-codegen/target/micro-codegen.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-codegen.jar
