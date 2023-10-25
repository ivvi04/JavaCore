FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1
COPY src/main/java/ru/lakeevda/lesson1 /src/ru/lakeevda/lesson1/
RUN mkdir ./out
RUN mkdir ./doc
RUN javac -sourcepath ./src -d out src/ru/lakeevda/lesson1/sample/Main.java
CMD java -classpath ./out ru.lakeevda.lesson1.sample.Main
CMD javadoc -sourcepath ./src -cp ./out -d ./doc -subpackages ru