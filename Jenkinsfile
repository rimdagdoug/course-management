pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *') // Polling SCM every 5 minutes
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')  // ID of the secret text (PAT) credential
        DOCKER_USERNAME = 'rimdagdoug'
        IMAGE_NAME = "${DOCKER_USERNAME}/course-project"  // Image name to push to Docker Hub
    }

    tools {
        maven 'Maven 3' // Maven tool configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:rimdagdoug/course-management.git',
                    credentialsId: 'Github' // GitHub credential ID for access to repo
            }
        }

        stage('Build Jar') {
            steps {
                script {
                    // Compilation with Maven to generate the JAR file
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Check Jar') {
            steps {
                script {
                    // Verifying the presence of the JAR file
                    sh 'ls -l target/'  // Listing files in the target directory
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Verifying the existence of Dockerfile and building Docker image
                    def dockerfilePath = 'Dockerfile'
                    if (fileExists(dockerfilePath)) {
                        dockerImage = docker.build("${IMAGE_NAME}", "-f ${dockerfilePath} .")
                    } else {
                        error "Dockerfile not found in the project root directory"
                    }
                }
            }
        }

        stage('Scan Docker Image') {
            steps {
                script {
                    // Scan de l'image Docker avec Trivy
                    def scanResult = sh(script: """
                        docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                        aquasec/trivy:latest image --exit-code 1 \\
                        --severity LOW,MEDIUM,HIGH,CRITICAL \\
			--timeout 60m \\
                        ${IMAGE_NAME}
                    """, returnStatus: true)

                    if (scanResult != 0) {
                        error "Vulnerability scan failed with exit code ${scanResult}"
                    } else {
                        echo "Vulnerability scan completed successfully."
                    }
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    // Using Docker Hub credentials (PAT) to authenticate and push the image
                    withDockerRegistry([ credentialsId: 'dockerhub' ]) {
                        echo "Pushing image ${IMAGE_NAME} to Docker Hub..."
                        dockerImage.push() // Pushing the image
                    }
                }
            }
        }
    }
}
