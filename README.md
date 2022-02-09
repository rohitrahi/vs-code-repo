In this workshop, we take a real-world situation and continuously improve it starting from the very basics and then increasing the scope gradually. We discuss requirements, make assumptions, work through limitations, and automate individual components with a goal of translating a real-world scenario into a working application. Let me explain further.

For the workshop, we’ve drawn up a fictitious hotel chain named "Vision Stays" (I know, our naming could be better). They have a static website that is very rudimentary. The website runs in an on-premises colocation facility that is about to be shut down. “Vision Stays” want to migrate their website to a cloud provider (Oracle Cloud Infrastructure). They also want to expand and enhance the existing website by:

Initially, the static application is migrated and deployed to OCI VMs and put behind a load balancer. This setup ensures that the current static web app is highly available. OCI Resource Manager is used to automate infrastructure creation. Next, the application is changed to leverage a microservices-based architecture pattern.         

In order to enable customers to make bookings, the application uses three dedicated microservices – Customer, Booking, and Hotel. These microservices implement a specific functional area of the application:


An OCI API Gateway is used to create governed interfaces for microservices and functions. The application calls microservices through the API Gateway. This enables users to sign up, list hotels, query availability, make reservations and view bookings (functionality enabled by the microservices described above).

Security is critical for this application, so it leverages an OCI Web Application Firewall (WAF) to add Rate Limiting. A rate limiting rule monitors the frequency of requests from a particular client (IP Address) and limits the number of requests allowed from a particular client within a given time interval. The application also uses OCI Vault service to create and retrieve secrets – this ensures that usernames and passwords for the JSON database and OCI Registry are not hard coded in the application.

Initially, the app is built and deployed manually. In later practices, build, test, and deployment is automated using the OCI DevOps service. OCI DevOps service supports three types of deployment environments:  Functions, OKE clusters and Instance groups. This application uses deployment pipelines to deploy to all three destinations. It also leverages the “Build Triggers” functionality to trigger CI/CD pipelines that build, deliver, and deploy artifacts when a code change is pushed to the OCI DevOps code repository.

Finally, the application integrates with OCI Observability services such as Monitoring, Logging, Notifications and Streaming to monitor the CI/CD pipelines, view logs and get an email notification when booking occurs.

This, in a nutshell, is what you'll be building in this workshop.
