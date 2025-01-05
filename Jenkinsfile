pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Assurez-vous que l'ID de credential est correct
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

        stage('Test DockerHub Login') {
            steps {
                script {
                    // Tentative de connexion à DockerHub pour vérifier l'authentification
                    try {
                        echo "Tentative de connexion à DockerHub"
                        sh """
                        echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin
                        """
                        echo "Connexion réussie à DockerHub."
                    } catch (e) {
                        currentBuild.result = 'FAILURE'
                        echo "Erreur lors de la connexion à DockerHub : ${e.message}"
                        throw e
                    }
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                    echo "Vérification de la connexion DockerHub avant le push..."
                    try {
                        // Tentative de push avec des logs détaillés
                        echo "Poussée de l'image serveur vers DockerHub : ${IMAGE_NAME_SERVER}"
                        dockerImageServer.push()
                        echo "Poussée de l'image client vers DockerHub : ${IMAGE_NAME_CLIENT}"
                        dockerImageClient.push()
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
