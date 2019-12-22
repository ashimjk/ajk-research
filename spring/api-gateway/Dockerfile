FROM openjdk:8-jre-alpine
WORKDIR /myapp

# Needed to fix Fontconfig warning: ignoring C.UTF-8: not a valid language tag
ENV LANG en_GB.UTF-8

# JRE fails to load fonts if there are no standard fonts in the image; DejaVu is a good choice,
# see https://github.com/docker-library/openjdk/issues/73#issuecomment-207816707
RUN apk add --update ttf-dejavu && rm -rf /var/cache/apk/*

EXPOSE 8090

ADD target/api-gateway-1.0.0.jar   /myapp/app.jar
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar
