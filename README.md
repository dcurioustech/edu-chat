# EduChat - AI-Powered Educational Chat Application

EduChat is a Spring Boot-based AI chat application designed for educational purposes. It provides a simple web-based chat interface, supports the creation and management of AI assistants, integrates tool functions, manages Model Context Protocol (MCP) connections, and uses Milvus as a vector database for storing embeddings. This project is ideal for learning about real-time web applications, REST APIs, vector databases, and modular software design.

## 🌟 Features

- 💬 Real-time chat interface with WebSocket
- 🤖 AI assistant creation and management
- 🛠️ Extensible tool functions (e.g., weather lookup)
- ☁️ Multi-cloud provider (MCP) integration
- 🧠 Vector database support with Milvus
- 🚀 Built with Spring Boot 3.2.0 & Java 21

## 🚀 Quick Start

### Prerequisites
- Java 21+
- Maven 3.6+
- Web browser with JavaScript enabled

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd edu-chat
   ```

2. **Start Milvus** (if using vector features)
    - Follow the [Milvus installation guide](https://milvus.io/docs/install_standalone-docker.md)
    - Or update `application.properties` with your Milvus connection details

3. **Build and run the application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**
    - Web Interface: http://localhost:8080/chat
    - API Documentation: http://localhost:8080/swagger-ui.html (if enabled)

## 🏗️ Project Structure

```

src/main/java/com/dcurioustech/educhat/
├── config/                      # Configuration classes
│   ├── WebSocketConfig.java       # Configures WebSocket for real-time chat
│   └── MilvusConfig.java          # Configures Milvus vector database client
├── controller/                   # REST controllers
│   ├── ChatController.java        # Handles chat messages via WebSocket
│   └── AssistantController.java   # Manages assistant creation and retrieval
├── service/                      # Business logic
│   ├── ChatService.java           # Processes chat messages and integrates with Milvus
│   ├── AssistantService.java      # Manages assistant creation and storage
│   ├── ToolService.java           # Executes tool functions (e.g., weather API)
│   └── MCPService.java           # Simulates multi-cloud provider connections
├── model/                        # Data models
│   ├── Assistant.java             # Model for AI assistants
│   ├── ChatMessage.java           # Model for chat messages
│   └── ToolFunction.java          # Model for tool functions
├── repository/                   # Data access layer
│   └── AssistantRepository.java   # JPA repository for assistant persistence
├── websocket/                    # WebSocket implementation
│   └── ChatWebSocketHandler.java  # Low-level WebSocket handler
└── resources/                    # Application resources
    ├── templates/                # Thymeleaf templates
    │   └── chat.html             # Chat UI template
    └── application.properties    # Application configuration
```

## 🛠️ Configuration

Edit `src/main/resources/application.properties` to configure:

```properties
# Server Configuration
server.port=8080

# H2 Database
spring.datasource.url=jdbc:h2:mem:educhatdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Milvus Vector Database
milvus.host=localhost
milvus.port=19530
milvus.collection=educhat_embeddings
```

## 📚 API Reference

### Chat
- `GET /chat` - WebSocket endpoint for real-time chat
- `POST /api/chat` - Send a chat message
  ```json
  {
    "content": "Hello, EduChat!"
  }
  ```

### Assistants
- `GET /api/assistants` - List all assistants
- `POST /api/assistants` - Create a new assistant
  ```json
  {
    "name": "Math Tutor",
    "description": "Helps with math problems",
    "toolIds": "calculator,formula_reference"
  }
  ```

## 🔧 Development

### Building the Project
```bash
mvn clean install
```

### Running Tests
```bash
mvn test
```

### Code Style
This project follows the Google Java Style Guide. Run the following to format code:
```bash
mvn spotless:apply
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Milvus](https://milvus.io/)
- [H2 Database](https://www.h2database.com/)
- [SockJS](https://github.com/sockjs/sockjs-client)
- [STOMP](https://stomp.github.io/)

---

<div align="center">
  Made with ❤️ by the EduChat Team
</div>

Features

Chat Interface: A web-based UI using Thymeleaf and WebSocket for real-time messaging.

Assistant Management: Create and manage AI assistants via REST APIs.

Tool Functions: Extensible tools (e.g., weather lookup) for assistant capabilities.

MCP Connections: Simulated connections to cloud providers (e.g., AWS, Azure).

Vector Database: Milvus for storing and querying embeddings of chat messages or assistant configurations.

Prerequisites

Java 17 or later



Maven for dependency management



Milvus (running locally or in the cloud)



Web Browser for accessing the chat UI

Setup Instructions





Clone the Repository:

git clone <repository-url>
cd educhat



Install Milvus:





Follow the Milvus installation guide to set up Milvus locally or use a cloud-hosted instance.



Update src/main/resources/application.properties with your Milvus host and port:

milvus.host=localhost
milvus.port=19530
milvus.collection=educhat_embeddings



Configure Dependencies: Ensure the pom.xml includes the following dependencies:

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.milvus</groupId>
        <artifactId>milvus-sdk-java</artifactId>
        <version>2.2.3</version>
    </dependency>
</dependencies>



Run the Application:

mvn spring-boot:run



Access the Application:





Open http://localhost:8080/chat in a web browser to use the chat interface.



Use a tool like Postman to interact with the REST API (e.g., http://localhost:8080/api/assistants).

Usage

Chat Interface





Navigate to http://localhost:8080/chat.



Type a message in the input field and click "Send" to interact with the AI.



Messages are processed in real-time via WebSocket, with responses displayed in the chat window.

Assistant Management





Create an Assistant: Send a POST request to http://localhost:8080/api/assistants with a JSON body:

{
"name": "MathTutor",
"description": "Helps with math problems",
"toolIds": "weather,calculator"
}



List Assistants: Send a GET request to http://localhost:8080/api/assistants to retrieve all assistants.

Tool Functions





The ToolService includes a sample weather tool. Extend it by adding new tools in ToolService.java and updating the ToolFunction model.



Example: Modify ToolService.executeTool to integrate with real APIs (e.g., OpenWeatherMap).

MCP Connections





The MCPService simulates cloud provider connections. Replace the placeholder logic in MCPService.java with real SDKs (e.g., AWS SDK for Java) for production use.

Vector Database





The ChatService and AssistantService use Milvus to store embeddings (e.g., for chat messages or assistant descriptions).



For educational exploration, integrate an embedding model (e.g., SentenceTransformers) to generate real embeddings.

Educational Notes





WebSocket: Learn real-time communication by experimenting with WebSocketConfig and ChatWebSocketHandler. Try adding features like private messaging.



Vector Database: Explore Milvus by generating embeddings for chat messages or assistant descriptions and implementing similarity searches.



REST APIs: Study AssistantController to understand RESTful design. Add endpoints for updating or deleting assistants.



Extensibility: Enhance the project by integrating a real AI model (e.g., via xAI’s API at https://x.ai/api) or adding authentication.

Contributing

Contributions are welcome! To contribute:





Fork the repository.



Create a feature branch (git checkout -b feature/your-feature).

Commit your changes (git commit -m "Add your feature").



Push to the branch (git push origin feature/your-feature).



Open a pull request.

License

This project is licensed under the MIT License.

Contact

For questions or support, contact the project maintainers or open an issue in the repository.