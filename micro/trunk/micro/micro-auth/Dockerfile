FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-auth

WORKDIR /micro-auth

EXPOSE 3000

ADD ./micro-auth/target/micro-auth.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-auth.jar
