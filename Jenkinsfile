pipeline {
    agent {
        kubernetes {
          label 'kubetemplate'
          idleMinutes 5
          yamlFile 'agent.yaml'
          defaultContainer 'maven'
        }
    }
    environment {
        DOCKER_REGISTRY = "https://hub.docker.com/"
        DOCKER_REPOSITORY = "devs90/workshop"
        DOCKER_CREDENTIAL_ID = "dockerhub"
        DOCKERHUB_CREDENTIAL = credentials("dockerhub")
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
        }
        stage('Docker') {
            steps {
                container('docker') {
                  sh "docker build -t devs90/workshop ."
                }
            }
        }
        stage('Deploy') {
            steps {
                container('docker') {
                  sh '''
                    echo $DOCKERHUB_CREDENTIAL_PSW | docker login -u $DOCKERHUB_CREDENTIAL_USER --password-stdin

                    docker push devs90/workshop:1.1.0
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