FROM mysql:5.7

MAINTAINER ygnet@yangguangnet.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./doc/db/micro-schema.sql /docker-entrypoint-initdb.d

COPY ./doc/db/micro-upms.sql /docker-entrypoint-initdb.d

COPY ./doc/db/micro-activiti.sql /docker-entrypoint-initdb.d