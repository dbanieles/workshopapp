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
        DOCKER_IMAGE = ""
    }
    stages {
        stage("Git") {
            steps {
                echo "Clone repository"
                echo params.branch
                dir("project") {
                    git url: GIT_URL
                    branch: "master"
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
                        DOCKER_IMAGE = docker.build(DOCKER_REPOSITORY)
                    }
                }
            }
        }
        
        stage("Publish") {
            steps {
                echo "Publish"
                dir("project"){
                    script {
                        docker.withRegistry(DOCKER_REPOSITORY, DOCKER_CREDENTIAL_ID) {
                            DOCKER_IMAGE.push("${env.BUILD_NUMBER}")
                            DOCKER_IMAGE.push("latest")
                        }
                    }
                }
            }
        }

        stage("Finish") {
            steps{
                echo "Clean up workdir"
                dir("project"){
                    sh '''
                        docker rmi $DOCKER_REGISTRY:$BUILD_NUMBER
                        docker rmi $DOCKER_REGISTRY:latest
                    '''
                    deleteDir()
                }
            }
        }   
    }      
}
