pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME_SERVER = '[username]/mern-server:${GIT_COMMIT}'
        IMAGE_NAME_CLIENT = '[username]/mern-client:${GIT_COMMIT}'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:rimdagdoug/course-management.git',
                    credentialsId: 'Github'
            }
        }

        stage('Build Server Image') {
            steps {
                dir('server') {
                    script {
                        dockerImageServer = docker.build("${IMAGE_NAME_SERVER}")
                    }
                }
            }
        }

        stage('Build Client Image') {
            steps {
                dir('client') {
                    script {
                        dockerImageClient = docker.build("${IMAGE_NAME_CLIENT}")
                    }
                }
            }
        }

        stage('Scan Server Image') {
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    aquasec/trivy:latest image --exit-code 0 \\
                    --severity LOW,MEDIUM,HIGH,CRITICAL \\
                    ${IMAGE_NAME_SERVER}
                    """
                }
            }
        }

        stage('Scan Client Image') {
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    aquasec/trivy:latest image --exit-code 0 \\
                    --severity LOW,MEDIUM,HIGH,CRITICAL \\
                    ${IMAGE_NAME_CLIENT}
                    """
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                    // Vérifie la version de Docker
                    sh 'docker --version'

                    // Donne des informations détaillées sur Docker
                    sh 'docker info'

                    // Liste les conteneurs Docker existants (utile pour voir s'il y a des erreurs)
                    sh 'docker ps -a'

                    // Essaye de te connecter à DockerHub pour vérifier l'authentification
                    sh """
                    echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin
                    """

                    // Push des images vers DockerHub
                    try {
                        docker.withRegistry('', "${DOCKERHUB_CREDENTIALS}") {
                            dockerImageServer.push()
                            dockerImageClient.push()
                        }
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        echo "Erreur lors du push vers DockerHub : ${e.message}"
                        throw e
                    }
                }
            }
        }
    }
}
