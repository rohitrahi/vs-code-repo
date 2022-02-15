# Developing Cloud Native Applicatons on OCI

In this workshop, we take a real-world situation and continuously improve it starting from the very basics and then increasing the scope gradually. We discuss requirements, make assumptions, work through limitations, and automate individual components with a goal of translating a real-world scenario into a working application. Let me explain further.

## Real-world scenario

For the workshop, we’ve drawn up a fictitious hotel chain named "Vision Stays" (I know, our naming could be better). They have a static website that is very rudimentary. The website runs in an on-premises colocation facility that is about to be shut down. “Vision Stays” want to migrate their website to a cloud provider (Oracle Cloud Infrastructure). They also want to expand and enhance the existing website by:

- Allowing customers to sign up and create an account
- Enabling customers to browse hotel availability and make reservations/view bookings
- Sharing available inventory in real-time with hotel aggregators (e.g., Expedia, Booking, etc.).

The current and future states of the website are shown below. Note that the future state of the website includes new functionality (sign-up, make booking, view reservations). 


Similar to how a migration like this might happen in real world, the app development happens in phases:

<ol>

 <li>Phase 1 – Migrate the static website to OCI; and make it highly-available (load balancer)</li>
 <li>Phase 2 – Use microservices architecture to add additional capabilities (sign-up, authentication, reservation etc.)</li>
 <li>Phase 3 – Secure the application (WAF, Key Management)</li>
<li>Phase 4 – Automate application build, test, and deployment (CI/CD pipelines)</li>
<li>Phase 5 – Add observability (monitoring, alerting, logging)</li>

</ol>

## Reference Architecture 

The final reference architecture for the application and some key components are described below.

![Reference Architecture](https://github.com/rohitrahi/vs-code-repo/blob/main/images/architecture.jpg)


Initially, the static application is migrated and deployed to OCI VMs and put behind a load balancer. This setup ensures that the current static web app is highly available. **OCI Resource Manager** is used to automate infrastructure creation. Next, the application is changed to leverage a microservices-based architecture pattern.         

In order to enable customers to make bookings, the application uses three dedicated microservices – Customer, Booking, and Hotel. These microservices implement a specific functional area of the application:

- The Customer microservice is used for registering new customers and getting customer related data
- The Booking microservice is used for making bookings
- The Hotel microservice is used for hotel inventory, including details such as hotel location, room availability and pricing.
- These microservices are deployed as containers in **Oracle Container Engine for Kubernetes (OKE)**.

On the data side, each microservice has its own database that best fits its requirements. Having one database for each service ensures loose coupling between services because all requests for data go through the service APIs and not a shared database. The application leverages **Oracle Autonomous JSON database** that offers developer-friendly document APIs and native JSON support.  

To enable “Vision Stays” to share its inventory data with third-party aggregators in real-time, the application leverages **Functions**, OCI’s serverless computing platform. 

There are two functions:

- The first one, get-hotel-details-func is used as an interface to expose the hotel booking application to third-party aggregators to retrieve hotel details
- The second function, create-booking-func is used to make bookings by third-party aggregators
developing native cloud apps - hotel scenario for workshop

An **OCI API Gateway** is used to create governed interfaces for microservices and functions. The application calls microservices through the API Gateway. This enables users to sign up, list hotels, query availability, make reservations and view bookings (functionality enabled by the microservices described above).

Security is critical for this application, so it leverages an **OCI Web Application Firewall (WAF)** to add Rate Limiting. A rate limiting rule monitors the frequency of requests from a particular client (IP Address) and limits the number of requests allowed from a particular client within a given time interval. The application also uses **OCI Vault service** to create and retrieve secrets – this ensures that usernames and passwords for the JSON database and OCI Registry are not hard coded in the application.

Initially, the app is built and deployed manually. In later practices, build, test, and deployment is automated using the **OCI DevOps** service. OCI DevOps service supports three types of deployment environments:  Functions, OKE clusters and Instance groups. This application uses deployment pipelines to deploy to all three destinations. It also leverages the “Build Triggers” functionality to trigger CI/CD pipelines that build, deliver, and deploy artifacts when a code change is pushed to the OCI DevOps code repository.

Finally, the application integrates with OCI Observability services such as **Monitoring, Logging, Notifications and Streaming** to monitor the CI/CD pipelines, view logs and get an email notification when booking occurs.

This, in a nutshell, is what you'll be building in this workshop.

## Workshop Guide

The workshop guide is available at [Developing Cloud Native Applications on Oracle Cloud Infrastructure](https://mylearn.oracle.com/course/developing-cloud-native-applications-on-oci-workshop/35644/102393).


