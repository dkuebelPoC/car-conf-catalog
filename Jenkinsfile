pipeline {
  agent { label 'node' }
  environment {
    REGISTRY = 'registry.example.com/kfz-config'
    IMAGE_TAG = "${env.GIT_COMMIT}"
    HELM_RELEASE = 'kfz-config-frontend'
    NAMESPACE = 'kfz-config-dev'
  }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Install & Test') {
      steps {
        sh 'npm ci'
        sh 'npm run build'
        // Tests optional
      }
    }
    stage('Docker Build & Push') {
      steps {
        sh """
          docker build -t $REGISTRY/${HELM_RELEASE}:$IMAGE_TAG .
          docker push $REGISTRY/${HELM_RELEASE}:$IMAGE_TAG
        """
      }
    }
    stage('Deploy to Dev') {
      steps {
        sh """
          helm upgrade --install $HELM_RELEASE helm/ \
            --namespace $NAMESPACE \
            --set image.repository=$REGISTRY/${HELM_RELEASE} \
            --set image.tag=$IMAGE_TAG
        """
      }
    }
  }
}
