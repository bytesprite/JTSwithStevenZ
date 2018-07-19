FROM java:8
ADD target/paymentservice.jar paymentservice.jar
EXPOSE 5672
MAINTAINER jahnelgroup.com
ENTRYPOINT ["java","-jar","/paymentservice.jar", "paymentservice.jar"]

#VOLUME /tmp
#RUN bash -c 'touch /app.jar'

#/home/hephaestus/Documents/ideaIU-2018.1.2/idea-IU-181.4668.68/Projects/auctionapplication/out/artifacts/auctionapplication_jar


#sudo docker build -t payment-service -f Dockerfile .
#sudo docker images
#sudo docker run -p 5672:5672 payment-service
#sudo docker stop payment-service

#docker container ls