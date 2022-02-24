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
        CURRENT_BRANCH = env.BRANCH_NAME
        TAG = env.BUILD_NUMBER
    }
    stages {
        stage('Code Analisys') {
            steps {
                echo "Sonarqube Analisys"
            }
        }
        stage('Unit Test') {
            steps {
                echo "Unit Test"
            }
        }
        stage('Unit Test') {
            steps {
                echo "Integration Test"
            }
        }
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
                    docker tag devs90/workshop devs90/workshop:$TAG
                    docker push devs90/workshop:$TAG
                    docker push devs90/workshop:latest
                  '''
                }
            }
        }
        stage('Deploy') {
            steps {
                container('helm') {
                    script {
                        if(CURRENT_BRANCH == "master"){
                           echo "Deploy prod"
                           sh "helm version"
                        }

                       if(CURRENT_BRANCH == "develop"){
                          echo "Deploy dev and staging"
                          sh "helm version"
                       }

                       if(CURRENT_BRANCH != "develop" && env.BRANCH_NAME != "master"){
                         echo "Deploy dev"
                         sh "helm version"
                       }
                    }
                }
            }
        }
    }
}