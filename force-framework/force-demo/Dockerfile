FROM ccr.ccs.tencentyun.com/winkey_crm/openjdk8-openj9:jdk8u262-b10_openj9-0.21.0-centos

RUN mkdir -p /force/demo

WORKDIR /force/demo

EXPOSE 8080

ADD ./target/force-demo.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=${SPRING_PROFILES_ACTIVE:dev}"]