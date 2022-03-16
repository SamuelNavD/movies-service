FROM openjdk:11-jre
ENV APP_FILE app-plain.jar
ENV APP_HOME /app
RUN mkdir $APP_HOME
EXPOSE 8080
COPY ./app/build/libs/$APP_FILE /app/$APP_FILE
CMD mkdir /certs
COPY AmazonRootCA1.cer /certs/
CMD keytool -importcert -alias "AmazonRootCA1" -trustcacerts -keystore cacerts -file /certs/AmazonRootCA1.cer

CMD rmdir -rf /certs
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
