pipeline {
    agent {
        kubernetes {
          label 'kubetemplate'
          idleMinutes 5
          yamlFile 'pod-template.yaml'
          defaultContainer 'maven'
        }
    }
    environment {
        DOCKER_REGISTRY = "https://hub.docker.com/"
        DOCKER_REPOSITORY = "devs90/workshop"
        DOCKER_CREDENTIAL_ID = "dockerhub"
        DOCKERHUB_CREDENTIALS = credentials("dockerhub")
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn -B -DskipTests clean package"
            }
        }
        stage('Docker') {
            steps {
                container('docker') {
                  sh "docker build -t devs90/workshop ."
                }
            }
        }
        stage('Publish') {
            steps {
                container('docker') {
                  sh '''
                    echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                    docker tag devs90/workshop devs90/workshop:$BUILD_NUMBER
                    docker push devs90/workshop:$BUILD_NUMBER
                    docker push devs90/workshop:latest
                '''

                }
            }
        }
        stage('Helm') {
            steps {
                container('helm') {
                   sh "helm version"
                }
            }
        }
    }
}