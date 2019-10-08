FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /micro-activiti

WORKDIR /micro-activiti

EXPOSE 5005

ADD ./micro-visual/micro-activiti/target/micro-activiti.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar micro-activiti.jar
