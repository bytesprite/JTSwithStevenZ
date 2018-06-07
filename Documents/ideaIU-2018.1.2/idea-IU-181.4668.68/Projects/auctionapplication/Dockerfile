FROM java:8
ADD target/auctionapplication.jar auctionapplication.jar
EXPOSE 8080
MAINTAINER jahnelgroup.com
ENTRYPOINT ["java","-jar","/auctionapplication.jar", "auctionapplication.jar"]

#VOLUME /tmp
#RUN bash -c 'touch /app.jar'

#/home/hephaestus/Documents/ideaIU-2018.1.2/idea-IU-181.4668.68/Projects/auctionapplication/out/artifacts/auctionapplication_jar


#sudo docker build -t auction-service -f Dockerfile .
#sudo docker images
#sudo docker run -p 8080:8080 auction-service        NOTE: Be careful of copy & paste for ports if adding docker to other projects
#sudo docker stop auction-service