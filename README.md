# Getting Started
In production, this application is meant to be run in a Docker container on GCP Cloud Run.  

## To run locally
./gradlew bootRun

The application is configured to run in debug mode with debugger port 32323.

When run outside of GCP, the environment variable `GOOGLE_APPLICATION_CREDENTIALS` 
must be set to the absolute path to the location of a keyfile associated with the
service account that has access to Cloud SQL. Search for `GOOGLE_APPLICATION_CREDENTIALS`
in `build.gradle.kts` for how this is done for a Gradle-based Spring Boot application.

Cloud SQL configuration for this application may be found in `application.properties`

## To run on GCP
1. Submit for build  
`gcloud builds submit --tag gcr.io/quizlet-development/applewebhooks`

2. Deploy built image  
`gcloud run deploy --image gcr.io/quizlet-development/applewebhooks --platform managed`

### References
* [Quickstart: Build and Deploy](https://cloud.google.com/run/docs/quickstarts/build-and-deploy)
* [Connecting to Cloud SQL from Cloud Run](https://cloud.google.com/sql/docs/mysql/connect-run)
* [Getting Started with Authentication](https://cloud.google.com/docs/authentication/getting-started)