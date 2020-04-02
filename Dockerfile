# Use the official maven/Java 8 image to create a build artifact.
FROM gradle:4.7.0-jdk8-alpine as builder

# Copy local code to the container image.
COPY --chown=gradle:gradle build.gradle.kts /home/gradle
COPY --chown=gradle:gradle settings.gradle.kts /home/gradle
COPY --chown=gradle:gradle src /home/gradle/src

# Build a release artifact.
RUN gradle build -x test --no-daemon

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM adoptopenjdk/openjdk8:jdk8u202-b08-alpine-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /home/gradle/build/libs/applewebhooks-*.jar /applewebhooks.jar

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/applewebhooks.jar"]
