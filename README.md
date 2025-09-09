# Math API

A Spring Boot API that provides basic Information about yourself.

## Example Requests

| Request URL | Response                                                              |
|-------------|-----------------------------------------------------------------------|
| `/api/info`    | `{ "email": "YourEmail@email.com", "current_datetime": "2025-...}` |


## Project Structure

```math_api/
├── src/
│ ├── main/
│ │ ├── java/com/example/apibasic/
│ │ │ ├── ApiResponse.java
│ │ │ ├── CorsConfig.java
│ │ │ ├── ErrorResponse.java
│ │ │ ├── ApiController.java
│ │ │ ├── NumberService.java
│ │ │ └── ApiApiApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ │ └── static/
│ │ └── templates/
│ └── test/
│ └── java/com/example/apibasic/
│ └── MathApiApplicationTests.java
├── pom.xml
└── README.md
```


## How to Set Up the Project

1. **Download and Install IntelliJ IDEA CE**:
    - Install the latest version of IntelliJ IDEA CE (Community Edition).

2. **Create the Spring Boot Project**:
    - Go to [start.spring.io](https://start.spring.io/).
    - Configure the project:
        - **Project**: Maven
        - **Language**: Java
        - **Spring Boot**: 3.4.2
        - **Group**: `com.example`
        - **Artifact**: `apibasic`
        - **Description**: Demo Project for Spring Boot
        - **Package Name**: `com.yourDomainName.apibasic`
        - **Packaging**: Jar
        - **Java**: 17
        - **Dependencies**: Spring Web, Spring Boot DevTools, Lombok
    - Click **Generate** to download the project.

3. **Open the Project in IntelliJ IDEA**:
    - Extract the downloaded ZIP file.
    - Open IntelliJ IDEA and select **Open**.
    - Navigate to the extracted folder and open it.

4. **Create the Required Java Files**:
    - Inside `src/main/java/com/example/apibasic`, create the following files:
        - `ApiResponse.java`
        - `CorsConfig.java`
        - `ErrorResponse.java`
        - `ApiController.java`
        - `ApiService.java`
        - `ApiApiApplication`


`ApiResponse.java`code:
```
package com.example.apibasic;


public class ApiResponse {
    private String email;
    private String current_datetime;
    private String github_url;

    public ApiResponse(String email, String current_datetime, String github_url) {
        this.email = email;
        this.current_datetime = current_datetime;
        this.github_url = github_url;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrent_datetime() {
        return current_datetime;
    }

    public void setCurrent_datetime(String current_datetime) {
        this.current_datetime = current_datetime;
    }

    public String getGithub_url() {
        return github_url;
    }

    public void setGithub_url(String github_url) {
        this.github_url = github_url;
    }
}
```

`CorsConfig.java` code:
```
package com.example.apibasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
```


`ApiController.java` code:
```
package com.example.apibasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse> getInfo() {
        ApiResponse response = apiService.getApiResponse();
        return ResponseEntity.ok(response);
    }
}
```

`ApiService.java` code:
```
package com.example.apibasic;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ApiService {

    public ApiResponse getApiResponse() {
        String email = "Youremail@email.com";
        String currentDatetime = Instant.now().toString(); // ISO 8601 format
        String githubUrl = "https://github.com/yourgithubUsername/apibasic";

        return new ApiResponse(email, currentDatetime, githubUrl);
    }
}
```
`ApibasicApplication` code:
```
package com.example.apibasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MathApiApplication.class, args);
    }
}
```



5. **Fix Lombok Error**:
    - Open `pom.xml` and add the Lombok dependency:
      ```xml
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.18.30</version>
          <scope>provided</scope>
      </dependency>
      ```
    - Reload Maven dependencies (`View` > `Tool Windows` > `Maven` > Reload).
    - Install the Lombok plugin in IntelliJ IDEA (`File` > `Settings` > `Plugins`).
    - Enable annotation processing (`File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors`).

6. **Run the Application**:
    - Open `ApiApplication.java` and click the green **Run** button.
    - The application will start on `http://localhost:8080`.

7. **Test the API**:
    - Use Postman to send a GET request to:
      ```
      http://localhost:8080/api/info
      ```
    - Example response:
      ```
      json
      {
   "email": "youremail@email.com",
   "current_datetime": "2025-02-05T19:23:14.647210Z",
   "github_url": "https://github.com/yourUsername/apibasic"
   }
      ```

8. **Deploy to AWS EC2**:

1. Launch an EC2 Instance
- Go to the AWS EC2 console.
- Click **Launch Instance**.
- **Choose an Amazon Machine Image (AMI):**
    - Ubuntu 22.04 LTS or Amazon Linux 2023.
- **Choose an Instance Type:**
    - `t2.micro` (Free tier) or `t3.small` (better performance).
- **Configure Security Group:**
    - Allow **HTTP (80)**, **HTTPS (443)**, and **SSH (22)**.
- **Set Authentication:**
    - Use a **key pair** for SSH access (download the `.pem` file).
- Click **Launch**.

2. Connect to EC2 via SSH  
   ```ssh -i your-key.pem ubuntu@your-ec2-public-ip```
   For Windows, use PuTTY with the `.pem` file.

3. Update EC2
```
sudo apt update && sudo apt upgrade -y
```
4. Install OpenJDK 17
```
sudo apt install openjdk-17-jdk -y
```
verify the installation `java -version`

Set Java 17 as default: `sudo update-alternatives --config java
`

Verify again: `java -version`

5 Set JAVA_HOME
```
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' | sudo tee -a /etc/environment
echo 'export PATH=$JAVA_HOME/bin:$PATH' | sudo tee -a /etc/environment
source /etc/environment
 ```

Verify: `echo $JAVA_HOME`

Expected output : `/usr/lib/jvm/java-17-openjdk-amd64
`

6 Check if Maven is also installed well and has no error: `mvn -version`

7 Upload Your Porject to EC2
There are multiple ways to upload your project.
If using GitHub, push your project to a repository, then clone it on EC2:
```
git clone <repository-url>
```

8 Build and Run Your Project
```
cd ~/apibasic
mvn clean package
mvn spring-boot:run
```

9 Test Your API
```
curl http://your-ec2-public-ip:8080/api/info
```


9. **Configure Your Domain in Route 53**:

If your domain is example.com, follow these steps:

1 Add an A Record in Route 53
- Go to AWS `Route 53`.
- Select your domain `(example.com)`.
- Click `Create` Record.

Set the following:
- Record name: LEAVE IT BLANK
- Record type: A - IPv4 address
- Value: `Your EC2 public IP`
- TTL: `300` (5 minutes)
- ClicK `Create` Record.
  Wait a few minutes for the DNS changes to propagate.

Test the Domain :
```
curl http://example.com:8080/api/info
```
10. **Remove :8080 Using NGINX Reverse Proxy**:

1 Install Nginx
```
sudo apt update
sudo apt install nginx -y
```

2 Edit Nginx Configuration
```
sudo nano /etc/nginx/sites-available/default
```
Replace everything in the file with:
```
server {
    listen 80;
    server_name example.com;

    location / {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

3 Restart Nginx
```
sudo systemctl restart nginx
```

Test Final result : `curl http://example.com/api/info
`

