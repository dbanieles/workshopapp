pipeline {
    agent {
        kubernetes {
          label 'kubeagent'
          idleMinutes 5
          yamlFile 'agent.yaml' 
          defaultContainer 'maven'
        }
    }
    environment {
        GIT_URL = "https://danielebaggio90@bitbucket.org/danielebaggio90/message-sender.git"
        GIT_CREDENTAL_ID = "bitbucket"
        DOCKER_REGISTRY = "https://hub.docker.com/"
        DOCKER_REPOSITORY = "devs90/devrepo"
        DOCKER_CREDENTIAL_ID = "dockerhub"
        DOCKER_IMAGE = ""
    }
    stages {
        stage("Git") {
            steps {
                echo "Clone repository"
                echo params.branch
                dir("project") {
                    git url: GIT_URL,
                    credentialsId: GIT_CREDENTAL_ID,
                    branch: "develop" //params.branch
                }
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean install"   
            }
        }
        stage('Unit Test') {
            steps {
                sh "mvn clean install"   
            }
        }
        stage('Docker') {
            steps {
                container('docker') {  
                  sh "docker build -t devs90/devrepo:latest ."
                }
            }
        }
    }
}
