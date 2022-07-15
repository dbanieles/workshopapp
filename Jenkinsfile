pipeline {
    agent any
    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    environment {
        GIT_URL = "https://github.com/dbanieles/workshopapp.git"
        GIT_CREDENTAL_ID = "bitbucket"
        DOCKER_REGISTRY = "https://hub.docker.com/"
        DOCKER_REPOSITORY = "devs90/devrepo"
        DOCKER_CREDENTIAL_ID = "dockerhub"
        DOCKERHUB_CREDENTIALS = credentials("dockerhub")
    }
    stages {
        stage("Git") {
            steps {
                echo "Clone repository"
                echo params.branch
                dir("project") {
                    git url: GIT_URL
                }
                            
            }
        }

        stage("Sonar analisys") {
            steps {
                echo "Validate"
            }
        }

        stage("Unit Test") {
            steps {
                echo "Unit Test"
                dir("project"){
                    sh '''
                        mvn test surefire-report:report
                    '''
                }
            }
        }

        stage("Integration Test") {
            steps {
                echo "Integration Test"
                dir("project"){
                    // sh "mvn test"
                }
            }
        }
        
        stage("Build") {
            steps {
                echo "Build"
                dir("project"){
                    sh '''
                        mvn -B -DskipTests clean package
                    '''
                }
            }
        }

        stage("Docker") {
            steps {
                echo "Docker"
                dir("project"){
                    script {
                        sh '''docker build -t devs90/workshop .'''
                    }
                }
            }
        }
        
        stage("Publish") {
            steps {
                echo "Publish"
                dir("project"){
                    
                    script {
                            echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                            docker push devs90/workshop:latest
                          '''
                    }

                }
            }
        }
    }      
}
