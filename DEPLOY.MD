# Deploy Fargate + Load Balancer + AuroraDB

## Deployment Steps:

1. **Go to RDS**
    1. Create Aurora MySQL database
        1. Select serverless instance
        2. Create new Security Group for the database
        3. Select "Dev/Test" template - optional
        4. Enable Data API access
    2. Connect to the query editor and run
        ```
        CREATE DATABASE mldb;
        ```

2. **Upload image to ECR**
    1. Connect Docker service to AWS:
        ```
        aws ecr get-login-password --region <region> | docker login --username AWS --password-stdin <aws_account_id>.dkr.ecr.<region>.amazonaws.com
        ```

    2. Tag your image:
        ```
        docker tag <source_image> <account-id>.dkr.ecr.us-east-1.amazonaws.com/<repo-name>:latest
        ```

    3. Push the Image:
        ```
        docker push <aws_account_id>.dkr.ecr.<region>.amazonaws.com/<repository_name>:<tag>
        ```

3. **Go to ECS**
    1. Create Cluster
    2. Create Task definition
        1. Set Fargate launch type
        2. Create new Task Role
            1. Select  "AWS Service/EC2"
            2. Select AmazonRDSDataFullAccess policy
        3. Set the new role to the task definition
        4. Put here the image URI

4. **Go to EC2**
    1. Create Load Balancer
        1. Create Security Group - Set LB port (80)
        2. Create Target Group - Set here app port and TargetType IP

5. **Go to ECS**
    1. Create Service in the Cluster - Set desired task to 2, but it is possible to use Autoscaling policy
        1. Add new Security Group
            1. Add new inbound rule for All TCP and select the source, LB-SecurityGroup
        2. Select LB

6. **Go to RDS**
    1. Select Security Group of the AuroraDB
    2. Add new inbound rule for All TCP and select the source, AppService-SecurityGroup
    