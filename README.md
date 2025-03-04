####**Overview**

This project demonstrates the deployment of the Binary Calculator WebApp using Docker and Kubernetes on Google Kubernetes Engine (GKE). The web application allows users to perform binary arithmetic operations and is hosted on a cloud-based Kubernetes cluster.

**Deployment Steps**

**1. Set Up Google Kubernetes Engine (GKE)**

**Set the compute zone:**

gcloud config set compute/zone northamerica-northeast1-b

**Create a Kubernetes cluster:**

gcloud container clusters create sofe3980u --num-nodes=3

**2. Deploy MySQL Server on GKE**

**Deploy MySQL using Kubernetes:**

kubectl create deployment mysql-deployment --image=mysql/mysql-server --port=3306

**Expose the MySQL service:**

kubectl expose deployment mysql-deployment --type=LoadBalancer --name=mysql-service

**Retrieve the external IP of MySQL service:**

kubectl get service --watch

**3. Build and Push the Docker Image**

**Navigate to the project directory:**

cd ~/SOFE3980U-Lab3-Part1/BinaryCalculatorWebapp

**Package the Maven project:**

mvn package

**Build the Docker image:**

docker build -t northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator .

**Push the Docker image to GCP Artifact Registry:**

docker push northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator

**4. Deploy the Binary Calculator WebApp on GKE**

**Deploy the application using Kubernetes:**

kubectl create deployment binarycalculator-deployment --image=northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator --port=8080

**Expose the service:**

kubectl expose deployment binarycalculator-deployment --type=LoadBalancer --name=binarycalculator-service

**Retrieve the external IP of the web application:**

kubectl get service --watch

**Access the application in the browser:**

http://<EXTERNAL-IP>:8080

**Updating the Deployment**

If you update the application code, follow these steps to redeploy:

**Rebuild the Docker image:**

mvn package
docker build -t northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator .
docker push northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator

**Update the running deployment:**

kubectl set image deployment/binarycalculator-deployment binarycalculator=northamerica-northeast2-docker.pkg.dev/lab-3-part-1-452700/sofe3980u/binarycalculator

**Deleting the Deployment**

**To remove the deployment and service:**

kubectl delete deployment binarycalculator-deployment
kubectl delete service binarycalculator-service

**Summary**

This project demonstrates the use of Docker and Kubernetes for deploying a Java-based WebApp on Google Kubernetes Engine (GKE). By following the steps above, you can successfully deploy, update, and manage the Binary Calculator WebApp in the cloud.
