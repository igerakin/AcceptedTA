
#!/bin/bash

# Docker run script for Technical Assessment

echo "🚀 Starting Technical Assessment Application..."

# Run the Docker container
docker run -d \
  --name technical-assessment-app \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=docker \
  -v $(pwd)/logs:/app/logs \
  technical-assessment:latest

# Check if container started successfully
if [ $? -eq 0 ]; then
    echo "✅ Container started successfully!"
    echo "🌐 Application URL: http://localhost:8080"
    echo "📖 Swagger UI: http://localhost:8080/swagger-ui.html"
    echo "🗄️  H2 Console: http://localhost:8080/h2-console"
    echo "❤️  Health Check: http://localhost:8080/actuator/health"
    
    # Show container status
    echo ""
    echo "📊 Container Status:"
    docker ps | grep technical-assessment-app
else
    echo "❌ Failed to start container!"
    exit 1
fi
