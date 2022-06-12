FROM tomcat:latest
RUN cp -R  /usr/local/tomcat/*  /usr/local/tomcat/
COPY ./*.war /usr/local/tomcat/
